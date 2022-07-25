package com.tutorial.todo.application.todo

import com.tutorial.todo.domain.todo.entity.*
import java.time.*

/**
 * @author Seowon Yun
 */
data class TodoListSummary(
    val no: Int,
    val title: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val finished: Boolean,
    val ended: Boolean
) {
    companion object {
        fun of(todoList: TodoList, current: LocalDateTime) = TodoListSummary(
            no = todoList.no,
            title = todoList.title,
            startDateTime = todoList.startDateTime,
            endDateTime = todoList.endDateTime,
            finished = todoList.finished,
            ended = current.isAfter(todoList.endDateTime)
        )
    }
}
