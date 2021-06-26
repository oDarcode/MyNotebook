package ru.dariamikhailukova.mynotebook.mvp.presenter.add

import android.text.TextUtils

import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.mynotebook.R

import ru.dariamikhailukova.mynotebook.mvp.model.Note
import ru.dariamikhailukova.mynotebook.mvp.view.add.AddFragment
import ru.dariamikhailukova.mynotebook.mvp.view.add.AddView
import ru.dariamikhailukova.mynotebook.viewmodel.NoteViewModel

class AddFragmentPresenter(_view: AddFragment): AddPresenter {
    private var view: AddView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    //добавление нового элемента в бд
    override fun insertDataToDatabase(name: String, text: String, date: String) {
        if (inputCheck(name, text, date)){
            val note = Note(0, name, text, (date))

            mNoteViewModel.addNote(note)
            view.showToast(R.string.successfully)
            view.returnToList()
        }else{
            view.showToast(R.string.fill_all)
        }
    }

    //все ли строки заполнены
    override fun inputCheck(name: String, text: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(date))
    }

}