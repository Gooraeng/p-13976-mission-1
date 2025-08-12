package com.ll.global

import com.ll.domain.system.SystemController
import com.ll.domain.wiseSaying.controller.WiseSayingController
import com.ll.domain.wiseSaying.repository.WiseSayingFileRepository
import com.ll.domain.wiseSaying.repository.WiseSayingMemoryRepository
import com.ll.domain.wiseSaying.repository.WiseSayingRepository
import com.ll.domain.wiseSaying.service.WiseSayingService

/*
* 싱글톤 기반 공용 객체 모음
* */
object SingletonObjects {

    val wiseSayingController by lazy { WiseSayingController() }
    val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingRepository : WiseSayingRepository by lazy { WiseSayingMemoryRepository() }
    val wiseSayingFileRepository by lazy { WiseSayingFileRepository() }
    val systemController by lazy { SystemController() }
}