package pl.mftau.mftau.view.ui

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class ClearErrorTextWatcher(val layoutToClear: TextInputLayout): TextWatcher {

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        layoutToClear.isErrorEnabled = false
    }

    override fun afterTextChanged(p0: Editable?) {}
}