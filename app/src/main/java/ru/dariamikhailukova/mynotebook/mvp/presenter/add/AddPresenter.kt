package ru.dariamikhailukova.mynotebook.mvp.presenter.add

interface AddPresenter {
    fun insertDataToDatabase(name: String, text: String, date: String)
    fun inputCheck(name: String, text: String, date: String): Boolean
}