package com.example.courseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.courseapp.R

class CurrenciesAdapter:BaseAdapter<CurrenciesAdapter.CurrencyViewHolder>() {

    class CurrencyViewHolder(view: View) : BaseAdapter.BaseViewHolder(view){

        private val tvCurrencyName:TextView = view.findViewById(R.id.tvCurrencyName)
        private val tvCurrencySym: TextView = view.findViewById(R.id.tvCurrencySym)
        private val ivCurrencyIcon:ImageView = view.findViewById(R.id.ivCurrencyIcon)
        private val tvCurrencyMarketCap:TextView = view.findViewById(R.id.tvCurrencyMarketCap)
        private val tvCurrencyPrice:TextView = view.findViewById(R.id.tvCurrencyPrice)

        init {
            itemView.setOnClickListener {
                //click
            }
        }

        override fun bind(item: Any) {
            let {
                item as Currency
                Glide.with(view.context).load(item.image).into(ivCurrencyIcon)
                tvCurrencySym.text = item.symbol
                tvCurrencyName.text = item.name
                tvCurrencyMarketCap.text = item.marketCap
                tvCurrencyPrice.text = item.price.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false))
    }

    //класс данных для элемента списка
    data class Currency(
        val id: String,
        val symbol: String,
        val name: String,
        val image: String,
        val price: Float,
        val marketCap: String,
        val marketCapRank: Int,
        val totalVolume: Float,
        val priceChangePercentage24h: Float,
        val marketCapChangePercentage24h: Float,
        val circulatingSupply: Double,
        val totalSupply: Long,
        val ath: Float,
        val athChangePercentage: Float
    )

}