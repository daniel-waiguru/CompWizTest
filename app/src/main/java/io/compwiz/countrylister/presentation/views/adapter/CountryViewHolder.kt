/*
 * MIT License
 *
 * Copyright (c) 2021 Daniel Waiguru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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