package com.ej.coinapp.network.model

import com.ej.coinapp.dataModel.RecentPriceData

data class RecentCoinPriceList (
    val status : String,
    val data : List<RecentPriceData>
)