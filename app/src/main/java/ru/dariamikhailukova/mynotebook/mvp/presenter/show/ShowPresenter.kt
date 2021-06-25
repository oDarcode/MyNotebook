package ru.dariamikhailukova.mynotebook.mvp.presenter.show

import android.text.Editable

interface ShowPresenter {
    //fun readNote(name: String, text: String)
    fun sendEmail(name: String, text: String, date: String)
    fun update(name: String, text: String, date: String)
}