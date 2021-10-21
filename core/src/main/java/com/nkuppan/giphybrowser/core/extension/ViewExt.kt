package com.nkuppan.giphybrowser.core.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 *  This extension will allow the user to clear focus and hide the keyboard
 */
fun EditText.clearFocusAndHideKeyboard() {
    clearFocus()
    val input = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.hideSoftInputFromWindow(windowToken, 0)
}
