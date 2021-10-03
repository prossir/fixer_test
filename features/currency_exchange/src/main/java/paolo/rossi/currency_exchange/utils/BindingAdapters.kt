package paolo.rossi.currency_exchange.utils

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter


@BindingAdapter("queryTextListener")
fun setOnQueryTextListener(searchView: SearchView, listener: SearchView.OnQueryTextListener) {
    searchView.setOnQueryTextListener(listener)
}