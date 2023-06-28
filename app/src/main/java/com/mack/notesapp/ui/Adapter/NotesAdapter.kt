package com.mack.notesapp.ui.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mack.notesapp.NotesEntity.Notes
import com.mack.notesapp.R
import com.mack.notesapp.databinding.ItemNotesBinding
import com.mack.notesapp.ui.Fragment.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.viewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilterList: ArrayList<Notes>) {
        notesList=newFilterList
        notifyDataSetChanged()
    }
    class viewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        return viewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= notesList.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.viewTitle.text=data.title
        holder.binding.viewSubtitle.text=data.subtitle
        holder.binding.viewDate.text = data.date

        when(data.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }
}