package com.shiromadev.personalbudget.api.util

import java.util.AbstractMap

fun convert(arrayList: List<AbstractMap.SimpleEntry<Int, String>>): List<Pair<Int, String>> {
	val list = ArrayList<Pair<Int, String>>()
	for (item in arrayList) {
		list.add(item.toPair())
	}
	return list
}