package com.scainitiative.kotlinscainitiativeproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.scainitiative.kotlinscainitiativeproject.database.AppDatabase
import com.scainitiative.kotlinscainitiativeproject.database.Note

class NoteViewModel(app: Application):AndroidViewModel(app) {

    var allNotes : MutableLiveData<List<Note>>

    init {
        allNotes = MutableLiveData()
    }

    fun getAllNoteObserver(): MutableLiveData<List<Note>>{
        return  allNotes
    }


    private fun getAllNotes(){
        val noteDao = AppDatabase.getAppDatabase(getApplication())?.noteDao()
        val noteList = noteDao?.getAllNotes()

        allNotes.postValue(noteList)
    }

    fun deleteNote(note: Note){
        val noteDao = AppDatabase.getAppDatabase(getApplication())?.noteDao()
        noteDao?.delete(note)
        getAllNotes()
    }
    fun createNote(note: Note){
        val noteDao = AppDatabase.getAppDatabase(getApplication())?.noteDao()
        noteDao?.insert(note)
    }
}