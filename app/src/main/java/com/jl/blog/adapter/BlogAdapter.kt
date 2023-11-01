package com.jl.blog.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jl.blog.R
import com.jl.blog.database.entities.BlogEntity
import com.jl.blog.domain.model.Blog
import java.util.*

class BlogAdapter(private val listTask: List<Blog>?, private val context: Context) :
    RecyclerView.Adapter<BlogAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskObj = listTask!![position]
        holder.txtTitle.text = taskObj.title
        holder.txtDescription.setText(taskObj.content)
        val hourSplit: List<String> = taskObj.date.split(":")
        if (hourSplit[0].toInt() >= 12 && hourSplit[0].toInt() < 24) {
            holder.txtHour.setText("" + " " + "pm")
        } else {
            holder.txtHour.setText("" + " " + "am")
        }
        val date = taskObj.date.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.txtDay.text = date[0]
        holder.txtMonth.text = date[1]
        holder.txtYear.text = date[2]
        holder.txtTitle.text = taskObj.title
    }

    override fun getItemCount(): Int {
        return listTask?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView
        var txtDescription: TextView
        var txtHour: TextView
        var txtDay: TextView
        var txtMonth: TextView
        var txtYear: TextView
        var btnMenu: ImageView

        init {
            txtTitle = view.findViewById(R.id.txtTitle)
            txtDescription = view.findViewById(R.id.txtDescription)
            txtHour = view.findViewById(R.id.txtHour)
            txtDay = view.findViewById(R.id.txtDay)
            txtMonth = view.findViewById(R.id.txtMonth)
            txtYear = view.findViewById(R.id.txtYear)
            btnMenu = view.findViewById(R.id.btnMenu)
        }
    }

    private fun equalsToday(myDate: Date, today: Date): Boolean {
        return if (myDate.year == today.year && myDate.month == today.month) {
            myDate.day == today.day
        } else false
    }
}