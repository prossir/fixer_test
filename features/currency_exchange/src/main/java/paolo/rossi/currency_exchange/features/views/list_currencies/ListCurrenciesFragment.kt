package paolo.rossi.currency_exchange.features.views.list_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import paolo.rossi.currency_exchange.R
import paolo.rossi.currency_exchange.databinding.FragmentListCurrenciesBinding
import paolo.rossi.currency_exchange.features.dto.CurrencyModel
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewModel
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewState


class ListCurrenciesFragment : Fragment(), CurrenciesAdapter.OnCurrencyItemClicked {

    private val viewModel: CurrencyExchangeViewModel by activityViewModels()
    private val viewStateObserver = Observer<CurrencyExchangeViewState> { state ->
        when (state) {
            is CurrencyExchangeViewState.AfterCurrenciesAreLoaded -> onCurrenciesLoaded()
            is CurrencyExchangeViewState.AfterExchangeRatesAreLoaded -> onExchangeRatesLoaded()
            is CurrencyExchangeViewState.OnError -> onError(state.errorMessage)
            else -> { /* Do nothing */ }
        }
    }

    private var binding: FragmentListCurrenciesBinding? = null
    private var adapter: CurrenciesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_currencies, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUi()
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, viewStateObserver)
    }

    private fun initUi() {
        adapter = CurrenciesAdapter(this)
        binding?.viewModel = viewModel
        binding?.rvCurrencies?.adapter = adapter
        viewModel.fetchLiveFilterableCurrencies()
    }

    private fun onCurrenciesLoaded() {
        viewModel.filterableLiveCurrencies.observe(viewLifecycleOwner, {
            adapter?.submitList(it)
        })
    }

    override fun onCurrencySelected(currency: CurrencyModel) {
        viewModel.fetchLiveExchangeRates(currency.abbreviation)
    }

    private fun onExchangeRatesLoaded() {
        view?.findNavController()?.navigate(ListCurrenciesFragmentDirections.actionListCurrencyFragmentToConvertCurrencyFragment())
    }

    private fun onError(errorMessage: String) {
        activity?.let {
            Snackbar.make(it.findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

}