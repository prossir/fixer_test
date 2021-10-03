package paolo.rossi.currency_exchange.features.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import paolo.rossi.currency_exchange.R
import paolo.rossi.currency_exchange.databinding.ActivityCurrencyExchangeBinding


class CurrencyExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyExchangeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    //private val viewModel: CurrencyExchangeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_currency_exchange)
        initUI()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = findNavController(R.id.f_nav_host)
        return NavigationUI.navigateUp(navigationController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initUI() {
        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawerLayout
        val navigationController = findNavController(R.id.f_nav_host)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.listCurrencyFragment), drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navigationController, appBarConfiguration)
    }

}