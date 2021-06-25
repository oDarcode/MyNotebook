package ru.dariamikhailukova.mynotebook.mvp.presenter.show

import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
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
        if(inputCheck(name, text, date)){
            view.sendIntent(name, text)
            //view.returnToList()
        }else{
            view.showToast("something")
            //view.showToast(R.string.emptyError)
        }

    }

    override fun update(name: String, text: String, date: String) {
        if(inputCheck(name,text, date)){
            val updatedNote = Note(view.currentNoteId(), name, text, Integer.parseInt(date))

            mNoteViewModel.updateNote(updatedNote)
            view.showToast("Successfully updated")
            view.returnToList()
            //Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()
            //findNavController().navigate(R.id.action_showFragment_to_listFragment)
        }else{
            view.showToast("Please fill out all fields")
            //Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    override fun delete(currentNote: Note) {
        mNoteViewModel.deleteNote(currentNote)
        view.showToast("Successfully removed")
        view.returnToList()
        //Toast.makeText(requireContext(), "Successfully removed", Toast.LENGTH_SHORT).show()
        //findNavController().navigate(R.id.action_showFragment_to_listFragment)
    }

    override fun inputCheck(name: String, text: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(date))
                //date.isEmpty())
    }
}