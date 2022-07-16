package com.tutorial.todo.presentation.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.tutorial.todo.application.todo.*
import java.time.*

/**
 * @author Seowon Yun
 */
data class TodoListAddRequest(
    val title: String,
    val content: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val startDateTime: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val endDateTime: LocalDateTime
) {
    fun toCommand() = TodoListAddCommand(
        title = this.title,
        content = this.content,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime
    )
}
