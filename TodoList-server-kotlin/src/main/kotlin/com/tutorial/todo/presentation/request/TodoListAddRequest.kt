package com.tutorial.todo.presentation.request

import com.tutorial.todo.application.todo.*
import java.time.*

/**
 * @author Seowon Yun
 */
data class TodoListAddRequest(
    val title: String,
    val content: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
) {
    fun toCommand() = TodoListAddCommand(
        title = this.title,
        content = this.content,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime
    )
}
