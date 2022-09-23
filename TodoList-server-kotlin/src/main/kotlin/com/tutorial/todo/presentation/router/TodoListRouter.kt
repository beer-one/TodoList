package com.tutorial.todo.presentation.router

import com.tutorial.todo.presentation.handler.*
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.web.reactive.function.server.*

/**
 * @author Seowon Yun
 */
@Configuration
class TodoListRouter(
    private val handler: TodoListHandler
) {

    @Bean
    fun todoListRoute(): RouterFunction<ServerResponse> {
        return coRouter {
            "/todoList".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("", handler::getPage)
                    POST("", handler::add)

                    "/{todoListNo}".nest {
                        GET("", handler::getDetail)
                        PUT("", handler::edit)
                        DELETE("", handler::deleteOne)
                    }
                }
            }
        }
    }
}
