package com.tutorial.todo.domain.todo.repository

import com.tutorial.todo.domain.todo.entity.*
import org.springframework.data.domain.*
import org.springframework.data.jpa.repository.*

/**
 * @author Seowon Yun
 */
interface TodoListRepository: JpaRepository<TodoList, Int> {

}
