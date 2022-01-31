package com.app.adreal.timetable.daysadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.adreal.timetable.databinding.TaskCardBinding
import com.app.adreal.timetable.daysdatabase.model.taskModel

class taskAdapter(private val context: Context) : RecyclerView.Adapter<taskAdapter.myViewHolder>() {

    private lateinit var binding: TaskCardBinding

    private  var datalist = emptyList<taskModel>()

    class myViewHolder(binding: TaskCardBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.title
        val date = binding.date
        val time = binding.time
        val info = binding.info
        val expand = binding.settings
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        binding = TaskCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.title.text = datalist[position].title
        holder.time.text = datalist[position].time
        holder.date.text = datalist[position].date
        holder.info.text = datalist[position].info

        holder.itemView.setOnClickListener()
        {
            holder.expand.isVisible = !holder.expand.isVisible
        }

        holder.itemView.setOnLongClickListener()
        {
            Toast.makeText(context,"Long press", Toast.LENGTH_LONG).show()
            true
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<taskModel>)
    {
        this.datalist = data
        notifyDataSetChanged()
    }
}