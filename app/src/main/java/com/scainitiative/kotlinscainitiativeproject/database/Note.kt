package com.scainitiative.kotlinscainitiativeproject.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 1,
    @ColumnInfo(name = "note_title") var noteTitle: String,
    @ColumnInfo(name = "note_content") var noteContent: String
)