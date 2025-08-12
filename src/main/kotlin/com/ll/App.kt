package com.ll

import com.ll.domain.wiseSaying.controller.WiseSayingController
import com.ll.domain.wiseSaying.enums.CommandType
import com.ll.domain.wiseSaying.repository.WiseSayingRepository
import com.ll.domain.wiseSaying.service.WiseSayingService
import com.ll.global.CommandProcessor
import com.ll.standard.util.InputHelper

class App {

    fun run() {
        val wiseSayingRepository = WiseSayingRepository()
        val wiseSayingService = WiseSayingService(wiseSayingRepository)
        val wiseSayingController = WiseSayingController(wiseSayingService)

        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")

            var cp : CommandProcessor?

            try {
                cp = CommandProcessor(InputHelper.fillText())
            } catch (ex : IllegalArgumentException) {
                println("유효하지 않은 입력입니다 다시 시도해주세요")
                continue
            }

            when (cp.command) {
                CommandType.등록 -> wiseSayingController.createOne()
                CommandType.목록 -> wiseSayingController.listUp()
                CommandType.삭제 -> wiseSayingController.deleteOne(cp)
                CommandType.수정 -> wiseSayingController.editOne(cp)
                CommandType.빌드 -> wiseSayingController.build()
                CommandType.종료 -> return
            }
        }
    }
}