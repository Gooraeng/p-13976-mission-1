package com.ll.domain.wiseSaying.entity

import com.ll.standard.util.JsonHelper

class WiseSaying (
    var id : Int = 0,
    var author : String,
    var content : String
) {

    constructor(author : String, content: String) : this (0, author, content)

    companion object {
        fun fromJson(json: String): WiseSaying {
            val map = JsonHelper.toMap(json)

            return WiseSaying(
                id = map["id"] as Int,
                content = map["content"] as String,
                author = map["author"] as String
            )
        }
    }

    fun modify(author : String, content : String) {
        this.author = author
        this.content = content
    }

    fun isNew() = id == 0

    val asMap: Map<String, Any>
        get() {
            return mapOf(
                "id" to id,
                "author " to author,
                "content" to content
            )
        }

    val asJsonStr: String
        get() {
            return """
                {
                    "id": $id,
                    "author": "$author",
                    "content": "$content"
                }
            """.trimIndent()
        }

    override fun toString(): String {
        return "$id / $author / $content"
    }
}
