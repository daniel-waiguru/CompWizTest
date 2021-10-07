package io.compwiz.countrylister.presentation.views.adapter

import android.net.Uri
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import io.compwiz.countrylister.databinding.CountryItemBinding
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.utils.loadImage

class CountryViewHolder(
    private val binding: CountryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
    fun bindItem(country: CountryDomain, isSelected: Boolean) {
        with(binding) {
            countryName.text = country.name
            alphaCode.text = country.alphaCode
            countryFlag.loadImage(Uri.parse(country.imageUrl), true)
            if (isSelected) root.alpha = 0.2F
        }
    }
    fun getItemDetails( items: List<CountryDomain>): ItemDetailsLookup.ItemDetails<CountryDomain> =
        object : ItemDetailsLookup.ItemDetails<CountryDomain>() {
            override fun getPosition(): Int = adapterPosition

            override fun getSelectionKey(): CountryDomain? = items[position]

        }

}