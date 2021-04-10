package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository : NoteRepository
    val butunNotlar : LiveData<List<Note>>

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(noteDao)
        butunNotlar = noteRepository.notListesi
    }


    fun notEkle(note : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.notEkle(note)
        }
    }
}