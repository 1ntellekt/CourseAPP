package com.example.courseapp.rest

data class GeckoCoin(
    val ath: Float,
    val ath_change_percentage: Float,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Float,
    val fully_diluted_valuation: Long,
    val high_24h: Int,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Int,
    val market_cap: Float,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Float,
    val market_cap_rank: Int,
    val max_supply: Int,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Float,
    val roi: Any,
    val symbol: String,
    val total_supply: Long,
    val total_volume: Float
)

data class GeckoCoinChart (var prices: List<List<Float>>)