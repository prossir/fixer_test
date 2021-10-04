package paolo.rossi.currency_exchange.utils

import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter


@BindingAdapter("queryTextListener")
fun setOnQueryTextListener(searchView: SearchView, listener: SearchView.OnQueryTextListener) {
    searchView.setOnQueryTextListener(listener)
}

@BindingAdapter("doubleAsText")
fun doubleAsText(textView: TextView, value: Double) {
    textView.text = "$value"
}