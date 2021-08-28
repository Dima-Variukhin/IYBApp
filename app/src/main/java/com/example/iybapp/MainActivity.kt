package com.example.iybapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var baseViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseViewModel = (application as IYBApp).baseViewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            baseViewModel.getAction()
        }
        val changeButton = findViewById<ImageButton>(R.id.changeButton)
        changeButton.setOnClickListener {
            baseViewModel.changeActionStatus()
        }

        baseViewModel.observe(this) { state ->
            state.show(
                object : BaseViewModel.ShowView {
                    override fun show(show: Boolean) {
                        progressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
                    }
                },
                object : BaseViewModel.EnableView {
                    override fun enable(enable: Boolean) {
                        button.isEnabled = enable
                    }
                },

                object : BaseViewModel.ShowText {
                    override fun show(text: String) {
                        textView.text = text
                    }
                },
                object : BaseViewModel.ShowImage {
                    override fun show(id: Int) {
                        changeButton.setImageResource(id)
                    }
                }
            )
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            baseViewModel.chooseFavorites(isChecked)
        }
    }
}