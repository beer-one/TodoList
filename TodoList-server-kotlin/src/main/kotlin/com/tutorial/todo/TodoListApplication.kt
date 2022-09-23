package com.tutorial.todo

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.boot.context.properties.*
import org.springframework.web.reactive.config.*



@EnableWebFlux
@ConfigurationPropertiesScan
@SpringBootApplication
class TodoListApplication

fun main(args: Array<String>) {
    runApplication<TodoListApplication>(*args)
}
