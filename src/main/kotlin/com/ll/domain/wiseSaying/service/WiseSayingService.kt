package com.ll.domain.wiseSaying.service

import com.ll.domain.wiseSaying.entity.WiseSaying
import com.ll.domain.wiseSaying.repository.WiseSayingRepository

class WiseSayingService (val wiseSayingRepository: WiseSayingRepository) {

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
}