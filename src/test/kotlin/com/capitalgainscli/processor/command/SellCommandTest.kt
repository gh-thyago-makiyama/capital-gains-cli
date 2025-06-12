package com.capitalgainscli.processor.command

import com.capitalgainscli.dtos.OperationInput
import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.stubs.zeroTaxValue
import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SellCommandTest {

    private lateinit var processor: CapitalGainsProcessor

    @BeforeEach
    fun setup() {
        processor = CapitalGainsProcessor()
    }

    @Test
    fun `sell with loss should not produce tax and accumulate loss`() {
        val processor = CapitalGainsProcessor()
        processor.updateBuy(1000, BigDecimal("10.00"))

        val sell = OperationInput("sell", BigDecimal("5.00"), 1000)
        val command = SellCommand(sell, processor)
        val result = command.execute()

        assertEquals(zeroTaxValue, result.tax)
        assertEquals(BigDecimal("5000.00"), processor.getAccumulatedLoss())
    }

    @Test
    fun `sell with total less than 20000 should not be taxed`() {
        val processor = CapitalGainsProcessor()
        processor.updateBuy(100, BigDecimal("10.00"))

        val sell = OperationInput("sell", BigDecimal("15.00"), 100)
        val command = SellCommand(sell, processor)
        val result = command.execute()

        assertEquals(zeroTaxValue, result.tax)
    }

    @Test
    fun `sell with profit should apply tax minus accumulated loss`() {
        val processor = CapitalGainsProcessor()
        processor.updateBuy(1000, BigDecimal("10.00"))
        processor.addLoss(BigDecimal("1000.00"))

        val sell = OperationInput("sell", BigDecimal("21.00"), 1000)
        val command = SellCommand(sell, processor)
        val result = command.execute()

        assertEquals(BigDecimal("2000.00"), result.tax)
        assertEquals(zeroTaxValue, processor.getAccumulatedLoss())
    }

    @Test
    fun `sell with profit and no loss should be taxed fully`() {
        val processor = CapitalGainsProcessor()
        processor.updateBuy(1000, BigDecimal("10.00"))

        val sell = OperationInput("sell", BigDecimal("20.01"), 1000)
        val command = SellCommand(sell, processor)
        val result = command.execute()

        assertEquals(BigDecimal("2002.00"), result.tax)
    }
}