package com.tutorial.todo.application.todo

import com.tutorial.todo.domain.todo.repository.*
import com.tutorial.todo.presentation.error.NotFoundException
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
    fun getByYearMonth(year: Int, month: Int): Mono<List<TodoListSummary>> {
        return Mono.fromCallable {
            val (startDateTime, endDateTime) = LocalDate.of(year, month, 1).let {
                LocalDateTime.of(it, LocalTime.MIN) to LocalDateTime.of(it.plusMonths(1), LocalTime.MIN).minusSeconds(1)
            }
            repository.findByStartDateTimeBetweenOrEndDateTimeBetween(startDateTime, endDateTime, startDateTime, endDateTime)
        }.map { list ->
            val current = LocalDateTime.now()
            list.map {
                TodoListSummary.of(it, current)
            }
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
