package com.ll.domain.wiseSaying.entity

class WiseSaying (var author : String, var content : String) {
    private var _id = 0

    override fun toString(): String {
        return "$_id / $author / $content"
    }

    var id: Int
        get() = _id
        set(value) {_id = value}

    fun modify(author : String, content : String) {
        this.author = author
        this.content = content
    }

    fun isNew() = _id == 0


}