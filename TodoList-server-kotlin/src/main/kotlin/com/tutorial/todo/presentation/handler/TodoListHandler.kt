package com.tutorial.todo.presentation.handler

import com.tutorial.todo.application.todo.*
import com.tutorial.todo.infrastructure.properties.TestProperties
import com.tutorial.todo.presentation.error.NotFoundException
import com.tutorial.todo.presentation.request.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.reactive.*
import kotlinx.coroutines.reactor.*
import org.slf4j.LoggerFactory
import org.springframework.data.domain.*
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.time.Duration
import java.time.LocalDate
import java.util.concurrent.Executors
import kotlin.random.Random
import kotlin.random.nextInt


@Component
class TodoListHandler(
    private val queryService: TodoListQueryService,
    private val commandService: TodoListCommandService,
    private val testProperties: TestProperties
) {
    suspend fun getByYearMonth(request: ServerRequest): ServerResponse {
        val year = request.queryParamOrNull("year")?.toIntOrNull() ?: LocalDate.now().year
        val month = request.queryParamOrNull("month")?.toIntOrNull() ?: LocalDate.now().monthValue

        return ok().body(queryService.getByYearMonth(year, month))
            .awaitSingle()
    }

    suspend fun getDetail(request: ServerRequest): ServerResponse {
        val no = request.getTodoListNo()

        return queryService.getOne(no).flatMap { ok().bodyValue(it) }
            .awaitSingle()

    }

    suspend fun add(request: ServerRequest): ServerResponse {
        return request.bodyToMono(TodoListAddRequest::class.java)
            .flatMap { commandService.add(it.toCommand()) }
            .flatMap { noContent().build() }
            .awaitSingle()
    }

    suspend fun edit(request: ServerRequest): ServerResponse {
        return request.bodyToMono(TodoListEditRequest::class.java)
            .flatMap {
                commandService.edit(
                    no = request.getTodoListNo(),
                    command = it.toCommand()
                )
            }
            .flatMap { noContent().build() }
            .awaitSingle()
    }

    suspend fun deleteOne(request: ServerRequest): ServerResponse {
        return commandService.delete(request.getTodoListNo())
            .flatMap { noContent().build() }
            .awaitSingle()
    }

    suspend fun test(request: ServerRequest): ServerResponse {
        return ok().bodyValueAndAwait(testProperties)
    }
    private fun ServerRequest.getTodoListNo(): Int = pathVariable("todoListNo").toInt()
}
