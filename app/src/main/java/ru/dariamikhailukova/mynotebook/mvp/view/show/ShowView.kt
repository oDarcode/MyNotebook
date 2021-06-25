package ru.dariamikhailukova.mynotebook.mvp.view.show

import android.text.Editable

interface ShowView {
    fun initView()
    fun updateItem()
    fun deleteNote()
    fun showToast(text: String)
    fun sendIntent(name: String, text: String)
    fun returnToList()
    fun currentNoteId(): Int
}