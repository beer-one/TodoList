package com.tutorial.todo.infrastructure.configuration

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.tutorial.todo.enums.FormatEnum
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class WebFluxConfig {
    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializers(LocalDateSerializer(FormatEnum.DATE.formatter))
            builder.serializers(LocalDateTimeSerializer(FormatEnum.DATETIME.formatter))
        }
    }
}