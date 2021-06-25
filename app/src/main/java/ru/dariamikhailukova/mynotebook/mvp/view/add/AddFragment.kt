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

import ru.dariamikhailukova.mynotebook.databinding.FragmentAddBinding
import ru.dariamikhailukova.mynotebook.mvp.presenter.add.AddFragmentPresenter


class AddFragment : Fragment(), AddView {
    private var presenter: AddFragmentPresenter? = null
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    //private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        //mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        presenter = AddFragmentPresenter(this)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun initView() {
        //TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save){
            val name = binding.textNoteName.text.toString()
            val text = binding.textNote.text.toString()
            val date = binding.textDate.text.toString()

            presenter?.insertDataToDatabase(name, text, date)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun returnToList() {
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}