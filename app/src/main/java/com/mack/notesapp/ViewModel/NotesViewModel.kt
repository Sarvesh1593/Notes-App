package com.mack.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mack.notesapp.Database.NotesDatabase
import com.mack.notesapp.NotesEntity.Notes
import com.mack.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application): AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myDao()
        repository = NotesRepository(dao)
    }

    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()
    fun getHighNotes() : LiveData<List<Notes>> = repository.getHighNotes()

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes():LiveData<List<Notes>> =repository.getAllNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}