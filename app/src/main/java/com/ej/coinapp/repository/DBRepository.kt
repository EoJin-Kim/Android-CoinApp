package com.ej.coinapp.repository

import com.ej.coinapp.App
import com.ej.coinapp.db.CoinPriceDatabase
import com.ej.coinapp.db.entity.InterestCoinEntity
import com.ej.coinapp.db.entity.SelectedCoinPriceEntity
import kotlinx.coroutines.flow.Flow

class DBRepository {

    val context = App.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoinData() : Flow<List<InterestCoinEntity>> = db.interestCoinDAO().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() : List<InterestCoinEntity> = db.interestCoinDAO().getSelectedData()

    fun getAllCoinPriceData() = db.selectedCoinDAO().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity) = db.selectedCoinDAO().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db.selectedCoinDAO().getOneCoinData(coinName)

}