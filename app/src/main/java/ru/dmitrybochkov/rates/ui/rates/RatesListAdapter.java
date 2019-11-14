package ru.dmitrybochkov.rates.ui.rates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dmitrybochkov.rates.R;
import ru.dmitrybochkov.rates.domain.Rate;

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
public class RatesListAdapter extends RecyclerView.Adapter<RatesListAdapter.RateViewHolder> {

    private List<Rate> rates;

    @NonNull
    @Override
    public RateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_rate, parent, false);
        return new RateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RateViewHolder holder, int position) {
        Rate rate = rates.get(position);
        holder.currency.setText(rate.getCurrency());
        holder.rate.setText(String.format(Locale.getDefault(),"%.2f", rate.getValue()));
    }

    @Override
    public int getItemCount() {
        return rates == null ? 0 : rates.size();
    }

    void updateItems(List<Rate> rates) {
        RatesDiffUtilCallback diffUtilCallback = new RatesDiffUtilCallback(this.rates, rates);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(diffUtilCallback);
        this.rates = rates;
        productDiffResult.dispatchUpdatesTo(this);
    }

    class RateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.currency)
        TextView currency;
        @BindView(R.id.rate)
        TextView rate;

        public RateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class RatesDiffUtilCallback<T extends Rate> extends DiffUtil.Callback {

        private final List<T> oldList;
        private final List<T> newList;

        public RatesDiffUtilCallback(List<T> oldList, List<T> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList == null ? 0 : oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList == null ? 0 : newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Rate oldRate = oldList.get(oldItemPosition);
            Rate newRate = newList.get(newItemPosition);
            return oldRate.getCurrency().equals(newRate.getCurrency());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Rate oldRate = oldList.get(oldItemPosition);
            Rate newRate = newList.get(newItemPosition);
            return Float.compare(oldRate.getValue(), newRate.getValue()) == 0;
        }
    }
}
