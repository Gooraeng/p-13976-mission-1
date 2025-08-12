package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying

interface WiseSayingRepository {

    fun save(wiseSaying: WiseSaying): WiseSaying
    fun delete(id : Int): Boolean
    fun findById(id : Int) : WiseSaying?
    fun findAll() : List<WiseSaying>
    fun clear()
    fun build()
    fun isEmpty() : Boolean

}