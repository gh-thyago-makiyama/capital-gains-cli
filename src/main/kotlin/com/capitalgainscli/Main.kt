package com.capitalgainscli

import com.capitalgainscli.cli.CapitalGainsCli
import com.capitalgainscli.processor.CapitalGainsProcessor

fun main() {
    CapitalGainsCli(CapitalGainsProcessor()).run()
}
