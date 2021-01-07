package com.scainitiative.kotlinscainitiativeproject

import com.scainitiative.kotlinscainitiativeproject.database.Note

interface NoteSelectionListener {
    fun onSelectNote(title: String)
    fun onDeleteNote(note: Note)
}