package com.ll

var lastId = 0

fun main() {
    println("== 명언 앱 ==")
    val wiseSayings = mutableListOf<WiseSaying>();

    while (true) {
        print("명령) ")

        val command = tryInput()

        if (command == "종료") break

        if (command == "등록") {
            print("명언) ")
            val content = tryInput()
            print("작가) ")
            val author = tryInput()

            val wiseSaying = WiseSaying(++lastId, author, content)
            wiseSayings.add(wiseSaying)

            println("${wiseSaying.id} 번 명언이 등록되었습니다.")
        }

        if (command == "목록") {
            println("번호 / 작가 / 명언")
            println("-----------------------------")
            wiseSayings.reversed().forEach { it -> println(it) }
        }
    }
}

fun tryInput() : String{
    return readlnOrNull()!!.trim()
}