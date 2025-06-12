package com.capitalgainscli.stubs

import com.capitalgainscli.dtos.TaxResult
import com.capitalgainscli.utils.Mapper.ZERO

val zeroTaxValue = TaxResult(ZERO).tax
