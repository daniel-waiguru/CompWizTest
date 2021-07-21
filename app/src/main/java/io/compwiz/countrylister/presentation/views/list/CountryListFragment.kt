package io.compwiz.countrylister.presentation.views.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.compwiz.countrylister.R
import io.compwiz.countrylister.data.models.StateWrapper
import io.compwiz.countrylister.databinding.FragmentCountryListBinding
import io.compwiz.countrylister.presentation.viewmodels.CountryListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryListFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private val countryListViewModel: CountryListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryListViewModel.fetchCountries()
        subscribers()
    }
    private fun subscribers() {
        countryListViewModel.resState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateWrapper.Success -> {
                    Log.d("Me", state.value.toString())
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}