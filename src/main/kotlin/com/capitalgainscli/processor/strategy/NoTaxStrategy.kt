package com.capitalgainscli.processor.strategy

import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.utils.Mapper.ZERO

class NoTaxStrategy : TaxStrategy {
    override fun calculateTax(): TaxResult = TaxResult(ZERO)
}