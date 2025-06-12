package com.capitalgainscli.processor.strategy

import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.stubs.zeroTaxValue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NoTaxStrategyTest {
    @Test
    fun `no tax strategy should always return 0`() {
        val strategy = NoTaxStrategy()
        val result: TaxResult = strategy.calculateTax()
        assertEquals(zeroTaxValue, result.tax)
    }
}
