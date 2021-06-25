package ru.dariamikhailukova.mynotebook.mvp.presenter.list

import androidx.lifecycle.LiveData
import ru.dariamikhailukova.mynotebook.mvp.model.Note

interface ListPresenter {
    fun deleteAll()
    fun getAllData(): LiveData<List<Note>>
}