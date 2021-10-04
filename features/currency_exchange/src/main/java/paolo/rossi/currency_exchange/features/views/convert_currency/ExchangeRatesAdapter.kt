package paolo.rossi.currency_exchange.features.views.convert_currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import paolo.rossi.currency_exchange.databinding.ItemExchangedCurrencyBinding
import paolo.rossi.currency_exchange.features.dto.ExchangeRateModel


class ExchangeRatesAdapter : ListAdapter<ExchangeRateModel, ExchangeRatesAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.bind(it) }
    }

    class ViewHolder(private val binding: ItemExchangedCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exchangeRate: ExchangeRateModel) {
            binding.exchangeRate = exchangeRate
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemExchangedCurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        private val diffUtil = object :
            DiffUtil.ItemCallback<ExchangeRateModel>() {
            override fun areItemsTheSame(old: ExchangeRateModel, new: ExchangeRateModel) =
                old.exchangeCurrency == new.exchangeCurrency

            override fun areContentsTheSame(old: ExchangeRateModel, new: ExchangeRateModel) =
                old == new
        }
    }

}