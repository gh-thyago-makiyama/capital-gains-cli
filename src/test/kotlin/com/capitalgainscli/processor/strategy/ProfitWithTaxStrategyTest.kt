package com.capitalgainscli.processor.strategy

import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.stubs.zeroTaxValue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class ProfitWithTaxStrategyTest {

    @Test
    fun `should apply 20 percent tax after discounting loss`() {
        val processor = CapitalGainsProcessor()
        processor.addLoss(BigDecimal("1000.00"))

        val strategy = ProfitWithTaxStrategy(BigDecimal("5000.00"), processor)
        val result = strategy.calculateTax()

        assertEquals(BigDecimal("800.00"), result.tax)
        assertEquals(zeroTaxValue, processor.getAccumulatedLoss())
    }

    @Test
    fun `should not apply tax if loss is greater than profit`() {
        val processor = CapitalGainsProcessor()
        processor.addLoss(BigDecimal("6000.00"))

        val strategy = ProfitWithTaxStrategy(BigDecimal("5000.00"), processor)
        val result = strategy.calculateTax()

        assertEquals(zeroTaxValue, result.tax)
        assertEquals(BigDecimal("1000.00"), processor.getAccumulatedLoss())
    }
}
