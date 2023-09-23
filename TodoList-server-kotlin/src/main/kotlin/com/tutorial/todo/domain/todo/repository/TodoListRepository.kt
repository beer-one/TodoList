package com.tutorial.todo.domain.todo.repository

import com.tutorial.todo.domain.todo.entity.*
import org.springframework.data.jpa.repository.*
import java.time.LocalDateTime

interface TodoListRepository: JpaRepository<TodoList, Int> {
    fun findByStartDateTimeBetweenOrEndDateTimeBetween(startDateTimeStart: LocalDateTime, startDateTimeEnd: LocalDateTime, endDateTimeStart: LocalDateTime, endDateTimeEnd: LocalDateTime): List<TodoList>
}
