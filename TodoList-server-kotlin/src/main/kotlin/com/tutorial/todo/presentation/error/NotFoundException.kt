package com.tutorial.todo.presentation.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(private val entity: String, private val index: Int) : RuntimeException() {
    override val message: String
        get() = "$entity is not found, index: $index"
}