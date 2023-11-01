package com.jl.blog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jl.blog.R
import com.jl.blog.domain.model.Blog
import java.util.*

class BlogAdapter(private val listBlog: List<Blog>, select :OnItemClickListener) :
    RecyclerView.Adapter<BlogAdapter.ViewHolder>(), Filterable{

    interface OnItemClickListener {
        fun onItemClick(item: Blog)
    }

    private val listener: OnItemClickListener = select


    var listFilterBlog: List<Blog> = listBlog
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogObj = listFilterBlog[position]
        holder.txtTitle.text = blogObj.title
        holder.txtDescription.setText(blogObj.content)
        val dateSplit: List<String> = blogObj.date.split("T")
        holder.txtAuthor.text = blogObj.author
        val date = dateSplit[0].split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.txtDay.text = date[2]
        holder.txtMonth.text = date[1]
        holder.txtYear.text = date[0]
        holder.txtTitle.text = blogObj.title

        holder.constraint.setOnClickListener {
            listener.onItemClick(blogObj)
        }
    }

    override fun getItemCount(): Int {
        return listFilterBlog.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView
        var txtDescription: TextView
        var txtAuthor: TextView
        var txtDay: TextView
        var txtMonth: TextView
        var txtYear: TextView
        var btnMenu: ImageView
        var constraint: ConstraintLayout



        init {
            txtTitle = view.findViewById(R.id.txtTitle)
            txtDescription = view.findViewById(R.id.txtDescription)
            txtAuthor = view.findViewById(R.id.txtAuthor)
            txtDay = view.findViewById(R.id.txtDay)
            txtMonth = view.findViewById(R.id.txtMonth)
            txtYear = view.findViewById(R.id.txtYear)
            btnMenu = view.findViewById(R.id.btnMenu)
            constraint = view.findViewById(R.id.constraint)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    listFilterBlog = listBlog
                } else {
                    val resultList = ArrayList<Blog>()
                    for (row in listBlog) {
                        if (row.title.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    listFilterBlog = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = listFilterBlog
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listFilterBlog = results?.values as ArrayList<Blog>
                notifyDataSetChanged()
            }
        }
    }
}