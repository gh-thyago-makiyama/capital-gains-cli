package com.capitalgainscli.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal

data class TaxResult(
    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "0.00")
    val tax: BigDecimal
)