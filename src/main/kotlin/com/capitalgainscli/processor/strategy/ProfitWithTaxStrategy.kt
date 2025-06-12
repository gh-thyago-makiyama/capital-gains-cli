package com.capitalgainscli.processor.strategy

import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.CapitalGainsProcessor
import java.math.BigDecimal
import java.math.RoundingMode

class ProfitWithTaxStrategy(
    private val profit: BigDecimal,
    private val processor: CapitalGainsProcessor
) : TaxStrategy {
    override fun calculateTax(): TaxResult {
        val profitAfterLoss = processor.deductLoss(profit)
        val tax = profitAfterLoss.multiply(BigDecimal("0.20")).setScale(2, RoundingMode.HALF_EVEN)
        return TaxResult(tax)
    }
}