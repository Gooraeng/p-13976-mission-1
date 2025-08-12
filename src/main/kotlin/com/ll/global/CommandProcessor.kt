package com.ll.global

import com.ll.domain.wiseSaying.enums.CommandType

/*
* 명령어 및 쿼리 스트링 처리 클래스
* */
class CommandProcessor (_command : String){
    private val commandType: CommandType
    private val querySet = mutableMapOf<String, String>()
    private val queryString: String?

    init {
        val queryList = _command.split("?", limit = 2)
        commandType = CommandType.valueOf(queryList[0])

        queryString = if(queryList.size > 1) queryList[1] else null

        queryString?.let {
            for (singleQuery in queryString.split("&")) {
                val splitQuery = singleQuery.split("=", limit = 2)

                if (splitQuery.size != 2) {
                    continue
                }

                val key = splitQuery[0].trim()
                val value = splitQuery[1].trim()

                querySet[key] = value
            }
        }
    }

    fun getItemFromKey(key: String): String? {
        return querySet[key]
    }

    var command: CommandType
        get() = commandType
        set(value) {throw IllegalArgumentException("명령어를 직접 입력할 수 없습니다") }

    val query: String?
        get() = queryString
}