package com.example.iybapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.iybapp.CommonDataRecyclerAdapter
import com.example.iybapp.IYBApp
import com.example.iybapp.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CommonDataRecyclerAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val baseViewModel = (application as IYBApp).baseViewModel
        val actionCommunication = (application as IYBApp).actionCommunication
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
        val observer: (t: List<CommonUiModel<String>>) -> Unit = { _ ->
            adapter.update()
        }
        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<String> {
            override fun change(id: String) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    val position = baseViewModel.changeItemStatus(id)
                    adapter.update(Pair(false, position))
                }.show()
            }
        }, actionCommunication)

        recyclerView.adapter = adapter

        baseViewModel.observeList(this, observer)
        baseViewModel.getItemList()


    }

}