package com.example.valoreseconomicos.util.watcher

import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import com.example.valoreseconomicos.util.formater.RutFormatter

class RutTextWatcher : TextWatcher {

    private var editText: AppCompatEditText? = null

    fun attachEditText(editText: AppCompatEditText) {
        this.editText = editText
        this.editText?.addTextChangedListener(this)
    }

    override fun afterTextChanged(editable: Editable?) {
        editText?.removeTextChangedListener(this)
        val formattedRut = RutFormatter.displayFormat(editable.toString())
        editText?.apply {
            text?.clear()
            append(formattedRut)
            setSelection(formattedRut.length)
        }
        editText?.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }
}