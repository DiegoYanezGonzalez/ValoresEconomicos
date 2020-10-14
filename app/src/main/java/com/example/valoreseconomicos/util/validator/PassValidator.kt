package com.example.valoreseconomicos.util.validator

object PassValidator {

    const val PASSWORD_VALUES = ".{6,16}"

    fun validate(password: String): Boolean {
        return password.isNotEmpty() && password.matches(PASSWORD_VALUES.toRegex())
    }
}