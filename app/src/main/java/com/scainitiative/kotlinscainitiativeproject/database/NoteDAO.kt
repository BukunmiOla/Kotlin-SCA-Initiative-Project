package com.scainitiative.kotlinscainitiativeproject.database

import androidx.room.*

@Dao
interface NoteDAO {
    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE note_title LIKE :title  LIMIT 1")
    fun findByTitle(title: String): Note

    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun updateNote(note: Note)
}