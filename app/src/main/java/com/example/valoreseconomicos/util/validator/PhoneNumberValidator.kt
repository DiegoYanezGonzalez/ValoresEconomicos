package com.example.valoreseconomicos.util.validator

import com.example.valoreseconomicos.util.ConstantValues.EMPTY_STRING
import com.example.valoreseconomicos.util.ConstantValues.PLUS_VALUE
import com.example.valoreseconomicos.util.ConstantValues.SPACE_STRING


object PhoneNumberValidator {
    private const val NUMBER_VALUES = "[0-9]+"
    private const val MIN_LENGTH_NUMBER = 8
    private const val MAX_LENGTH_NUMBER = 11

    fun validatePhone(phone: String): Boolean {
        val validatePhone =
            phone.replace(PLUS_VALUE, EMPTY_STRING).replace(SPACE_STRING, EMPTY_STRING)
        return (validatePhone.isNotEmpty()
                && validatePhone.matches(NUMBER_VALUES.toRegex())
                && validatePhoneLength(validatePhone))
    }

    private fun validatePhoneLength(validatePhone: String): Boolean {
        return validatePhone.length in (MIN_LENGTH_NUMBER)..MAX_LENGTH_NUMBER
    }
}