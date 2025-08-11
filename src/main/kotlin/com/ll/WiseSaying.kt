package com.ll

class WiseSaying (val id : Int, var author : String, var content : String) {

    override fun toString(): String {
        return "$id / $author / $content"
    }
}