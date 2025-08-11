package com.ll

var lastId = 0

fun main() {
    println("== 명언 앱 ==")
    val wiseSayings = mutableListOf<WiseSaying>();

    while (true) {
        print("명령) ")

        val commandProcessor = CommandProcessor(tryInput())
        val command = commandProcessor.command

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

        if (command == "삭제") {
            val id = commandProcessor.getItemFromKey("id")

            val result = wiseSayings.removeIf { wiseSaying -> wiseSaying.id == id }

            if (result) {
                println("$id 번 명언이 삭제되었습니다.")
            } else {
                println("$id 번 명언은 존재하지 않습니다.")
            }
        }

        if (command == "수정") {
            val id = commandProcessor.getItemFromKey("id")

            val wiseSaying : WiseSaying? = wiseSayings.find { w -> w.id == id }

            wiseSaying?.let {
                println("명언(기존) : ${it.content}")
                print("명언 : ")
                val newContent = tryInput()

                println("작가(기존) : ${it.author}")
                print("작가) ")
                val newAuthor = tryInput()

                wiseSaying.author = newAuthor
                wiseSaying.content = newContent
            } ?: run {
                println("$id 번 명언은 존재하지 않습니다.")
            }


        }
    }
}

fun tryInput() : String{
    return readlnOrNull()!!.trim()
}