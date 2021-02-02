package com.scainitiative.kotlinscainitiativeproject

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scainitiative.kotlinscainitiativeproject.database.Note
import kotlinx.android.synthetic.main.note_model.view.*

class NoteViewHolder(itemView: View, private val listener: NoteSelectionListener): RecyclerView.ViewHolder(itemView) {
    fun bindData(note: Note) {
        val noteTitle  = note.noteTitle
        itemView.titleTv.text = noteTitle
        itemView.setOnClickListener { listener.onSelectNote(noteTitle) }
        itemView.deleteBtn.setOnClickListener { listener.onDeleteNote(note) }
    }
}