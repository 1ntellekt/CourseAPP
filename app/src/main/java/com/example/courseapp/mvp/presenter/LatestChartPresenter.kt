package com.example.courseapp.mvp.presenter

import com.example.courseapp.di.App
import com.example.courseapp.mvp.contract.LatestChartContract
import com.example.courseapp.rest.CoinGeckoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LatestChartPresenter:LatestChartContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoApi

    init {
        App.appComponent.inject(this)
    }

    override fun makeChart(id: String) {
        view.showProgress()
        subscribe(geckoApi.getCoinMarketChart(id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.prices }
            .flatMap { Observable.fromIterable(it) }
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.addEntryToChart(it[0], it[1])
            },{
                view.hideProgress()
                view.showErrorMessage(it.message)
                it.printStackTrace()
            })
        )
    }

    override fun refreshChart() {
        view.refresh()
    }

}