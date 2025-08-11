package com.ll

fun main() {
    println("== 명언 앱 ==")

    while (true) {
        print("명령) ")

        val command = readlnOrNull()

        if (command == "종료") {
            break
        }
    }
}