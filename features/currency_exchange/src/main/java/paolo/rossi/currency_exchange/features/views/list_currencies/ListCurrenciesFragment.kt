package paolo.rossi.currency_exchange.features.views.list_currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.rossi.currency_exchange.R
import paolo.rossi.currency_exchange.databinding.FragmentListCurrenciesBinding
import paolo.rossi.currency_exchange.features.dto.CurrencyModel
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewModel
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewState


class ListCurrenciesFragment : Fragment(), CurrenciesAdapter.OnCurrencyItemClicked {

    private val viewModel: CurrencyExchangeViewModel by viewModel()
    private val viewStateObserver = Observer<CurrencyExchangeViewState> { state ->
        when (state) {
            is CurrencyExchangeViewState.AfterCurrenciesAreLoaded -> onCurrenciesLoaded()
            is CurrencyExchangeViewState.OnError -> onError()
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
        binding?.viewModel = viewModel
        adapter = CurrenciesAdapter(this)
        viewModel.fetchLiveFilterableCurrencies()
    }

    private fun onCurrenciesLoaded() {
        viewModel.filterableLiveCurrencies.observe(viewLifecycleOwner, {
            adapter?.submitList(it)
        })
    }

    private fun onError() {

    }

    override fun onCurrencySelected(currency: CurrencyModel) {

    }

}