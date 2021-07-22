package io.compwiz.countrylister.presentation.views.adapter

import androidx.recyclerview.selection.ItemKeyProvider
import io.compwiz.countrylister.domain.model.CountryDomain

class CountryItemKeyProvider(private val adapter: CountryAdapter): ItemKeyProvider<CountryDomain>(
    SCOPE_CACHED) {
    override fun getKey(position: Int): CountryDomain? {
        return adapter.getCountryItem(position)
    }

    override fun getPosition(key: CountryDomain): Int {
        return adapter.getPosition(key.name)
    }
}
