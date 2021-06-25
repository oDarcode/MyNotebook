package ru.dariamikhailukova.mynotebook.mvp.view.show

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.dariamikhailukova.mynotebook.R

import ru.dariamikhailukova.mynotebook.databinding.FragmentShowBinding
import ru.dariamikhailukova.mynotebook.mvp.presenter.show.ShowFragmentPresenter

class ShowFragment : Fragment(), ShowView {
    private var presenter: ShowFragmentPresenter? = null
    private val args by navArgs<ShowFragmentArgs>()

    private var _binding: FragmentShowBinding? = null
    private val binding get() = _binding!!

    //private lateinit var mNoteViewModel: NoteViewModel

    lateinit var name: String
    lateinit var text: String
    lateinit var date: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        //mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        presenter = ShowFragmentPresenter(this)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun initView(){
        name = args.currentNote.name
        text = args.currentNote.text
        date = args.currentNote.date.toString()

        binding.updateTextNoteName.setText(name)
        binding.updateTextNote.setText(text)
        binding.updateTextDate.setText(date)
    }


    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_share_delete_menu, menu)
    }

    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteNote()
        }

        if(item.itemId == R.id.menu_share){
            presenter?.sendEmail(name, text, date)
        }

        if(item.itemId == R.id.menu_save){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateItem(){
        name = binding.updateTextNoteName.text.toString()
        text = binding.updateTextNote.text.toString()
        date = binding.updateTextDate.text.toString()
        //date = Integer.parseInt(binding.updateTextDate.text.toString())

        presenter?.update(name, text, date)
    }


    override fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            presenter?.delete(args.currentNote)
            //mNoteViewModel.deleteNote(args.currentNote)
            //Toast.makeText(requireContext(), "Successfully removed", Toast.LENGTH_SHORT).show()
            //findNavController().navigate(R.id.action_showFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ${args.currentNote.name}?")
        builder.setMessage("Are you sure?")
        builder.create().show()
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun sendIntent(name: String, text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, name)
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, ""))
    }

    override fun returnToList() {
        findNavController().navigate(R.id.action_showFragment_to_listFragment)
    }

    override fun currentNoteId(): Int {
        return args.currentNote.id
    }

}