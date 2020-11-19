package com.yuehai.learn.english.bean

data class StationSummaryBean(
        val yearPower: String,
        val yearPlanPower: String,
        val monthPower: String,
        val yearPlanRate: String,
        val planStatus: Int,
        val stationCapacity: String,
        val stationPower: String,
        val dayPower: String,
        val stationTypeSummary: StationNumSummaryBean,
        val stationUnitCount: Int,
        val instantaneous: String?
)

data class StationNumSummaryBean(
        val lightStationNum: Int,
        val windStationNum: Int
)