package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying
import java.util.*

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): WiseSaying

    fun delete(id : Int): Boolean

    fun findById(id : Int) : WiseSaying?

    fun findAll() : List<WiseSaying>

    fun clear()

    fun build()

    fun isEmpty() : Boolean

    // page 관련 미반영
    fun findByQuery(keywordType: String?, keyword: String?) : List<WiseSaying> {
        val wiseSayings = findAll()

        val toCopy = if (keywordType == "content" && keyword != null) {
            wiseSayings.filter { w -> w.content.contains(keyword) }
        } else if (keywordType == "author" && keyword != null) {
            wiseSayings.filter { w -> w.author.contains(keyword) }
        } else if (keywordType.isNullOrEmpty() && keyword.isNullOrEmpty()) {
            wiseSayings
        } else {
            emptyList()
        }

        return Collections.unmodifiableList(toCopy).reversed();
    }
}