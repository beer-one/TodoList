package com.tutorial.todo.presentation.handler

import com.tutorial.todo.application.todo.*
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
    private val queryService: TodoListQueryService
) {

    suspend fun getPage(request: ServerRequest): ServerResponse {
        val page = request.queryParamOrNull("page")?.toIntOrNull() ?: 1
        val pageSize = request.queryParamOrNull("pageSize")?.toIntOrNull() ?: 10


        return ok().body(queryService.getPage(PageRequest.of(page, pageSize)))
            .awaitSingle()

    }

    suspend fun getDetail(request: ServerRequest): ServerResponse {
        val no = request.pathVariable("todoListNo").toInt()

        return ok().body(queryService.getOne(no))
            .awaitSingleOrNull()
            ?: notFound().buildAndAwait()
    }

    suspend fun add(request: ServerRequest): ServerResponse {

        return noContent().buildAndAwait()
    }

    suspend fun edit(request: ServerRequest): ServerResponse {

        return noContent().buildAndAwait()
    }

    suspend fun deleteOne(request: ServerRequest): ServerResponse {

        return noContent().buildAndAwait()
    }
}
