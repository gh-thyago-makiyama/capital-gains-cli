package com.capitalgainscli.processor.strategy

import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.stubs.zeroTaxValue
import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class LossStrategyTest {
    @Test
    fun `loss strategy should return 0 tax and accumulate loss`() {
        val processor = CapitalGainsProcessor()
        val strategy = LossStrategy(BigDecimal("500.00"), processor)

        val result = strategy.calculateTax()

        assertEquals(zeroTaxValue, result.tax)
        assertEquals(BigDecimal("500.00"), processor.getAccumulatedLoss())
    }
}