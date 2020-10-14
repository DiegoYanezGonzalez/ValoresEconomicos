package com.example.valoreseconomicos.util.extentions

import androidx.appcompat.widget.AppCompatEditText
import com.example.valoreseconomicos.util.validator.EmailValidator
import com.example.valoreseconomicos.util.validator.NameValidator
import com.example.valoreseconomicos.util.validator.PassValidator
import com.example.valoreseconomicos.util.validator.RutValidator

fun AppCompatEditText.isValidNameInput(message: String) : Boolean{
    val result = NameValidator.validate(text.toString())
    if(!result){
        this.error = message
        requestFocus()
    }
    return result
}

fun AppCompatEditText.isValidRutInput(message: String) : Boolean{
    val result = RutValidator.validate(text.toString())
    if(!result){
        error = message
        requestFocus()
    }
    return result
}

fun AppCompatEditText.isValidEmailInput(message: String) : Boolean{
    val result = EmailValidator.validate(text.toString())
    if(!result){
        error = message
        requestFocus()
    }
    return result
}

fun AppCompatEditText.isValidPassInput(message: String) : Boolean{
    val result = PassValidator.validate(text.toString())
    if(!result){
        error = message
        requestFocus()
    }
    return result
}

fun AppCompatEditText.isValidConfirmPassInput(message: String, pass: String) : Boolean {
    val result = pass == text.toString()
    if(!result){
        error = message
        requestFocus()
    }
    return result
}