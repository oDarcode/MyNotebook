package ru.dariamikhailukova.mynotebook.mvp.presenter.show

import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.mynotebook.R
import ru.dariamikhailukova.mynotebook.mvp.model.Note
import ru.dariamikhailukova.mynotebook.mvp.view.show.ShowFragment
import ru.dariamikhailukova.mynotebook.mvp.view.show.ShowView
import ru.dariamikhailukova.mynotebook.viewmodel.NoteViewModel

class ShowFragmentPresenter(_view: ShowFragment): ShowPresenter {
    private var view: ShowView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    init {
        view.initView()
    }

    //вызов неяного интента - отправки элемента бд
    override fun sendEmail(name: String, text: String, date: String) {
        if(inputCheck(name, text, date)){
            view.sendIntent(name, text)
        }else{
            view.showToast(R.string.fill_all)
        }

    }

    //обновление полей элемента бд
    override fun update(name: String, text: String, date: String) {
        if(inputCheck(name,text, date)){
            val updatedNote = Note(view.currentNoteId(), name, text, (date))

            mNoteViewModel.updateNote(updatedNote)
            view.showToast(R.string.update)
            view.returnToList()
        }else{
            view.showToast(R.string.fill_all)
        }
    }

    //удаление элемента бд
    override fun delete(currentNote: Note) {
        mNoteViewModel.deleteNote(currentNote)
        view.showToast(R.string.remove)
        view.returnToList()
    }

    //все ли строки заполнены
    override fun inputCheck(name: String, text: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(date))
    }
}