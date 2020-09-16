package com.intuit.cats.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.intuit.cats.R
import com.intuit.cats.models.Cat

class CatListAdapter(
    var cats: List<Cat>,
    private val onRowClickListener: OnRowClickListener
) : RecyclerView.Adapter<CatListViewHolder>() {

    companion object {
        fun newInstance(cats: List<Cat>, onRowClick: (String) -> Unit): CatListAdapter {
            return CatListAdapter(cats, object : OnRowClickListener {
                override fun onClick(id: String) {
                    onRowClick(id)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_cat_list_row, parent, false)
        return CatListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        holder.bind(cats[position], onRowClickListener::onClick)
    }

    fun update(newList: List<Cat>) {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(CatDataDiffCallback(cats, newList))
        cats = newList
        result.dispatchUpdatesTo(this)
    }

    interface OnRowClickListener {
        fun onClick(id: String)
    }
}