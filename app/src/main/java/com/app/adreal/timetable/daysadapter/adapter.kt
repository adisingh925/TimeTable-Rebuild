package com.app.adreal.timetable.daysadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.adreal.timetable.databinding.DayCardBinding
import com.app.adreal.timetable.daysdatabase.model.day_model

class adapter() : RecyclerView.Adapter<adapter.myviewholder>() {

    private lateinit var binding: DayCardBinding

    private  var datalist = emptyList<day_model>()

    class myviewholder(binding: DayCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val subject = binding.subject
        val starttime = binding.startTime
        val endtime = binding.endTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.myviewholder {
        binding = DayCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: adapter.myviewholder, position: Int) {
        holder.subject.text = datalist[position].subject.toString()
        holder.starttime.text = datalist[position].starttime.toString()
        holder.endtime.text = datalist[position].endtime.toString()
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<day_model>)
    {
        this.datalist = data
        notifyDataSetChanged()
    }
}