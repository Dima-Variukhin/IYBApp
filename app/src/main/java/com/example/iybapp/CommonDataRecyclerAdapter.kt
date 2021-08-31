//package com.example.iybapp
//
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.iybapp.core.data.CommonDataModel
//import com.example.iybapp.presentation.CorrectTextView
//
//class CommonDataRecyclerAdapter<E>() :
//    RecyclerView.Adapter<CommonDataRecyclerAdapter<E>.CommonDataViewHolder<E>>() {
//    private val list: List<CommonDataModel<E>> = ArrayList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<E> {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: CommonDataViewHolder<E>, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//
//    inner class CommonDataViewHolder<E>(view: View) : RecyclerView.ViewHolder(view) {
//        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
//        fun bind(model: CommonDataModel<E>) = model.map(textView)
//    }
//}