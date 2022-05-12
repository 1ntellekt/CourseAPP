package com.example.courseapp.mvp.contract

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract {

    interface View

    abstract class Presenter<V: View> {
        private val subscriptions = CompositeDisposable()
        protected lateinit var view:V

        fun subscribe(subscription: Disposable) {
            subscriptions.add(subscription)
        }
        fun unsubscribe() {
            subscriptions.clear()
        }
        fun attach(view: V) {
            this.view = view
        }
        fun detach() {
            unsubscribe()
        }
    }

}

/*
    Это базовый класс, в нем определен абстрактный презентер и его методы —
    подписка/отписка на поток данных, прикрепление/открепление View
*/