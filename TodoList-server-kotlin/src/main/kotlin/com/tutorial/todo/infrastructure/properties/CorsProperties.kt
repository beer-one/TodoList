package com.tutorial.todo.infrastructure.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cors")
data class CorsProperties(
    val origins: String,
    val maxAge: Int
)