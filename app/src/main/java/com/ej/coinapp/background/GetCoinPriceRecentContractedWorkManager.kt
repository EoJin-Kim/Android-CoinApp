package com.ej.coinapp.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ej.coinapp.db.entity.SelectedCoinPriceEntity
import com.ej.coinapp.network.model.RecentCoinPriceList
import com.ej.coinapp.repository.DBRepository
import com.ej.coinapp.repository.NetworkRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date

class GetCoinPriceRecentContractedWorkManager(val context : Context, workerParameters: WorkerParameters)
    : CoroutineWorker(context,workerParameters){

    private val dbRepository = DBRepository()
    private val netWorkRepository = NetworkRepository()
    override suspend fun doWork(): Result {
        Timber.d("doWork")
        getAllInterestSelectedCoinData()
        return Result.success()
    }

    private suspend fun getAllInterestSelectedCoinData(){
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        val timeStamp = Calendar.getInstance().time
        for (coinData in selectedCoinList) {

            Timber.d(coinData.toString())

            val recentCoinPriceList = netWorkRepository.getInterestCoinPriceData(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())
            saveSelectedCoinPrice(
                coinData.coin_name,
                recentCoinPriceList,
                timeStamp
            )
        }
    }

    private fun saveSelectedCoinPrice(
        coinName : String,
        recentCoinPriceList : RecentCoinPriceList,
        timeStamp : Date
    ){
        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp

        )
        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)
    }
}