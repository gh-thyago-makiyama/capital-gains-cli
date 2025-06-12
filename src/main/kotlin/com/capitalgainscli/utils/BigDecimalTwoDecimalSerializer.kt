package com.capitalgainscli.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal

class BigDecimalTwoDecimalSerializer : JsonSerializer<BigDecimal>() {
    override fun serialize(value: BigDecimal, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeNumber(value.setScale(2, BigDecimal.ROUND_HALF_EVEN))
    }
}
