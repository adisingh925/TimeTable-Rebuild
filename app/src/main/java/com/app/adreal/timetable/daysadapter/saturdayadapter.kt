package com.app.adreal.timetable.daysadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.adreal.timetable.databinding.DayCardBinding
import com.app.adreal.timetable.daysdatabase.model.saturday_model

class saturdayadapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<saturdayadapter.myviewholder>() {

    private lateinit var binding: DayCardBinding

    interface OnItemClickListener
    {
        fun onItemClick(data : saturday_model)
    }

    private  var datalist = emptyList<saturday_model>()

    class myviewholder(binding: DayCardBinding) : RecyclerView.ViewHolder(binding.root) {

        val subject = binding.subject
        val starttime = binding.startTime
        val endtime = binding.endTime
        val settings = binding.settings
        val delete = binding.delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): saturdayadapter.myviewholder {
        binding = DayCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: saturdayadapter.myviewholder, position: Int) {
        holder.subject.text = datalist[position].subject.toString()
        holder.starttime.text = datalist[position].starttime.toString()
        holder.endtime.text = datalist[position].endtime.toString()

        holder.itemView.setOnClickListener()
        {
            holder.settings.isVisible = !holder.settings.isVisible
        }

        holder.delete.setOnClickListener()
        {
            onItemClickListener.onItemClick(datalist[position])
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<saturday_model>)
    {
        this.datalist = data
        notifyDataSetChanged()
    }
}
