package com.ll

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

object Runner {
    private val originalIn = System.`in`
    private val originalOut = System.out

    fun run(input: String): String {
        val input = input
            .trimIndent()
            .plus("\n종료")

        val outputStream = ByteArrayOutputStream()

        try {
            System.setIn(ByteArrayInputStream(input.toByteArray()))
            System.setOut(
                PrintStream( outputStream )
            )

            App().run()

        } finally {
            System.setIn(originalIn)
            System.setOut(originalOut)
        }

        return outputStream.toString()
            .trim()
            .replace("\r\n", "\n")
    }
}