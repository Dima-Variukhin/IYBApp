package com.example.iybapp.data

import android.content.Context
import android.util.AttributeSet
import com.example.iybapp.data.BaseViewModel

class CorrectButton : androidx.appcompat.widget.AppCompatButton, EnableView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}