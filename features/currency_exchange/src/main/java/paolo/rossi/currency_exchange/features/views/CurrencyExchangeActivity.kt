package paolo.rossi.currency_exchange.features.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import paolo.rossi.currency_exchange.R
import paolo.rossi.currency_exchange.databinding.ActivityCurrencyExchangeBinding


class CurrencyExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyExchangeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel :CurrencyExchangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_exchange)
        initUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = findNavController(R.id.f_nav_host)
        return NavigationUI.navigateUp(navigationController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initUI() {
        viewModel.isLoading.set(false)
        setSupportActionBar(binding.toolbar)
        val navigationController = findNavController(R.id.f_nav_host)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.listCurrencyFragment))
        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration)
    }

}