package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.NotesDao.NotesDao
import com.example.notesapp.NotesEntity.Notes

class NotesRepository(val Dao : NotesDao) {

    fun getAllNotes():LiveData<List<Notes>> {
        return Dao.getNotes()
    }

    fun insertNotes(notes: Notes){
        return Dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int){
        return Dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        return Dao.updateNotes(notes)
    }

}