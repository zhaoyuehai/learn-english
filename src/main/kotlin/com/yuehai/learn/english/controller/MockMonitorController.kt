package com.yuehai.learn.english.controller

import ResultUtil
import com.alibaba.druid.support.json.JSONUtils
import com.alibaba.fastjson.JSON
import com.yuehai.learn.english.bean.PvCapabilityDiagramsParams
import com.yuehai.learn.english.bean.PvStationSummaryParams
import com.yuehai.learn.english.bean.StationNumSummaryBean
import com.yuehai.learn.english.bean.StationSummaryBean
import com.yuehai.learn.english.util.JsonUtil
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.boot.system.ApplicationHome
import org.springframework.web.bind.annotation.*
import java.text.DecimalFormat

@RestController
@RequestMapping("api/v3/monitor")
class MockMonitorController : BaseController() {

    @ApiOperation("电站实时数据汇总")
    @ApiImplicitParam(name = "UTCString", value = "UTC字符串(yyyy-MM-dd’T’HH:mm:ss’Z’)", paramType = "path", dataType = "String", required = true)
    @GetMapping("stations/{UTCString}")
    fun stationSummary(@PathVariable UTCString: String) = ResultUtil.success(data = StationSummaryBean(
            randomValue(587520.0),
            randomValue(698584.0),
            randomValue(39117.0),
            randomValue(50.0, 40) + "%",
            1,
            randomValue(3928.0),
            randomValue(1008.0),
            randomValue(845.0),
            StationNumSummaryBean(22, 64),
            3974, null
    ))

    @ApiOperation("风电站数据汇总")
    @ApiImplicitParam(name = "UTCString", value = "UTC字符串(yyyy-MM-dd’T’HH:mm:ss’Z’)", paramType = "path", dataType = "String", required = true)
    @GetMapping("wind/stations/{UTCString}")
    fun windStationSummary(@PathVariable UTCString: String): Any {
        val jsonString = getJsonFromFile("WindStationSummary") ?: return ResultUtil.fail("暂无数据")
        return JSONUtils.parse(jsonString)
    }

    @ApiOperation("光伏电站实时数据汇总")
    @PostMapping("pv/stations")
    fun pvStationSummary(@RequestBody params: PvStationSummaryParams): Any {
        val jsonString = getJsonFromFile("PvStationSummary") ?: return ResultUtil.fail("暂无数据")
        return JSON.parse(jsonString)
    }

    @ApiOperation("光伏各电站出力图")
    @PostMapping("pv/stations/capabilitydiagrams")
    fun pvCapabilityDiagrams(@RequestBody params: PvCapabilityDiagramsParams): Any {
        val jsonString = getJsonFromFile("PvCapabilityDiagrams") ?: return ResultUtil.fail("暂无数据")
        return JSON.parse(jsonString)
    }


    fun randomValue(value: Int, deviation: Int? = null): Int {
        val devi = deviation ?: value / 2
        return value + (-devi..devi).random()
    }

    fun randomValue(value: Double, deviation: Int? = null): String {
        val devi: Int = deviation ?: (value / 2).toInt()
        val result = value + (-devi..devi).random() + (-10..10).random() / 10.0 + (-10..10).random() / 100.0
        return DecimalFormat("######0.00").format(result)
    }

    fun getJsonFromFile(jsonFileName: String): String? {
        val filePath = "${ApplicationHome().dir.path}/mock/monitor/$jsonFileName.json"
        return JsonUtil.readJsonFile(filePath)
    }
}