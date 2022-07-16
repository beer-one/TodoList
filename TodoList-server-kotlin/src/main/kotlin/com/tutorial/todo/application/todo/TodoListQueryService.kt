package com.tutorial.todo.application.todo

import com.tutorial.todo.application.PageResult
import com.tutorial.todo.domain.todo.repository.*
import com.tutorial.todo.presentation.error.NotFoundException
import org.springframework.data.domain.*
import org.springframework.data.repository.*
import org.springframework.stereotype.*
import reactor.core.publisher.*
import java.time.*

/**
 * @author Seowon Yun
 */
@Service
class TodoListQueryService(
    private val repository: TodoListRepository
) {
    fun getPage(pageable: Pageable): Mono<PageResult<TodoListSummary>> {
        return Mono.fromCallable {
            repository.findAll(pageable)
        }.map { page ->
            val current = LocalDateTime.now()
            PageResult(page.map {
                TodoListSummary.of(it, current)
            })
        }
    }

    fun getOne(no: Int): Mono<TodoListDetail> {
        return Mono.fromCallable {
            repository.findByIdOrNull(no) ?: throw NotFoundException("TodoList", no)
        }.flatMap { data ->
            Mono.just(TodoListDetail.of(data))
        }
    }
}
