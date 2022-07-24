package com.tutorial.todo.infrastructure.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.tutorial.todo.enums.FormatEnum
import com.tutorial.todo.infrastructure.properties.CorsProperties
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
class WebFluxConfig(
    private val properties: CorsProperties,
    private val objectMapper: ObjectMapper
): WebFluxConfigurer {

    @Bean
    fun jsonCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.simpleDateFormat(FormatEnum.DATETIME.pattern)
            builder.serializers(LocalDateSerializer(FormatEnum.DATE.formatter))
            builder.serializers(LocalDateTimeSerializer(FormatEnum.DATETIME.formatter))
        }
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(properties.origins)
            .allowedMethods("GET", "OPTION", "HEAD", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(properties.maxAge.toLong())
    }

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        configurer.defaultCodecs().apply {
            jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
            jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
        }
    }
}