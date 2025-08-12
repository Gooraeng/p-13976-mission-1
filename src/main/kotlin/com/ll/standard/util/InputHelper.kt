package com.ll.standard.util

class InputHelper {
    companion object {
        fun fillText() : String {
            return readlnOrNull()!!.trim()
        }
    }
}