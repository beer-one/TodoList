package com.tutorial.todo.enums

import java.time.format.DateTimeFormatter

enum class FormatEnum(val pattern: String) {
    DATE("yyyy-MM-dd"),
    DATETIME("yyyy-MM-dd HH:mm:ss");

    val formatter = DateTimeFormatter.ofPattern(pattern)
}