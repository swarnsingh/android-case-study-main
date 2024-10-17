package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.models.deals.Deal


class DealItemAdapter(private var deals: List<Deal>) : RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deals.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = deals[position]
        viewHolder.bind(item)
    }

    fun updateDeals(newDeals: List<Deal>) {
        this.deals = newDeals
        notifyDataSetChanged()
    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(deal: Deal) {
        itemView.findViewById<TextView>(R.id.deal_list_item_title).text = deal.title
        itemView.findViewById<TextView>(R.id.deal_list_item_price).text =
            deal.salePrice?.displayString.orEmpty()
    }
}
