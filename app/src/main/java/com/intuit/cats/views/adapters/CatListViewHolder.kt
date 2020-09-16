package com.intuit.cats.views.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.intuit.cats.models.Cat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_cat_list_row.view.catBreedNameText

class CatListViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(cat: Cat, onClickListener: (String) -> Unit) {
        with(containerView) {
            catBreedNameText.text = cat.name
            setOnClickListener {
                onClickListener(cat.id)
            }
        }
    }
}