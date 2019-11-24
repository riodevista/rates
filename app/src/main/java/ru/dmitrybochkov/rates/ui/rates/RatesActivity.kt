package ru.dmitrybochkov.rates.ui.rates

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import ru.dmitrybochkov.rates.R
import ru.dmitrybochkov.rates.domain.Rate
import ru.dmitrybochkov.rates.ui.BaseActivity
import ru.dmitrybochkov.rates.view_models.RatesViewModel

class RatesActivity : BaseActivity() {

    @BindView(R.id.recycler_view)
    internal lateinit var ratesList: RecyclerView
    @BindView(R.id.swipe_refresh_layout)
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.no_data)
    internal lateinit var noDataMessage: TextView

    internal lateinit var viewModel: RatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)
        ButterKnife.bind(this)
        viewModel = ViewModelProviders.of(this).get(RatesViewModel::class.java)
        initViews()
        bindData()
    }

    private fun initViews() {
        ratesList.layoutManager = LinearLayoutManager(this)
        ratesList.adapter = RatesListAdapter()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateRates()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun bindData() {
        viewModel.rates.observe(this, Observer<List<Rate>> { this.updateRatesList(it) })
    }

    private fun updateRatesList(rates: List<Rate>) {
        (ratesList.adapter as RatesListAdapter).updateItems(rates)
    }
}
