package com.ll.domain.wiseSaying.controller

import com.ll.Runner
import com.ll.global.AppConfig
import com.ll.global.SingletonObjects
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test


class WiseSayingControllerTest {

    @BeforeEach
    fun prepare() {
        AppConfig.activateTestMode()
        SingletonObjects.wiseSayingFileRepository.clear()
    }

    @Test
    @DisplayName("명언 등록")
    fun t1() {
        val result = Runner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
        """)

        assertThat(result).contains("명언) ")
        assertThat(result).contains("작가) ")
        assertThat(result).contains("1번 명언이 등록되었습니다.")
    }

    @Test
    @DisplayName("명언 목록")
    fun t2() {
        val result = Runner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            목록
        """)

        assertThat(result).contains("1 / 작자미상 / 현재를 사랑하라.")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
    }

    @Test
    @DisplayName("명언 삭제")
    fun t3() {
        val result = Runner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            삭제?id=1
            삭제?id=1
            목록
        """)

        assertThat(result).contains("1번 명언이 삭제되었습니다.")
        assertThat(result).contains("1번 명언은 존재하지 않습니다.")
        assertThat(result).doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
        assertThat(result).contains("2 / 작자미상 / 과거에 집착하지 마라.")
    }

    @Test
    @DisplayName("명언 수정")
    fun t4() {
        val result = Runner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            수정?id=1
            현재와 자신을 사랑하라.
            홍길동
            목록
        """)

        assertThat(result).contains("1번 명언이 수정되었습니다.")
        assertThat(result).doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
        assertThat(result).contains("1 / 홍길동 / 현재와 자신을 사랑하라.")
    }

    @Test
    @DisplayName("명언 빌드")
    fun t5() {
        val result = Runner.run(
            """
            등록
            현재를 사랑하라.
            작자미상
            등록
            과거에 집착하지 마라.
            작자미상
            빌드
        """)

        assertThat(result).contains("${AppConfig.buildFileName} 파일의 내용이 갱신되었습니다.")
    }
}