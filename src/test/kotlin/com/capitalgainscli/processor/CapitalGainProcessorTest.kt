package com.capitalgainscli.processor

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.stubs.zeroTaxValue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class CapitalGainProcessorTest {
    @Test
    fun `processOperations should handle a full buy and sell flow`() {
        val processor = CapitalGainsProcessor()
        val operations = listOf(
            OperationInput("buy", BigDecimal("10.00"), 1000),
            OperationInput("sell", BigDecimal("30.00"), 1000)
        )

        val results = processor.processOperations(operations)

        assertEquals(2, results.size)
        assertEquals(zeroTaxValue, results[0].tax)
        assertEquals(BigDecimal("4000.00"), results[1].tax)
    }
}
