package com.tutorial.todo.infrastructure.configuration

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.tutorial.todo.enums.FormatEnum
import com.tutorial.todo.infrastructure.properties.CorsProperties
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class CorsConfig(
    private val properties: CorsProperties
): WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(properties.origins)
            .allowedMethods("GET", "OPTION", "HEAD", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(properties.maxAge.toLong())
    }
}