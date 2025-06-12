package com.capitalgainscli.processor.strategy

import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.utils.Mapper.ZERO
import java.math.BigDecimal

class LossStrategy(
    private val loss: BigDecimal,
    private val processor: CapitalGainsProcessor
) : TaxStrategy {
    override fun calculateTax(): TaxResult {
        processor.addLoss(loss)
        return TaxResult(ZERO)
    }
}
