package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val butunNotlar : List<Note>) : RecyclerView.Adapter<RecyclerViewAdapter.NotViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.not_item, parent, false)

        return NotViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val currentItem = butunNotlar[position]

        holder.notTitle.text = currentItem.noteTitle
        holder.notText.text = currentItem.noteText
    }


    class NotViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val notTitle = itemView.findViewById<TextView>(R.id.not_title_tv)
        val notText = itemView.findViewById<TextView>(R.id.not_text_tv)
    }
}