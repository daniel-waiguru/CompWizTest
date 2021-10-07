package io.compwiz.countrylister.presentation.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.compwiz.countrylister.databinding.CountryItemBinding
import io.compwiz.countrylister.domain.model.CountryDomain

class CountryAdapter (
    private val listener: (CountryDomain) -> Unit
): ListAdapter<CountryDomain, CountryViewHolder>(COMPARATOR) {
    var tracker: SelectionTracker<CountryDomain>? = null
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

    object COMPARATOR: DiffUtil.ItemCallback<CountryDomain>() {
        override fun areItemsTheSame(oldItem: CountryDomain, newItem: CountryDomain): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CountryDomain, newItem: CountryDomain): Boolean {
            return oldItem == newItem
        }

    }
    fun getCountryItem(position: Int): CountryDomain = currentList[position]
    fun getPosition(name: String) = currentList.indexOfFirst { it.name == name }
}