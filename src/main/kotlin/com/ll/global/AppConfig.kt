package com.ll.global

object AppConfig {

    private val _baseDbPath = "db"
    private val _dbPath = "$_baseDbPath/wiseSaying"
    private val _buildFileName = "data.json"

    private var _mode = ActivateMode.DEV

    val dBPath: String
        get() = _dbPath

    val buildFileName: String
        get() = _buildFileName

    fun activateDevMode() {
        _mode = ActivateMode.DEV
    }

    fun activateTestMode() {
        _mode = ActivateMode.TEST
    }

    val mode : ActivateMode
        get() = _mode
}