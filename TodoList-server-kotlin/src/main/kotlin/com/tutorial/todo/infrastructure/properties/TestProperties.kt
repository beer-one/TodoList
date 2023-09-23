package com.tutorial.todo.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "test")
data class TestProperties(
    val data: String
)