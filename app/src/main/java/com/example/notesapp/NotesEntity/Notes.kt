package com.example.notesapp.NotesEntity


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val title: String,
    val subtitle: String,
    val notes: String,
    val date:String,
    val priority:String
):Parcelable