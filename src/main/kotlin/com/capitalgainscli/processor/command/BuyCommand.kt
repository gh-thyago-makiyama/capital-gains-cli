package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.utils.Mapper.ZERO

class BuyCommand(
    private val input: OperationInput,
    private val processor: CapitalGainsProcessor
) : OperationCommand {
    override fun execute(): TaxResult {
        processor.updateBuy(input.quantity, input.unitCost)
        return TaxResult(ZERO)
    }
}