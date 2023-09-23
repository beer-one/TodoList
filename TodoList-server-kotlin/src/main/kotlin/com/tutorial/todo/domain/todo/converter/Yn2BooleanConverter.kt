package com.tutorial.todo.domain.todo.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class Yn2BooleanConverter: AttributeConverter<Boolean, String> {
    override fun convertToDatabaseColumn(attribute: Boolean): String {
        return if(attribute) "Y" else "N"
    }

    override fun convertToEntityAttribute(dbData: String): Boolean {
        return dbData == "Y"
    }
}