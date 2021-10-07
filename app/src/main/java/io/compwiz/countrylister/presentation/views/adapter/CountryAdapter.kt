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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.compwiz.countrylister.data.local.entity.CountryEntity
import io.compwiz.countrylister.databinding.CountryItemBinding

class CountryAdapter (
    private val listener: (CountryEntity) -> Unit
): ListAdapter<CountryEntity, CountryViewHolder>(COMPARATOR) {
    var tracker: SelectionTracker<CountryEntity>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val countryItem = getItem(position)
        tracker?.let {
            holder.bindItem(countryItem, it.isSelected(countryItem))
        }
        holder.itemView.setOnClickListener {
            listener(countryItem)
        }
    }

    object COMPARATOR: DiffUtil.ItemCallback<CountryEntity>() {
        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.hashCode()== newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem == newItem
        }

    }
    fun getCountryItem(position: Int): CountryEntity = currentList[position]
    fun getPosition(name: String) = currentList.indexOfFirst { it.name == name }
}