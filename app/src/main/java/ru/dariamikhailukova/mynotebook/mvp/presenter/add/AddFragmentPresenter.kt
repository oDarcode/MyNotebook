package ru.dariamikhailukova.mynotebook.mvp.presenter.add

import android.text.TextUtils

import androidx.lifecycle.ViewModelProvider

import ru.dariamikhailukova.mynotebook.mvp.model.Note
import ru.dariamikhailukova.mynotebook.mvp.view.add.AddFragment
import ru.dariamikhailukova.mynotebook.mvp.view.add.AddView
import ru.dariamikhailukova.mynotebook.viewmodel.NoteViewModel

class AddFragmentPresenter(_view: AddFragment): AddPresenter {
    private var view: AddView = _view
    private lateinit var mNoteViewModel: NoteViewModel

    init{
        mNoteViewModel = ViewModelProvider(_view).get(NoteViewModel::class.java)
        //view.initView()
    }

    override fun insertDataToDatabase(name: String, text: String, date: String) {
        if (inputCheck(name, text, date)){
            val note = Note(0, name, text, Integer.parseInt(date))

            mNoteViewModel.addNote(note)
            view.showToast("Success")
            //Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
            view.returnToList()
            //findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            view.showToast("Please fill all fields.")
            //Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_LONG).show()
        }
    }

    override fun inputCheck(name: String, text: String, date: String): Boolean{
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || TextUtils.isEmpty(date))
        //date.isEmpty())
    }

}