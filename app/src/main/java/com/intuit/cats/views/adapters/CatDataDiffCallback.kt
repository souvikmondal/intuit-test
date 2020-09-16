package com.intuit.cats.views.adapters

import androidx.recyclerview.widget.DiffUtil
import com.intuit.cats.models.Cat

class CatDataDiffCallback (
    private val oldCatData: List<Cat>,
    private var newCatData: List<Cat>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldCatData[oldItemPosition] == newCatData[newItemPosition]

    override fun getOldListSize(): Int = oldCatData.size

    override fun getNewListSize(): Int = newCatData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true
}