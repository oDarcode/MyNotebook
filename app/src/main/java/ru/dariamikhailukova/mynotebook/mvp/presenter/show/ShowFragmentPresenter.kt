package ru.dariamikhailukova.mynotebook.mvp.presenter.show

import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

    override fun sendEmail(name: String, text: String, date: String) {
        if(isEmpty(name, text, date)){
            view.showToast("something")
            //view.showToast(R.string.emptyError)
        }else{
            view.sendIntent(name, text)
            view.returnToList()
        }

    }

    override fun update(name: String, text: String, date: String) {
        if(isEmpty(name,text, date)){
            val updatedNote = Note(args.currentNote.id, name, text, Integer.parseInt(date))

            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_showFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun isEmpty(name: String, text: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(date))
                //date.isEmpty())
    }
}