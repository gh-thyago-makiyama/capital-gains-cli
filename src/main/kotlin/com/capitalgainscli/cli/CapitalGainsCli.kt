package com.capitalgainscli.cli

import com.capitalgainscli.processor.CapitalGainsProcessor
import com.capitalgainscli.utils.Mapper

class CapitalGainsCli(
    private val processor: CapitalGainsProcessor
) {
    fun run() {
        generateSequence(::readLineSafe)
            .takeWhile { it.isNotBlank() }
            .map { Mapper.toListOperationDTO(it) }
            .forEach { operations ->
                val result = processor.processOperations(operations)
                println(Mapper.toJsonString(result))
            }
    }

    private fun readLineSafe(): String = readLine() ?: ""
}