package com.rcdz.mykotlindemo.model.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rcdz.mykotlindemo.R

class PictureAdapter(private val pictures: ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<PictureAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return ViewHolder(view)
    }

    //   /storage/emulated/0/temp/0.5437442147590474.jpg
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val options = RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
            .centerCrop()
        Glide.with(context).load(pictures[position]).apply(options).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return pictures.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
    fun AddData(data: List<String>) {
        pictures.addAll(data)
        notifyDataSetChanged()
    }
    fun ClearData(data: List<String>) {
        pictures.clear()
    }
}