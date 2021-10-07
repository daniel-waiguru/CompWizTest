package io.compwiz.countrylister.presentation.views.adapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import io.compwiz.countrylister.domain.model.CountryDomain

class CountryDetailsLookup(private val rv: RecyclerView, private val items: List<CountryDomain>): ItemDetailsLookup<CountryDomain>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<CountryDomain>? {
        val view = rv.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (rv.getChildViewHolder(view) as CountryViewHolder).getItemDetails(items)
        }
        return null
    }
}