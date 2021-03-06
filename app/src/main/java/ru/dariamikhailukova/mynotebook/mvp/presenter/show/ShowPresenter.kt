package ru.dariamikhailukova.mynotebook.mvp.presenter.show

import ru.dariamikhailukova.mynotebook.mvp.model.Note

interface ShowPresenter {
    fun sendEmail(name: String, text: String, date: String)
    fun update(name: String, text: String, date: String)
    fun delete(currentNote: Note)
    fun inputCheck(name: String, text: String): Boolean
    fun getDate(): String
}