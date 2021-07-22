package io.compwiz.countrylister.presentation.views.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.compwiz.countrylister.databinding.FragmentCountryDetailsBinding
import io.compwiz.countrylister.domain.model.CountryDomain
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
    private fun showDetails(country: CountryDomain) {
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
    private fun getCountryArg(): CountryDomain {
        val args by navArgs<CountryDetailsFragmentArgs>()
        return args.country
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}