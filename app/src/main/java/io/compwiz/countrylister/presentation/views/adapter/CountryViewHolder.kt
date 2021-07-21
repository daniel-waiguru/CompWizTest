package io.compwiz.countrylister.presentation.views.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import io.compwiz.countrylister.databinding.CountryItemBinding
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.utils.loadImage

class CountryViewHolder(
    private val binding: CountryItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
    fun bindItem(country: CountryDomain) {
        with(binding) {
            countryName.text = country.name
            alphaCode.text = country.alphaCode
            countryFlag.loadImage(Uri.parse(country.imageUrl), true)
        }
    }
}