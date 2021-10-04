package paolo.rossi.currency_exchange.features.dto

import androidx.databinding.ObservableDouble
import androidx.lifecycle.MutableLiveData
import java.util.*


data class ExchangeRateModel(
    val baseCurrency: String,
    val exchangeCurrency: String,
    val exchangeRate: Double
) {

    val exchangeCurrencySymbol: String
        get() {
            return try {
                Currency.getInstance(exchangeCurrency).getSymbol(Locale.ROOT)
            } catch (e: Exception) {
                ""
            }
        }

    var baseCurrencyEquivalent: MutableLiveData<ObservableDouble> = MutableLiveData(ObservableDouble(0.0))

}