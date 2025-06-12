package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.stubs.zeroTaxValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class BuyCommandTest {

    private lateinit var processor: CapitalGainsProcessor

    @BeforeEach
    fun setup() {
        processor = CapitalGainsProcessor()
    }

    @Test
    fun `buy command should not produce tax and update average cost`() {
        val buy = OperationInput("buy", BigDecimal("10.00"), 1000)
        val command = BuyCommand(buy, processor)
        val result = command.execute()

        assertEquals(zeroTaxValue, result.tax)
        assertEquals(1000, processor.getTotalQuantity())
        assertEquals(BigDecimal("10.00"), processor.getAverageCost())
    }
}
