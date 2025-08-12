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
    fun findByQuery(keywordType: String, keyword: String, page: Int) : Page<WiseSaying> {
        // 조건에 맞는 데이터 먼저 구하기
        val data =
            if (keywordType.isEmpty() && keyword.isEmpty()) findAll()

            else if (keywordType == "content" && keyword.isNotEmpty()) {
                findAll().filter { it.content.contains(keyword) }
            }
            else if (keywordType == "author" && keyword.isNotEmpty()) {
                findAll().filter { it.author.contains(keyword) }
            }
            else emptyList()

        val start : Int = PER_PAGE * (page - 1)
        val maxPage = Math.ceilDiv(data.size, PER_PAGE)
        val end : Int = if (data.size >= PER_PAGE) start + PER_PAGE else data.size

        if (page <= 0 || page > maxPage) throw IllegalArgumentException("존재하지 않는 페이지입니다.")

        return Page(
            pageNo = page,
            pages = maxPage,
            data = data.subList(start, end)
        )
    }

    fun initData() {
        for (i in 1..10) {
            save(WiseSaying("작자미상 $i", "명언 $i"))
        }
    }
}