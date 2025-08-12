package com.ll.domain.wiseSaying.repository

import com.ll.domain.wiseSaying.entity.WiseSaying
import java.util.*

class WiseSayingMemoryRepository : WiseSayingRepository {
    private var lastId = 0
    private val wiseSayings = mutableListOf<WiseSaying>()

    init {
        initData()
    }

    override fun save(wiseSaying : WiseSaying): WiseSaying {
        if (wiseSaying.isNew()) {
            wiseSaying.id = ++lastId
            wiseSayings.add(wiseSaying)
        }

        return wiseSaying
    }

    override fun delete(id: Int): Boolean {
        return wiseSayings.removeIf{ it.id == id }
    }

    override fun findById(id: Int): WiseSaying? {
        return wiseSayings.find { w -> w.id == id }
    }

    override fun findAll(): List<WiseSaying> {
        return Collections.unmodifiableList(wiseSayings).reversed()
    }

    override fun clear() {
        wiseSayings.clear()
        lastId = 0
    }

    override fun build() {}

    override fun isEmpty(): Boolean {
        return wiseSayings.isEmpty()
    }
}