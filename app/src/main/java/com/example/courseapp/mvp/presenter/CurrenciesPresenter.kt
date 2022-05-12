package com.example.courseapp.mvp.presenter


import com.example.courseapp.adapter.CurrenciesAdapter
import com.example.courseapp.di.App
import com.example.courseapp.formatThousands
import com.example.courseapp.mvp.contract.CurrenciesContract
import com.example.courseapp.rest.CoinGeckoApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {

    //внедряем источник данных
    @Inject
    lateinit var geckoApi: CoinGeckoApi
    //инициализируем компоненты Даггера
    init {
        App.appComponent.inject(this)
    }

    //создаем список, загружая данные с помощью RxJava
    override fun makeList() {
        view.showProgress()
        subscribe(geckoApi.getCoinMarket()
            //определяем отдельный поток для отправки данных
            .subscribeOn(Schedulers.io())
            //получаем данные в основном потоке
            .observeOn(AndroidSchedulers.mainThread())
            //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
            .flatMap { Observable.fromIterable(it) }
            //наполняем поля элемента списка для адаптера
            .doOnNext {
                view.addCurrency(CurrenciesAdapter.Currency(
                    it.id,
                    it.symbol,
                    it.name,
                    it.image,
                    it.current_price,
                    it.market_cap.formatThousands(),
                    it.market_cap_rank,
                    it.total_volume,
                    it.price_change_percentage_24h,
                    it.market_cap_change_percentage_24h,
                    it.circulating_supply,
                    it.total_supply,
                    it.ath,
                    it.ath_change_percentage
                    )
                )
            }.doOnComplete{
                view.hideProgress()
            }
            //подписывает Observer на Observable
            .subscribe({
                view.hideProgress()
                view.notifyAdapter()
            }, {
                view.showErrorMessage(it.message)
                view.hideProgress()
                it.printStackTrace()
            })
        )
    }

    //обновляем список
    override fun refreshList() {
        view.refresh()
        makeList()
    }

}