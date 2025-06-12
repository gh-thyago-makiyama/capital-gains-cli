package com.capitalgainscli.processor.command

enum class Operation() {
    BUY,
    SELL;

    companion object {
        fun fromString(operation: String): Operation {
            return entries.find { it.name.equals(operation, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid operation: $operation")
        }
    }
}