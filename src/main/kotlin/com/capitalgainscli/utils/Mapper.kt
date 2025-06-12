package com.capitalgainscli.utils

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.dtos.TaxResult
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.math.BigDecimal

object Mapper {
    private val mapper = jacksonObjectMapper().apply {
        enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN)
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

        val module = SimpleModule().apply {
            addSerializer(BigDecimal::class.java, BigDecimalTwoDecimalSerializer())
        }
        registerModule(module)
    }

    fun toListOperationDTO(input: String): List<OperationInput> {
        return mapper.readValue(input)
    }

    fun toJsonString(operations: List<TaxResult>): String {
        return mapper.writeValueAsString(operations)
    }

    val ZERO: BigDecimal = BigDecimal.ZERO.setScale(2)
}

class BigDecimalTwoDecimalSerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeNumber(value.setScale(2, BigDecimal.ROUND_HALF_EVEN))
    }
}