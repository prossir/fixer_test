package paolo.rossi.currency_exchange.features.views


sealed class CurrencyExchangeViewState {
    object None : CurrencyExchangeViewState()
    object AfterCurrenciesAreLoaded : CurrencyExchangeViewState()
    object AfterExchangeRatesAreLoaded : CurrencyExchangeViewState()
    data class OnError(val errorMessage: String) : CurrencyExchangeViewState()
}