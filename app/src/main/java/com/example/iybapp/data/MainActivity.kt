package com.example.iybapp.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.iybapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var baseViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseViewModel = (application as IYBApp).baseViewModel
        val button = findViewById<CorrectButton>(R.id.actionButton)
        val progressBar = findViewById<CorrectProgressButton>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            baseViewModel.getAction()
        }
        val changeButton = findViewById<CorrectImageButton>(R.id.changeButton)
        changeButton.setOnClickListener {
            baseViewModel.changeActionStatus()
        }

        baseViewModel.observe(this) { state ->
            state.show(progressBar, button, textView, changeButton)
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            baseViewModel.chooseFavorites(isChecked)
        }
    }
}