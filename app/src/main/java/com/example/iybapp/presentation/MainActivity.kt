package com.example.iybapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iybapp.IYBApp
import com.example.iybapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var baseViewModel: BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseViewModel = (application as IYBApp).baseViewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.actionFavoriteDataView)
        favoriteDataView.linkWith(baseViewModel)

        baseViewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val quoteViewModel = (application as IYBApp).quoteViewModel
        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteFavoriteDataView)
        quoteFavoriteDataView.linkWith(quoteViewModel)

        quoteViewModel.observe(this) { state ->
            quoteFavoriteDataView.show(state)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter
    }
}