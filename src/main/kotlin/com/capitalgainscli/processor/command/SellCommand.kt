package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.processor.strategy.LossStrategy
import com.capitalgainscli.processor.strategy.NoTaxStrategy
import com.capitalgainscli.processor.strategy.ProfitWithTaxStrategy
import com.capitalgainscli.utils.Mapper.ZERO
import java.math.BigDecimal
import java.math.RoundingMode

class SellCommand(
    private val input: OperationInput,
    private val processor: CapitalGainsProcessor
) : OperationCommand {
    override fun execute(): TaxResult {
        val sellTotal = input.unitCost.multiply(BigDecimal(input.quantity))
        val costTotal = processor.getAverageCost().multiply(BigDecimal(input.quantity))
        val profit = sellTotal.subtract(costTotal).setScale(2, RoundingMode.HALF_EVEN)

        processor.registerSell(input.quantity)

        val strategy = when {
            profit < ZERO -> LossStrategy(profit.abs(), processor)
            sellTotal <= BigDecimal("20000.00") -> NoTaxStrategy()
            else -> ProfitWithTaxStrategy(profit, processor)
        }

        return strategy.calculateTax()
    }
}
