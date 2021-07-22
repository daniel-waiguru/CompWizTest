package io.compwiz.countrylister.presentation.views.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.compwiz.countrylister.data.models.StateWrapper
import io.compwiz.countrylister.databinding.FragmentCountryListBinding
import io.compwiz.countrylister.domain.model.CountryDomain
import io.compwiz.countrylister.presentation.viewmodels.CountryListViewModel
import io.compwiz.countrylister.presentation.views.adapter.CountryAdapter
import io.compwiz.countrylister.presentation.views.adapter.CountryDetailsLookup
import io.compwiz.countrylister.presentation.views.adapter.CountryItemKeyProvider
import io.compwiz.countrylister.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryListFragment : Fragment() {
    private var _binding: FragmentCountryListBinding? = null
    private val binding get() = _binding!!
    private val countryListViewModel: CountryListViewModel by viewModel()
    private lateinit var tracker: SelectionTracker<CountryDomain>
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
    private fun setupTracker(adapter: CountryAdapter, rv: RecyclerView, items: List<CountryDomain>) {
        tracker = SelectionTracker.Builder(
            "selectedCountry",
            rv,
            CountryItemKeyProvider(adapter),
            CountryDetailsLookup(rv, items),
            StorageStrategy.createParcelableStorage(CountryDomain::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()
        adapter.tracker = tracker
    }
    private fun observeViewState(adapter: CountryAdapter) {
        countryListViewModel.resState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateWrapper.Success -> {
                    setupTracker(adapter, binding.countryRv, state.value)
                    selectionObserver(tracker)
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

    private fun selectionObserver(tracker: SelectionTracker<CountryDomain>?) {
        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<CountryDomain>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val selectedItems = tracker.selection.toList()
                    if (selectedItems.size == 3) {
                        var withHighestPop = 0
                        for (item in selectedItems) {
                            if (item.population > withHighestPop){
                                withHighestPop = item.population
                            }
                        }
                        snackbar(
                            "${selectedItems.find {
                                it.population == withHighestPop }?.alphaCode
                            } (${withHighestPop.format()})")
                    }
                }
            }
        )
    }
    private fun createAdapter(): CountryAdapter {
        return CountryAdapter {
            onCountryItemClicked(it)
        }
    }
    private fun onCountryItemClicked(country: CountryDomain) {
        navigateToDetailsScreen(country)
    }
    private fun navigateToDetailsScreen(country: CountryDomain) {
        findNavController().navigate(
            CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(country)
        )
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