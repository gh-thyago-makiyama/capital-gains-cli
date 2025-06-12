package com.capitalgainscli.processor.strategy

import com.capitalgainscli.dtos.TaxResult

interface TaxStrategy {
    fun calculateTax(): TaxResult
}