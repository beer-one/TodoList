package com.tutorial.todo.domain.todo.entity

import com.tutorial.todo.application.todo.*
import java.time.*
import javax.persistence.*
import kotlin.jvm.Transient

/**
 * @author Seowon Yun
 */
@Entity
@Table(name = "TODO_LIST")
data class TodoList(
    @Id
    @Column(name = "todo_list_no")
    val no: Int = 0
) {
    @Column(name = "title")
    var title: String = ""
        private set

    @Column(name = "content")
    var content: String = ""
        private set

    @Column(name = "start_ymdt")
    var startDateTime: LocalDateTime = LocalDateTime.MIN
        private set

    @Column(name = "end_ymdt")
    var endDateTime: LocalDateTime = LocalDateTime.MAX
        private set

    @Column(name = "finish_yn")
    var finished: Boolean = false

    fun edit(command: TodoListEditCommand) {
        title = command.title
        content = command.content
        startDateTime = command.startDateTime
        endDateTime = command.endDateTime
        finished = command.finished
    }

    companion object {
        fun create(command: TodoListAddCommand) : TodoList {
            return TodoList().apply {
                title = command.title
                content = command.content
                startDateTime = command.startDateTime
                endDateTime = command.endDateTime
            }
        }
    }
}
