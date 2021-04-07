package com.example.notesapp

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val notListesi : LiveData<List<Note>> = noteDao.getAll()

    suspend fun notEkle(note : Note) {
        noteDao.insert(note)
    }
}