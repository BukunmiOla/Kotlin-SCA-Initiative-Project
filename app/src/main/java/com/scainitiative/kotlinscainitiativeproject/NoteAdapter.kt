package com.scainitiative.kotlinscainitiativeproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scainitiative.kotlinscainitiativeproject.database.Note

class NoteAdapter():
    RecyclerView.Adapter<NoteViewHolder>() {
    private lateinit var noteListener : NoteSelectionListener

     private lateinit var items : ArrayList<Note>

    fun setListData(data:ArrayList<Note>,listener: NoteSelectionListener){
        this.items = data
        this.noteListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note_model, parent, false)
        return NoteViewHolder(itemView,noteListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}