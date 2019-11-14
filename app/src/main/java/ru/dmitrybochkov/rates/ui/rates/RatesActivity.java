package ru.dmitrybochkov.rates.ui.rates;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dmitrybochkov.rates.R;
import ru.dmitrybochkov.rates.domain.Rate;
import ru.dmitrybochkov.rates.ui.BaseActivity;
import ru.dmitrybochkov.rates.view_models.RatesViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class RatesActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView ratesList;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_data)
    TextView noDataMessage;

    RatesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(RatesViewModel.class);
        initViews();
        bindData();
    }

    private void initViews() {
        ratesList.setLayoutManager(new LinearLayoutManager(this));
        ratesList.setAdapter(new RatesListAdapter());

        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.updateRates();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void bindData() {
        viewModel.getRates().observe(this, this::updateRatesList);
    }

    private void updateRatesList(List<Rate> rates) {
//        if (rates.isEmpty()) {
//            noDataMessage.setVisibility(View.VISIBLE);
//            swipeRefreshLayout.setVisibility(View.INVISIBLE);
//        } else {
//            noDataMessage.setVisibility(View.INVISIBLE);
//            swipeRefreshLayout.setVisibility(View.VISIBLE);
//        }
        ((RatesListAdapter) ratesList.getAdapter()).updateItems(rates);
    }
}
