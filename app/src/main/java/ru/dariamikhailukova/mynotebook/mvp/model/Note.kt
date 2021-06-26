package ru.dariamikhailukova.mynotebook.mvp.model

import android.os.Parcelable
import androidx.room.*
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var text: String,
    var date: String
): Parcelable