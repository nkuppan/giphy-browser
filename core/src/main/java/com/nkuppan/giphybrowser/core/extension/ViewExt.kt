package com.nkuppan.giphybrowser.core.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 *  This extension will allow the user to clear focus and hide the keyboard
 */
fun EditText.clearFocusAndHideKeyboard() {
    clearFocus()
    val input = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Extension method to show snackbar using the view
 */
fun View.showSnackBarMessage(@StringRes stringRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, stringRes, length).show()
}
