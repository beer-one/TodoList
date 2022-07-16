package com.tutorial.todo.application

import org.springframework.data.domain.Page

class PageResult<T>(
    val page: Int,
    val items: List<T>
) {
    constructor(page: Page<T>) : this(page.number, page.toList())
}