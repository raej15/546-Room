package com.example.roomcomplete

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.asLiveData()
    }

    fun addNote(text: String, title: String) = viewModelScope.launch {
        repo.addNote(Note(text = text,title = title))
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }
}
