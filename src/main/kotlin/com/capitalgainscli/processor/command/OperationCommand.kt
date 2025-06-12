package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.processor.command.Operation
import com.capitalgainscli.processor.command.Operation.BUY
import com.capitalgainscli.processor.command.Operation.SELL

interface OperationCommand {
    fun execute(): TaxResult

    companion object {
        fun from(input: OperationInput, processor: CapitalGainsProcessor): OperationCommand {
            val operation = Operation.Companion.fromString(input.operation)
            return when (operation) {
                BUY -> BuyCommand(input, processor)
                SELL -> SellCommand(input, processor)
            }
        }
    }
}