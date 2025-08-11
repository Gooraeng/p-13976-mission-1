package com.ll

var lastId = 0

fun main() {
    println("== 명언 앱 ==")

    while (true) {
        print("명령) ")

        val command = tryInput()

        if (command == "종료") break

        if (command == "등록") {
            print("명언) ")
            val content = tryInput()
            print("작가) ")
            val author = tryInput()

            println("${++lastId} 번 명언이 등록되었습니다.")
        }
    }
}

fun tryInput() : String{
    return readlnOrNull()!!.trim()
}