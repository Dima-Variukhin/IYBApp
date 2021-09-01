package com.example.iybapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.iybapp.core.presentation.CommonCommunication
import com.example.iybapp.presentation.CommonUiModel
import com.example.iybapp.presentation.CorrectImageButton
import com.example.iybapp.presentation.CorrectTextView
import com.example.iybapp.presentation.FailedCommonUiModel

class CommonDataRecyclerAdapter<T>(
    private val listener: FavoriteItemClickListener<T>,
    private val communication: CommonCommunication<T>
) :
    RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder<T>>() {
    private val list = ArrayList<CommonUiModel<T>>()

    fun show(data: List<CommonUiModel<T>>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<T> {
        val emptyList = viewType == 0
        val view =
            LayoutInflater.from(parent.context).inflate(
                if (emptyList)
                    R.layout.no_favorite_item
                else
                    R.layout.common_data_item, parent, false
            )

        return if (emptyList) EmptyFavoritesViewHolder(view) else CommonDataViewHolder.Base(
            view,
            listener
        )
    }

    override fun onBindViewHolder(holder: CommonDataViewHolder<T>, position: Int) {
        holder.bind(communication.getList()[position])
    }

    override fun getItemCount() = communication.getList().size

    override fun getItemViewType(position: Int) = when (communication.getList()[position]) {
        is FailedCommonUiModel -> 0
        else -> 1
    }

    fun update() {
        val result = communication.getDiffResult()
        result.dispatchUpdatesTo(this)
    }

    inner class EmptyFavoritesViewHolder<T>(view: View) : CommonDataViewHolder<T>(view)

    interface FavoriteItemClickListener<T> {
        fun change(id: T)
    }

    abstract class CommonDataViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
        open fun bind(model: CommonUiModel<T>) = model.show(textView)

        class Base<T>(view: View, private val listener: FavoriteItemClickListener<T>) :
            CommonDataViewHolder<T>(view) {
            private val iconView = itemView.findViewById<CorrectImageButton>(R.id.changeButton)
            override fun bind(model: CommonUiModel<T>) {
                super.bind(model)
                iconView.setOnClickListener {
                    model.change(listener)
                }
            }
        }
    }
}

