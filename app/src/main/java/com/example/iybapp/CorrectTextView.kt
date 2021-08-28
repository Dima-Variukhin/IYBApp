package com.example.iybapp

import android.content.Context
import android.util.AttributeSet


class CorrectTextView : androidx.appcompat.widget.AppCompatTextView, BaseViewModel.ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun show(text: String) {

    }
}