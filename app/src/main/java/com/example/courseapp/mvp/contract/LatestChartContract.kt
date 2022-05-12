package com.example.courseapp.mvp.contract

class LatestChartContract {

    interface View:BaseContract.View{
        fun addEntryToChart(value: Float, date: String = "")
        fun addEntryToChart(date: Float, value: Float)
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
    }

    abstract class Presenter:BaseContract.Presenter<View>(){
        abstract fun makeChart(id: String)
        abstract fun refreshChart()
    }
}

/*
В соответствии с концепцией MVP, элемент view должен быть максимально “тупым”, в нем
мы производим только элементарные действия, такие, как, например, добавить элементы в
список/на график, показать/спрятать значок загрузки и т.д. Методы, соответствующие этому
элементу, мы определяем в интерфейсе View контракта.
Логикой мы занимаемся в презентере – бизнес-логика, манипуляции данными,
запуск фоновых задач и т.д. В нем же мы решаем, когда показать те или иные элементы на экране.
Методы, соответствующие презентеру, мы определяем в абстрактном классе Presenter контракта.
При реализации презентера, манипуляции видом будут производиться через переменную view суперкласса
BaseContract.Presenter.
 */