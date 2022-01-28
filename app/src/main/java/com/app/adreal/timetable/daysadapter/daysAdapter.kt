package com.app.adreal.timetable.daysadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.adreal.timetable.databinding.DayCardBinding
import com.app.adreal.timetable.daysdatabase.model.dayModel

class daysAdapter(private val context: Context, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<daysAdapter.myviewholder>() {

    private lateinit var binding: DayCardBinding

    interface OnItemClickListener
    {
        fun onItemClick(data : dayModel)
    }

    private  var datalist = emptyList<dayModel>()

    class myviewholder(binding: DayCardBinding) : RecyclerView.ViewHolder(binding.root) {

        val subject = binding.subject
        val starttime = binding.startTime
        val endtime = binding.endTime
        val settings = binding.settings
        val delete = binding.delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        binding = DayCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myviewholder(binding)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.subject.text = datalist[position].subject.toString()
        holder.starttime.text = datalist[position].starttime.toString()
        holder.endtime.text = datalist[position].endtime.toString()

        holder.itemView.setOnClickListener()
        {
            holder.settings.isVisible = !holder.settings.isVisible
        }

        holder.itemView.setOnLongClickListener()
        {
            Toast.makeText(context,"Long press",Toast.LENGTH_LONG).show()
            true
        }

        holder.delete.setOnClickListener()
        {
            onItemClickListener.onItemClick(datalist[position])
        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    fun setdata(data : List<dayModel>)
    {
        this.datalist = data
        notifyDataSetChanged()
    }
}
