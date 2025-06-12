package com.capitalgainscli.processor

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.command.OperationCommand
import java.math.BigDecimal
import java.math.RoundingMode
import com.capitalgainscli.utils.Mapper.ZERO

class CapitalGainsProcessor {
    private var totalQuantity = 0
    private var averageCost = ZERO
    private var accumulatedLoss = ZERO

    fun getAverageCost(): BigDecimal = averageCost
    fun getTotalQuantity(): Int = totalQuantity
    fun getAccumulatedLoss(): BigDecimal = accumulatedLoss

    fun processOperations(operations: List<OperationInput>): List<TaxResult> {
        return operations.map { input ->
            val command = OperationCommand.from(input, this)
            command.execute()
        }
    }

    fun updateBuy(quantity: Int, cost: BigDecimal) {
        val totalCost = averageCost.multiply(BigDecimal(totalQuantity)) + cost.multiply(BigDecimal(quantity))
        totalQuantity += quantity
        averageCost = if (totalQuantity > 0)
            totalCost.divide(BigDecimal(totalQuantity), 2, RoundingMode.HALF_EVEN)
        else ZERO
    }

    fun registerSell(quantity: Int) {
        totalQuantity -= quantity
    }

    fun addLoss(loss: BigDecimal) {
        accumulatedLoss = accumulatedLoss.add(loss)
    }

    fun deductLoss(profit: BigDecimal): BigDecimal {
        val profitAfterLoss = profit.subtract(accumulatedLoss).max(ZERO)
        accumulatedLoss = if (profit > accumulatedLoss) ZERO else accumulatedLoss.subtract(profit)
        return profitAfterLoss
    }
}