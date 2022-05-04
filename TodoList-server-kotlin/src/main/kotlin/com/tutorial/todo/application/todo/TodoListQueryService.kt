package com.tutorial.todo.application.todo

import com.tutorial.todo.domain.todo.repository.*
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
    fun getPage(pageable: Pageable): Mono<Page<TodoListSummary>> {
        return Mono.fromCallable {
            repository.findAll(pageable)
        }.map { page ->
            val current = LocalDateTime.now()
            page.map {
                TodoListSummary.of(it, current)
            }
        }
    }

    fun getOne(no: Int): Mono<TodoListDetail> {
        return Mono.fromCallable {
            repository.findByIdOrNull(no)
        }.flatMap { data ->
            data?.let { Mono.just(TodoListDetail.of(it)) }
                ?: Mono.empty()
        }
    }
}
