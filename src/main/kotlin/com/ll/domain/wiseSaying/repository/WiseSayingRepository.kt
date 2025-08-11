package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying

class WiseSayingRepository {
    private var lastId = 0
    private val WiseSayingList = mutableListOf<WiseSaying>()

    fun save(wiseSaying: WiseSaying) : WiseSaying {
        if (wiseSaying.isNew()) {
            wiseSaying.id = ++lastId
            WiseSayingList.add(wiseSaying)
        }
        return wiseSaying
    }

    fun findById(id: Int): WiseSaying? {
        return WiseSayingList.find { it.id == id }
    }

    fun delete(id : Int) : Boolean {
        return WiseSayingList.removeIf { it.id == id }
    }

    fun findAll(): List<WiseSaying> {
        return WiseSayingList.toMutableList().reversed();
    }
}