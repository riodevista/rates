package ru.dmitrybochkov.rates.ui.rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import ru.dmitrybochkov.rates.R
import ru.dmitrybochkov.rates.domain.Rate
import java.util.*

/**
 * Created by Dmitry Bochkov on 14.11.2019.
 */
internal class RatesListAdapter : RecyclerView.Adapter<RatesListAdapter.RateViewHolder>() {

    private var rates: List<Rate>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_rate, parent, false)
        return RateViewHolder(view)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        val rate = rates!![position]
        holder.currency.text = rate.currency
        holder.rate.text = String.format(Locale.getDefault(), "%.2f", rate.value)
    }

    override fun getItemCount(): Int {
        return if (rates == null) 0 else rates!!.size
    }

    internal fun updateItems(rates: List<Rate>) {
        val diffUtilCallback = RatesDiffUtilCallback(this.rates, rates)
        val productDiffResult = DiffUtil.calculateDiff(diffUtilCallback)
        this.rates = rates
        productDiffResult.dispatchUpdatesTo(this)
    }

    inner class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.currency)
        lateinit var currency: TextView
        @BindView(R.id.rate)
        lateinit var rate: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    internal inner class RatesDiffUtilCallback(private val oldList: List<Rate>?, private val newList: List<Rate>?) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList?.size ?: 0
        }

        override fun getNewListSize(): Int {
            return newList?.size ?: 0
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRate = oldList!![oldItemPosition]
            val newRate = newList!![newItemPosition]
            return oldRate.currency.equals(newRate.currency)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRate = oldList!![oldItemPosition]
            val newRate = newList!![newItemPosition]
            return oldRate.value.compareTo(newRate.value) == 0
        }
    }
}
