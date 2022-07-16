package com.tutorial.todo.presentation.error

import java.lang.RuntimeException

class NotFoundException(private val entity: String, private val index: Int) : RuntimeException() {
    override val message: String
        get() = "$entity is not found, index: $index"
}