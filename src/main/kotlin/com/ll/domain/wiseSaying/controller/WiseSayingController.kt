package com.ll.domain.wiseSaying.controller

import com.ll.global.AppConfig
import com.ll.global.CommandProcessor
import com.ll.global.SingletonObjects
import com.ll.standard.util.InputHelper

class WiseSayingController () {
    private val wiseSayingService = SingletonObjects.wiseSayingService

    fun createOne() {
        print("명언) ")
        val content = InputHelper.fillText()
        print("작가) ")
        val author = InputHelper.fillText()

        val wiseSaying = wiseSayingService.addNewWiseSaying(content, author)

        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun listUp(command : CommandProcessor) {
        var keywordType : String? = null
        var keyword : String? = null

        // 쿼리 입력이 있었는지 확인하고
        // 필요한 파라미터들을 검사함
        // page 반영 시 수정 예정
        command.query?.let {
            keywordType = command.getItemFromKey("keywordType")
            keyword = command.getItemFromKey("keyword")

            if ((keywordType != null && keyword == null) ||
                (keywordType == null && keyword != null)) throw IllegalArgumentException()

            println("-----------------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("-----------------------------")
        }

        println("번호 / 작가 / 명언")
        println("-----------------------------")

        wiseSayingService.getListOfQueriedWiseSayings(keywordType, keyword)
            .forEach { println(it) }
    }

    fun deleteOne(command : CommandProcessor) {
        val id = findId(command)

        val success = wiseSayingService.removeWiseSayingById(id)

        if (success) {
            println("${id}번 명언이 삭제되었습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }

    fun editOne(command : CommandProcessor) {
        val id = findId(command)

        val wiseSaying = wiseSayingService.findWiseSayingById(id)

        wiseSaying?.let {
            println("명언(기존) : ${it.content}")
            print("명언 : ")
            val newContent = InputHelper.fillText()

            println("작가(기존) : ${it.author}")
            print("작가 : ")
            val newAuthor = InputHelper.fillText()

            wiseSayingService.modifyWiseSaying(wiseSaying, newAuthor, newContent)
            println("${id}번 명언이 수정되었습니다.")
        } ?: run {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }

    fun build() {
        wiseSayingService.buildFromCurrentWiseSayings()
        println("${AppConfig.buildFileName} 파일의 내용이 갱신되었습니다.")
    }

    private fun findId(command : CommandProcessor) : Int {
        return requireNotNull(Integer.parseInt(command.getItemFromKey("id"))) {
            "id를 찾을 수 없습니다."
        }
    }


}