package com.ll.global

class Page<T> (
    val pageNo: Int,
    val pages : Int,
    val data: List<T>
){

    val size = data.size

    fun getPageString(): String {
        val sb = StringBuilder("페이지 : ")

        for (i in 1..pages) {
            val toAdd = if (i == pageNo) "[ $i ]" else i
            sb.append(toAdd)
            if (i < pages) sb.append(" / ")
        }

        return sb.toString()
    }
}