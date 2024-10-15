package com.shiromadev.personalbudget.api.view.piechart.model

interface AnalyticalPieChartInterface {
	fun setDataChart(list: List<Pair<Int, String>>)
	fun startAnimation()
}