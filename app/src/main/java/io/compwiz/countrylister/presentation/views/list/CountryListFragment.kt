package io.compwiz.countrylister.presentation.views.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.compwiz.countrylister.data.models.StateWrapper
import io.compwiz.countrylister.databinding.FragmentCountryListBinding
import io.compwiz.countrylister.presentation.viewmodels.CountryListViewModel
import io.compwiz.countrylister.presentation.views.adapter.CountryAdapter
import io.compwiz.countrylister.utils.gone
import io.compwiz.countrylister.utils.snackBar
import io.compwiz.countrylister.utils.visible
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
        setupUi()
        countryListViewModel.fetchCountries()
    }
    private fun setupUi() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewState(adapter)
    }
    private fun observeViewState(adapter: CountryAdapter) {
        countryListViewModel.resState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateWrapper.Success -> {
                    binding.progress.gone()
                    adapter.submitList(state.value)
                    Log.d("Countries", state.value.toString())
                }
                is StateWrapper.Failure -> {
                    binding.progress.gone()
                    snackBar(state.errorMessage) {
                        countryListViewModel.fetchCountries()
                    }
                }
                is StateWrapper.Loading -> {
                    binding.progress.visible()
                }
            }
        }
    }
    private fun createAdapter(): CountryAdapter {
        return CountryAdapter()
    }
    private fun setupRecyclerView(adapter: CountryAdapter) {
        binding.countryRv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}