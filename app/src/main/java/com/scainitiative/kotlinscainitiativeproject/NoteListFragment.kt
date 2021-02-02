package com.scainitiative.kotlinscainitiativeproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scainitiative.kotlinscainitiativeproject.database.Note
import kotlinx.android.synthetic.main.fragment_note_list.*


class NoteListFragment : Fragment() {

    lateinit var noteAdapter: NoteAdapter
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        floatingActionButton.setOnClickListener { showNote("new") }
       noteListRv.apply {
            layoutManager = LinearLayoutManager(context)
            noteAdapter = NoteAdapter()
            adapter = noteAdapter
//            addItemDecoration(DividerItemDecoration(requireContext(),1))
        }

        val listener :NoteSelectionListener = object : NoteSelectionListener{
            override fun onSelectNote(title: String) {
                showNote(title)
            }

            override fun onDeleteNote(note: Note) {
                viewModel.deleteNote(note)
            }
        }
        viewModel = ViewModelProviders.of(requireActivity()).get(NoteViewModel::class.java)
        val note1 = Note(0,"God is good","Our GOd is awesome")
        viewModel.createNote(note1)
        viewModel.getAllNoteObserver().observe(viewLifecycleOwner, Observer {
            noteAdapter.setListData(ArrayList(it),listener)
            noteAdapter.notifyDataSetChanged()
        })


    }

    private fun showNote(title: String) {
        view?.findNavController()
                ?.navigate(NoteListFragmentDirections.actionNoteListFragmentToShowNoteFragment(title))
    }
}