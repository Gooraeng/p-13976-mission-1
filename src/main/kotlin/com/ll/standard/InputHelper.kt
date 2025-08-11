package com.ll.standard

class InputHelper {
    companion object {
        fun fillText() : String {
            return readlnOrNull()!!.trim()
        }
    }
}