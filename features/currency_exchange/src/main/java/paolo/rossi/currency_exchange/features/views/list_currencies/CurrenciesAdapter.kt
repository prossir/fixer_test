package paolo.rossi.currency_exchange.features.views.list_currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import paolo.rossi.currency_exchange.databinding.ItemCurrencyBinding
import paolo.rossi.currency_exchange.features.dto.CurrencyModel

class CurrenciesAdapter(
    val listener: OnCurrencyItemClicked
): ListAdapter<CurrencyModel, CurrenciesAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.bind(it, listener) }
    }

    class ViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: CurrencyModel, listener: OnCurrencyItemClicked) {
            binding.currency = currency
            binding.listener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    interface OnCurrencyItemClicked {
        fun onCurrencySelected(currency: CurrencyModel)
    }

    companion object {
        private val diffUtil = object :
            DiffUtil.ItemCallback<CurrencyModel>() {
            override fun areItemsTheSame(old: CurrencyModel, new: CurrencyModel) =
                old.abbreviation == new.abbreviation

            override fun areContentsTheSame(old: CurrencyModel, new: CurrencyModel ) =
                old == new
        }
    }

}