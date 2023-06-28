package com.example.notesapp.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.NotesEntity.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notes ->
            oldMyNotes = notes as ArrayList<Notes>
            binding.recyclerNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter=NotesAdapter(requireContext(),notes)
            binding.recyclerNotes.adapter = adapter
        }
        binding.getAllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notes ->
                oldMyNotes = notes as ArrayList<Notes>
                binding.recyclerNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter=NotesAdapter(requireContext(),notes)
                binding.recyclerNotes.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notes ->
                oldMyNotes = notes as ArrayList<Notes>
                binding.recyclerNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),notes)
                binding.recyclerNotes.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notes ->
                oldMyNotes = notes as ArrayList<Notes>
                binding.recyclerNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),notes)
                binding.recyclerNotes.adapter = adapter
            }
        }
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notes ->
                oldMyNotes = notes as ArrayList<Notes>
                binding.recyclerNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter = NotesAdapter(requireContext(),notes)
                binding.recyclerNotes.adapter = adapter
            }
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment2)
        }
        return binding.root

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.search_menu)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }



        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun notesFiltering(newText: String?) {
        val newFilterList = arrayListOf<Notes>()

        for (i in oldMyNotes){
            if(i.title.contains(newText!!) || i.subtitle.contains(newText!!)){
                newFilterList.add(i)
            }
        }
        adapter.filtering(newFilterList)

    }
}


