package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.global.AppConfig
import com.ll.standard.util.JsonHelper
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.nio.file.StandardOpenOption
import kotlin.io.path.exists

class WiseSayingRepository {
    private var lastId = 0
    private val wiseSayings : MutableList<WiseSaying> by lazy { loadJson() }
    private val dbPath = AppConfig.dBPath

    init {
        File(dbPath).mkdirs()
        load()
    }

    fun save(wiseSaying: WiseSaying) : WiseSaying {
        if (wiseSaying.isNew()) {
            wiseSaying.id = ++lastId
            wiseSayings.add(wiseSaying)
            saveAsFile("lastId.txt", "$lastId")
        }

        saveAsFile("${wiseSaying.id}.json", wiseSaying.asJsonStr)

        return wiseSaying
    }

    fun findById(id: Int): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }

    fun delete(id : Int) : Boolean {
        deleteWiseSayingFile(id)
        return wiseSayings.removeIf { it.id == id }
    }

    fun findAll(): List<WiseSaying> {
        return wiseSayings.toMutableList().reversed()
    }

    fun build() {
        val map = wiseSayings.map { wisSaying -> wisSaying.asMap }
        val toString = JsonHelper.toString(map)
        saveAsFile(AppConfig.buildFileName, toString)
    }

    private fun saveAsFile(fileName: String, value: String) {
        val targetPath = Paths.get(dbPath, fileName)
        val tmpFile = Paths.get(dbPath, "$fileName.tmp");

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

    private fun load() {
        loadJson()
        lastId = parseLastId()
    }

    private fun parseLastId() : Int {
        val path = Paths.get(dbPath, "lastId.txt")

        return (
            if (!Files.exists(path)) 0
            else Integer.parseInt(Files.readString(path))
        )
    }

    private fun loadJson() : MutableList<WiseSaying> {
        val path = Paths.get(dbPath, "data.json")
        if (!path.exists()) return mutableListOf()

        return path
            .toFile()
            .listFiles()
            ?.filter { it.name != "data.json" }
            ?.filter { it.name.endsWith(".json") }
            ?.map { it.readText() }
            ?.map { WiseSaying.fromJson(it) }
            ?.sortedByDescending { it.id }
            .orEmpty()
            .toMutableList()
    }

    private fun deleteWiseSayingFile(id: Int) : Boolean {
        return Paths.get("$dbPath/$id.json")
            .toFile()
            .delete()
    }
}