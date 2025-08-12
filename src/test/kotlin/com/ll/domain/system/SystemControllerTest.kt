package com.ll.domain.system

import com.ll.Runner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class SystemControllerTest {
    @Test
    @DisplayName("종료 테스트")
    fun t1() {
        val result = Runner.run("")

        assertThat(result).contains("프로그램을 종료합니다.")
    }
}