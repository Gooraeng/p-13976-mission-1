package com.ll

import com.ll.domain.wiseSaying.enums.CommandType
import com.ll.global.CommandProcessor
import com.ll.global.SingletonObjects
import com.ll.standard.util.InputHelper

class App {

    fun run() {
        val wiseSayingController = SingletonObjects.wiseSayingController
        val systemController = SingletonObjects.systemController

        println("== 명언 앱 ==")

        while (true) {
            print("명령) ")

            try {
                val cp = CommandProcessor(InputHelper.fillText())

                when (cp.command) {
                    CommandType.등록 -> wiseSayingController.createOne()
                    CommandType.목록 -> wiseSayingController.listUp(cp)
                    CommandType.삭제 -> wiseSayingController.deleteOne(cp)
                    CommandType.수정 -> wiseSayingController.editOne(cp)
                    CommandType.빌드 -> wiseSayingController.build()
                    CommandType.종료 -> {
                        systemController.terminate()
                        break
                    }
                }
            } catch (ex : IllegalArgumentException) {
                val exMessage = ex.message ?: "유효하지 않은 입력입니다. 다시 시도해주세요."

                println(exMessage)
            }
        }
    }
}