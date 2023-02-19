package com.ej.coinapp.repository

import com.ej.coinapp.network.Api
import com.ej.coinapp.network.RetrofitInstance

class NetworkRepository {
    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()
    suspend fun getInterestCoinPriceData(coin : String) = client.getRecentCoinPrice(coin)

}