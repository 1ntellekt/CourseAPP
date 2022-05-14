package com.example.courseapp.chart

import android.content.Context
import android.graphics.Color
import com.example.courseapp.R
import com.example.courseapp.di.App
import com.example.courseapp.formatters.YearValueFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import javax.inject.Inject

class LatestChart {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var formatter: YearValueFormatter

    lateinit var chart: LineChart

    init {
        App.appComponent.inject(this)
    }

    /*
    Функция initChart инициализирует график. Здесь можно изменять такие параметры графика,
как описание, поддержку жестов, масштабирование и перетаскивание, причем
масштабирование может выполняться только по одной из осей
     */

    fun initChart(chart: LineChart){
        this.chart = chart
        chart.description.isEnabled = false
        // enable description text
        chart.description.isEnabled = false
        // enable touch gestures
        chart.setTouchEnabled(true)
        // enable scaling and dragging
        chart.isDragEnabled = true
        chart.setScaleEnabled(false)
        chart.isScaleXEnabled = true
        chart.setDrawGridBackground(false)
        chart.isDoubleTapToZoomEnabled = false
        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false)
        //Sets the maximum distance in screen dp a touch can be away from an entry to cause it to get highlighted
        chart.maxHighlightDistance = 300F
        val data = LineData()
        data.setValueTextColor(Color.BLACK)
        // add empty data
        chart.data = data
        // get the legend (only possible after setting data)
        chart.legend.isEnabled = true
        //add marker
        chart.setDrawMarkers(true)
        chart.marker = MyMarkerView(context, R.layout.custom_marker_view)
        val xl = chart.xAxis
        xl.textColor = Color.BLACK
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawGridLines(false)
        xl.valueFormatter = formatter
        xl.labelCount = 3
        xl.granularity = 48F
        xl.setAvoidFirstLastClipping(true)
        xl.isEnabled = true
        val leftAxis = chart.axisLeft
        leftAxis.textColor = Color.BLACK
        leftAxis.setDrawGridLines(true)
        val rightAxis = chart.axisRight
        rightAxis.isEnabled = true
    }

    //добавление данных на график
    //Функция addEntry добавляет данные на график, используя объект интерфейса ILineDataSet
    fun addEntry(value: Float, date: Float) {
        val data = chart.data
        if (data != null) {
            var set: ILineDataSet? = data.getDataSetByIndex(0)
            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }
            data.addEntry(Entry(date, value), 0)
            data.notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.moveViewToX(date)
            chart.highlightValue(date, 0)
        }
    }

    /*
    Функция createSet() настраивает и возвращает объект класса LineDataSet. Каждый DataSet
объект представляет группу записей (например, класс Entry) внутри графика, которые
принадлежат друг другу.
     */
    private fun createSet(): LineDataSet {
        val set = LineDataSet(null, "Price, USD")
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        set.setDrawFilled(true)
        set.setDrawCircles(false)
        set.lineWidth = 1.8f
        set.circleRadius = 4f
        set.setCircleColor(Color.BLACK)
        set.highlightLineWidth = 1.2f
        set.highLightColor = context.resources.getColor(R.color.teal_200)
        set.color = Color.BLACK
        set.fillColor = Color.BLACK
        set.enableDashedHighlightLine(10f, 5f, 0f)
        set.fillAlpha = 100
        set.setDrawHorizontalHighlightIndicator(true)
        set.setFillFormatter { _, _ ->
            chart.axisLeft.axisMinimum
        }
        return set
    }

}