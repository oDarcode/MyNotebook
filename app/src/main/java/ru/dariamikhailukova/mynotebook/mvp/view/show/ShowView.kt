package ru.dariamikhailukova.mynotebook.mvp.view.show

interface ShowView {
    fun initView()
    fun updateItem()
    fun deleteNote()
    fun showToast(text: Int)
    fun sendIntent(name: String, text: String)
    fun returnToList()
    fun currentNoteId(): Int
}