package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.global.Page

interface WiseSayingRepository {
    val PER_PAGE: Int
        get() = 5

    fun save(wiseSaying: WiseSaying): WiseSaying

    fun delete(id : Int): Boolean

    fun findById(id : Int) : WiseSaying?

    fun findAll() : List<WiseSaying>

    fun clear()

    fun build()

    fun isEmpty() : Boolean

    // page 관련 미반영
    fun findByQuery(keywordType: String?, keyword: String?, page: Int) : Page<WiseSaying> {
        val allWiseSayings = findAll()
        val pages = Math.ceilDiv(allWiseSayings.size, PER_PAGE)
        var wiseSayings : List<WiseSaying>

        try {
            val trimSize = if (allWiseSayings.size >= PER_PAGE) PER_PAGE * page else allWiseSayings.size

            wiseSayings = allWiseSayings.subList(PER_PAGE * (page - 1), trimSize)
        } catch (e: Exception) {
            throw IllegalArgumentException("해당 페이지를 찾을 수 없습니다.")
        }

        val data = if (keywordType == "content" && keyword != null) {
            wiseSayings.filter { w -> w.content.contains(keyword) }
        } else if (keywordType == "author" && keyword != null) {
            wiseSayings.filter { w -> w.author.contains(keyword) }
        } else if (keywordType.isNullOrEmpty() && keyword.isNullOrEmpty()) {
            wiseSayings
        } else {
            emptyList()
        }

        return Page(pageNo = page, pages = pages, data = data);
    }

    fun initData() {
        for (i in 1..10) {
            save(WiseSaying("작자미상 $i", "명언 $i"))
        }
    }
}