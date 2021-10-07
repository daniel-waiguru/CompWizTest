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

package io.compwiz.countrylister.presentation.views.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.compwiz.countrylister.data.local.entity.CountryEntity
import io.compwiz.countrylister.databinding.FragmentCountryDetailsBinding
import io.compwiz.countrylister.utils.loadImage

class CountryDetailsFragment : Fragment() {
    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }
    private fun setupUI() {
        val country = getCountryArg()
        showDetails(country)
    }
    private fun showDetails(country: CountryEntity) {
        with(binding) {
            largeFlag.loadImage(Uri.parse(country.imageUrl))
            countryName.text = country.name
            regionChip.text = country.region
            subRegionChip.text = country.subRegion
            capitalCity.text = country.capitalCity
            population.text = country.population.toString()
            alphaCode.text = country.alphaCode
        }
    }
    private fun getCountryArg(): CountryEntity{
        val args by navArgs<CountryDetailsFragmentArgs>()
        return args.country
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}