package com.tutorial.todo.presentation.handler

import com.tutorial.todo.application.todo.*
import com.tutorial.todo.presentation.request.*
import kotlinx.coroutines.reactive.*
import kotlinx.coroutines.reactor.*
import org.springframework.data.domain.*
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*

/**
 * @author Seowon Yun
 */
@Component
class TodoListHandler(
    private val queryService: TodoListQueryService,
    private val commandService: TodoListCommandService
) {

    suspend fun getPage(request: ServerRequest): ServerResponse {
        val page = request.queryParamOrNull("page")?.toIntOrNull() ?: 1
        val pageSize = request.queryParamOrNull("pageSize")?.toIntOrNull() ?: 10

        return ok().body(queryService.getPage(PageRequest.of(page, pageSize)))
            .awaitSingle()
    }

    suspend fun getDetail(request: ServerRequest): ServerResponse {
        val no = request.getTodoListNo()

        return ok().body(queryService.getOne(no))
            .awaitSingleOrNull()
            ?: notFound().buildAndAwait()
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
            .onErrorResume { notFound().build() }
            .awaitSingle()
    }

    suspend fun deleteOne(request: ServerRequest): ServerResponse {
        return commandService.delete(request.getTodoListNo())
            .flatMap { noContent().build() }
            .onErrorResume { notFound().build() }
            .awaitSingle()
    }

    private fun ServerRequest.getTodoListNo(): Int = pathVariable("todoListNo").toInt()
}
