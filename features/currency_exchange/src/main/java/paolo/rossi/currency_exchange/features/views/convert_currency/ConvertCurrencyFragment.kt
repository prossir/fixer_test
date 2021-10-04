package paolo.rossi.currency_exchange.features.views.convert_currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import paolo.rossi.currency_exchange.R
import paolo.rossi.currency_exchange.databinding.FragmentConvertCurrencyBinding
import paolo.rossi.currency_exchange.features.views.CurrencyExchangeViewModel


class ConvertCurrencyFragment : Fragment() {

    private val viewModel: CurrencyExchangeViewModel by activityViewModels()
    private var binding: FragmentConvertCurrencyBinding? = null
    private var adapter: ExchangeRatesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_convert_currency, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUi()
    }

    private fun initObservers() {
        viewModel.liveExchangeRates.observe(viewLifecycleOwner, {
            adapter?.submitList(it)
        })
    }

    private fun initUi() {
        adapter = ExchangeRatesAdapter()
        binding?.rvExchangeRates?.adapter = adapter
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
    }

}