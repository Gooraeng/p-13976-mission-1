package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.global.AppConfig
import com.ll.global.AppConfig.buildFileName
import com.ll.standard.util.JsonHelper
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption

class WiseSayingFileRepository : WiseSayingRepository {
    private val dbPath = AppConfig.dBPath

    private fun createDbPath() {
        File(dbPath).mkdirs()
    }

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        createDbPath()

        if (wiseSaying.isNew()) {
            wiseSaying.id = generateId()
        }

        saveAsFile("${wiseSaying.id}.json", wiseSaying.asJsonStr)
        return wiseSaying
    }

    private fun generateId(): Int {
        return (getLastId() + 1).also {
            saveLastId(it)
        }
    }

    private fun getLastId(): Int {
        try {
            return Paths.get("$dbPath/lastId.txt")
                .toFile()
                .readText()
                .toInt()
        } catch (e: Exception) {
            return 0
        }
    }

    private fun saveLastId(lastId: Int) {
        saveAsFile("lastId.txt", lastId.toString())
    }

    override fun delete(id: Int): Boolean {
        createDbPath()

        return Paths.get("$dbPath/$id.json")
            .toFile()
            .takeIf { it.exists() }
            ?.delete()
            ?: false
    }

    override fun findById(id: Int): WiseSaying? {
        createDbPath()

        return Paths.get("$dbPath/$id.json")
            .toFile()
            .takeIf { it.exists() }
            ?.readText()
            ?.let(WiseSaying::fromJson)
    }

    override fun findAll(): List<WiseSaying> {
        return createWiseSayingList().reversed()
    }

    private fun createWiseSayingList() : List<WiseSaying> {
        createDbPath()

        return Paths.get(dbPath)
            .toFile()
            .listFiles()
            ?.filter { it.name != buildFileName }
            ?.filter { it.name.endsWith(".json") }
            ?.map { it.readText() }
            ?.map { WiseSaying.fromJson(it) }
            .orEmpty()
    }

    override fun clear() {
        Paths.get(dbPath)
            .toFile().deleteRecursively()
    }

    override fun build() {
        val map = createWiseSayingList()
            .map(WiseSaying::asMap)

        saveAsFile(buildFileName, JsonHelper.toString(map))
    }

    override fun isEmpty(): Boolean {
        createDbPath()

        return Paths.get(dbPath)
            .toFile()
            .listFiles()
            ?.filter { it.name != buildFileName }
            ?.none { it.name.endsWith(".json") }
            ?: true
    }

    private fun saveAsFile(fileName: String, value: String) {
        val targetPath = Paths.get("$dbPath/", fileName)
        val tmpFile = Paths.get("$dbPath/", "$fileName.tmp");

        // 임시 파일 작성
        val newBufferedWriter = Files.newBufferedWriter(
            tmpFile,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        )

        try {
            newBufferedWriter.write(value)
        } finally {
            newBufferedWriter.close()
        }

        // 임시 파일을 실제 파일로 수정
        Files.move(
            tmpFile, targetPath,
            StandardCopyOption.ATOMIC_MOVE,
            StandardCopyOption.REPLACE_EXISTING
        )
    }
}