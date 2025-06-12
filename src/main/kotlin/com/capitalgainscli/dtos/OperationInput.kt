package com.capitalgainscli.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class OperationInput(
    val operation: String,
    @JsonProperty("unit-cost")
    val unitCost: BigDecimal,
    val quantity: Int
)
