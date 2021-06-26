package ru.dariamikhailukova.mynotebook.mvp.presenter.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import ru.dariamikhailukova.mynotebook.mvp.model.Note
import ru.dariamikhailukova.mynotebook.mvp.view.list.ListFragment
import ru.dariamikhailukova.mynotebook.mvp.view.list.ListView
import ru.dariamikhailukova.mynotebook.viewmodel.NoteViewModel

class ListFragmentPresenter(_view: ListFragment): ListPresenter {
    private var view: ListView = _view
    private var mNoteViewModel: NoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)

    //считывание всех элементов бд
    override fun getAllData(): LiveData<List<Note>> {
        return mNoteViewModel.readAllData
    }

    //удаление всех элементов бд
    override fun deleteAll(){
        mNoteViewModel.deleteAllNotes()
        view.showToast("Removed everything")
    }
}