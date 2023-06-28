package com.mack.notesapp.Repository

import androidx.lifecycle.LiveData
import com.mack.notesapp.NotesDao.NotesDao
import com.mack.notesapp.NotesEntity.Notes

class NotesRepository(val Dao : NotesDao) {

    fun getAllNotes():LiveData<List<Notes>> {
        return Dao.getNotes()
    }

    fun getLowNotes() : LiveData<List<Notes>> = Dao.getLowNotes()
    fun getMediumNotes() : LiveData<List<Notes>> = Dao.getMediumNotes()
    fun getHighNotes() : LiveData<List<Notes>> = Dao.getHighNotes()



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