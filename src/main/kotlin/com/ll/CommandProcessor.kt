package com.ll

class CommandProcessor (val _command : String){
    private lateinit var commandString: String
    private val querySet = mutableMapOf<String, Int>();

    init {
        val queryList = _command.split("?", limit = 2)
        commandString = queryList[0]

        val queryString = if(queryList.size > 1) queryList[1] else ""

        if (queryString.isNotEmpty()) {
            for (singleQuery in queryString.split("&")) {
                val splitQuery = singleQuery.split("=", limit = 2)
                if (splitQuery.size != 2) {
                    throw IllegalArgumentException("잘못된 쿼리 입력이 감지되었습니다")
                }
                querySet.put(splitQuery[0], splitQuery[1].toInt())
            }
        }
    }

    fun getItemFromKey(key: String): Int? {
        return querySet[key]
    }

    var command: String
        get() = commandString
        set(value) {throw IllegalArgumentException("명령어를 직접 입력할 수 없습니다") }
}