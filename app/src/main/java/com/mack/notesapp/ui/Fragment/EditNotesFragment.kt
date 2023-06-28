package com.mack.notesapp.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mack.notesapp.NotesEntity.Notes
import com.mack.notesapp.R
import com.mack.notesapp.ViewModel.NotesViewModel
import com.mack.notesapp.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Date

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding:FragmentEditNotesBinding
    var priority : String = "1"
    val viewModel : NotesViewModel by viewModels()
    private var onBackPressedCallback: OnBackPressedCallback? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding=FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subtitle)
        binding.editNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" ->{
                priority="1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "2" ->{
                priority="2"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3" ->{
                priority="3"
                binding.pRed.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

        binding.pGreen.setOnClickListener{
            priority="1"
            binding.pGreen.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority="2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority="3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root

    }

    private fun navigateBackToHome() {
        val navController = Navigation.findNavController(requireView())
        navController.navigate(R.id.action_editNotesFragment_to_homeFragment2)
    }

    private fun updateNotes(it: View?) {

        val title = binding.editTitle.text.toString()
        val subtitle=binding.editSubtitle.text.toString()
        val notes = binding.editNotes.text.toString()

        val d = Date()

        val notesDate:CharSequence = DateFormat.format("d MMMM, yyyy ", d.time)

        val data = Notes(
            oldNotes.data.id,
            title =title,
            subtitle =subtitle,
            notes = notes,
            date=notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment2)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete_menu){
            val bottomSheet :BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)

            bottomSheet.setContentView(R.layout.dialog_delete)

            bottomSheet.show()

            val bottomSheetYes = bottomSheet.findViewById<TextView>(R.id.btn_yes)
            val bottomSheetNo = bottomSheet.findViewById<TextView>(R.id.btn_no)

            bottomSheetYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                val navController = Navigation.findNavController(requireView())
                navController.popBackStack(R.id.homeFragment, false)
                bottomSheet.dismiss()
            }
            bottomSheetNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }

        return super.onOptionsItemSelected(item)
    }



}