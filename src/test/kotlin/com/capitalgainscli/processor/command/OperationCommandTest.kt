package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.processor.CapitalGainsProcessor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import kotlin.test.assertTrue

class OperationCommandTest {
    @Test
    fun `should return BuyCommand for buy operation`() {
        val input = OperationInput("buy", BigDecimal("10.00"), 100)
        val processor = CapitalGainsProcessor()
        val command = OperationCommand.from(input, processor)
        assertTrue(command is BuyCommand)
    }

    @Test
    fun `should return SellCommand for sell operation`() {
        val input = OperationInput("sell", BigDecimal("10.00"), 100)
        val processor = CapitalGainsProcessor()
        val command = OperationCommand.from(input, processor)
        assertTrue(command is SellCommand)
    }

    @Test
    fun `should throw error for unknown operation`() {
        val input = OperationInput("invalid", BigDecimal("10.00"), 100)
        val processor = CapitalGainsProcessor()

        assertThrows<IllegalArgumentException> {
            OperationCommand.from(input, processor)
        }.also {
            assertTrue(it.message?.contains("Invalid operation: invalid") == true)
        }
    }
}
