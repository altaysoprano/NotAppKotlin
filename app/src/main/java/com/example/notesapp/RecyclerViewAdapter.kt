package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.NotViewHolder>() {

    private var butunNotlar: List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.not_item, parent, false)

        return NotViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return butunNotlar.size
    }

    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val currentItem = butunNotlar[position]

        holder.notTitle.text = currentItem.noteTitle
        holder.notText.text = currentItem.noteText
    }

    fun listeyiGuncelle(notlar : List<Note>) {
        butunNotlar = notlar
        notifyDataSetChanged()
    }

    class NotViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val notTitle = itemView.findViewById<TextView>(R.id.not_title_tv)
        val notText = itemView.findViewById<TextView>(R.id.not_text_tv)
    }
}