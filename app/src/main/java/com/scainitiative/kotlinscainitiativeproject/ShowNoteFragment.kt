package com.scainitiative.kotlinscainitiativeproject

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scainitiative.kotlinscainitiativeproject.database.AppDatabase
import com.scainitiative.kotlinscainitiativeproject.database.Note
import kotlinx.android.synthetic.main.fragment_show_note.*


class ShowNoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = ShowNoteFragmentArgs.fromBundle(requireArguments())
        when (val title = args.title){
            "new" -> {
                saveButton.setOnClickListener {
                    val note = Note(2,noteTitleEt.text.toString(), noteContentEt.text.toString())
                    createNote(note)
                }
            }
            else ->{
                val currentNote = getNoteByTitle(title)
                val id = currentNote?.id
                noteTitleEt.setText(currentNote?.noteTitle)
                noteContentEt.setText(currentNote?.noteContent)
                saveButton.setOnClickListener {
                    val note = id?.let { it1 -> Note(it1,noteTitleEt.text.toString(), noteContentEt.text.toString()) }
                    if (note != null) {
                        updateNote(note)
                    }
                }
            }
        }


    }
    private fun getNoteByTitle(title: String): Note? {
        val noteDao = AppDatabase.getAppDatabase(Application())?.noteDao()
        return noteDao?.findByTitle(title)
    }

    private fun createNote(note: Note){
        val noteDao = AppDatabase.getAppDatabase(Application())?.noteDao()
        noteDao?.insert(note)
    }

    private fun updateNote(note: Note){
        val noteDao = AppDatabase.getAppDatabase(Application())?.noteDao()
        noteDao?.updateNote(note)
    }
}