package ru.dariamikhailukova.mynotebook.mvp.view.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.dariamikhailukova.mynotebook.R
import ru.dariamikhailukova.mynotebook.databinding.FragmentAddBinding
import ru.dariamikhailukova.mynotebook.mvp.presenter.add.AddFragmentPresenter

class AddFragment : Fragment(), AddView {
    private var presenter: AddFragmentPresenter? = null
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        presenter = AddFragmentPresenter(this)
        setHasOptionsMenu(true)

        return binding.root
    }

    //Создание меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
    }

    //выбор элемента меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save){
            val name = binding.textNoteName.text.toString()
            val text = binding.textNote.text.toString()

            presenter?.insertDataToDatabase(name, text)
        }
        return super.onOptionsItemSelected(item)
    }

    //переход к фрагменту list
    override fun returnToList() {
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    //вывод Toast
    override fun showToast(text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}