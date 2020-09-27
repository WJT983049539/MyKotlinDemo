package com.rcdz.mykotlindemo.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rcdz.mykotlindemo.R
import com.rcdz.mykotlindemo.model.bean.UserDataLoadMore

class RecyclerViewAdapter(private val loadMoreList: List<UserDataLoadMore>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = loadMoreList[position].userName
    }

    override fun getItemCount(): Int {
        return loadMoreList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

}
