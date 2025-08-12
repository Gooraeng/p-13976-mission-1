package com.ll.global

object AppConfig {
    private val _baseDbPath = "db"
    private val _dbPath = "$_baseDbPath/wiseSaying/"
    private val _buildFileName = "data.json"
    private val _buildFilePath = "$_dbPath/$_buildFileName/"

    val dBPath: String
        get() = _dbPath

    val buildFileName: String
        get() = _buildFileName

    val buildFilePath: String
        get() = _buildFilePath
}