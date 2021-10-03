package paolo.rossi.currency_exchange.features.views


sealed class CurrencyExchangeViewState {
    object None : CurrencyExchangeViewState()
    object AfterCurrenciesAreLoaded : CurrencyExchangeViewState()
    object OnError : CurrencyExchangeViewState()
}