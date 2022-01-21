package com.app.adreal.timetable.daysadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.adreal.timetable.databinding.DayCardBinding
import com.app.adreal.timetable.daysdatabase.model.monday_model

class mondayadapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<mondayadapter.myviewholder>() {

    private lateinit var binding: DayCardBinding

    interface OnItemClickListener
    {
        fun onItemClick(data : monday_model)
    }

    private  var datalist = emptyList<monday_model>()

    class myviewholder(binding: DayCardBinding) : RecyclerView.ViewHolder(binding.root) {

        val subject = binding.subject
        val starttime = binding.startTime
        val endtime = binding.endTime
        val expand = binding.expand
        val contract = binding.contract
        val settings = binding.settings
        val delete = binding.delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mondayadapter.myviewholder {
        binding = DayCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: mondayadapter.myviewholder, position: Int) {
        holder.subject.text = datalist[position].subject.toString()
        holder.starttime.text = datalist[position].starttime.toString()
        holder.endtime.text = datalist[position].endtime.toString()

        holder.expand.setOnClickListener()
        {
            holder.settings.isVisible = true
            holder.expand.isVisible = false
        }

        holder.contract.setOnClickListener()
        {
            holder.settings.isVisible = false
            holder.expand.isVisible = true
        }

        holder.delete.setOnClickListener()
        {
            onItemClickListener.onItemClick(datalist[position])
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<monday_model>)
    {
        this.datalist = data
        notifyDataSetChanged()
    }
}
