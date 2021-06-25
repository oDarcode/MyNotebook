package ru.dariamikhailukova.mynotebook.mvp.view.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import ru.dariamikhailukova.mynotebook.R
import ru.dariamikhailukova.mynotebook.mvp.model.Note
import ru.dariamikhailukova.mynotebook.viewmodel.NoteViewModel
import ru.dariamikhailukova.mynotebook.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save){
            insertDataToDatabase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase() {
        val name = binding.textNoteName.text.toString()
        val text = binding.textNote.text.toString()
        val date = binding.textDate.text
        //findNavController().navigate(R.id.action_addFragment_to_listFragment)
        if (inputCheck(name, text, date)){
            val note = Note(0, name, text, Integer.parseInt(date.toString()))

            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, text: String, date: Editable): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(text) || date.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}