package com.shiromadev.personalbudget.api.view.piechart.model

import android.os.Parcelable
import android.view.View

class AnalyticalPieChartState(
	private val superSavedState: Parcelable?,
	val dataList: List<Pair<Int, String>>
) : View.BaseSavedState(superSavedState), Parcelable {
}