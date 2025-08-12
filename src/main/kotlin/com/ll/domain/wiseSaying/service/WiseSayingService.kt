package com.ll.domain.wiseSaying.service

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.global.Page
import com.ll.global.SingletonObjects

class WiseSayingService () {
    private val wiseSayingRepository = SingletonObjects.wiseSayingFileRepository

    fun addNewWiseSaying(content : String, author : String) : WiseSaying {
        return wiseSayingRepository.save(WiseSaying(author, content))
    }

    fun listAllWiseSayings() : List<WiseSaying> {
        return wiseSayingRepository.findAll()
    }

    fun modifyWiseSaying(wiseSaying: WiseSaying, author : String, content : String) {
        wiseSaying.modify(author, content)
        wiseSayingRepository.save(wiseSaying)
    }

    fun findWiseSayingById(id : Int) : WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun removeWiseSayingById(id : Int) : Boolean {
        return wiseSayingRepository.delete(id)
    }

    fun buildFromCurrentWiseSayings() {
        wiseSayingRepository.build()
    }

    fun getListOfQueriedWiseSayings(keywordType: String, keyword: String, page: Int): Page<WiseSaying> {
        return wiseSayingRepository.findByQuery(keywordType, keyword, page)
    }
}