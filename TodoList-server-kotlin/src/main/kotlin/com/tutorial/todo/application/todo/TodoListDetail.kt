package com.tutorial.todo.application.todo

import com.tutorial.todo.domain.todo.entity.*
import java.time.*

/**
 * @author Seowon Yun
 */
data class TodoListDetail(
    val no: Int,
    val title: String,
    val content: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val finished: Boolean,
    val ended: Boolean
) {
    companion object {
        fun of(todoList: TodoList) = TodoListDetail(
            no = todoList.no,
            title = todoList.title,
            content = todoList.content,
            startDateTime = todoList.startDateTime,
            endDateTime = todoList.endDateTime,
            finished = todoList.finished,
            ended = todoList.endDateTime.isAfter(LocalDateTime.now())
        )
    }
}
