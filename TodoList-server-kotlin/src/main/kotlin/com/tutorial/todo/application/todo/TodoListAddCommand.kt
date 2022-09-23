package com.tutorial.todo.application.todo

import java.time.*


data class TodoListAddCommand(
    val title: String,
    val content: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)
