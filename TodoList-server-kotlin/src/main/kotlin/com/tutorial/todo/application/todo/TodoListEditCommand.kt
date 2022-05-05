package com.tutorial.todo.application.todo

import java.time.*

/**
 * @author Seowon Yun
 */
data class TodoListEditCommand(
    val title: String,
    val content: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val finished: Boolean
)
