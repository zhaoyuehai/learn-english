package com.yuehai.learn.english.service.impl

import ResultUtil
import com.github.pagehelper.PageHelper
import com.yuehai.learn.english.bean.*
import com.yuehai.learn.english.entity.WordEntity
import com.yuehai.learn.english.entity.WordExtraEntity
import com.yuehai.learn.english.mapper.WordExtraMapper
import com.yuehai.learn.english.mapper.WordMapper
import com.yuehai.learn.english.mapper.WordMarkMapper
import com.yuehai.learn.english.service.WordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*


@Service
class WordServiceImpl : WordService {

    @Autowired
    private lateinit var wordMapper: WordMapper

    @Autowired
    private lateinit var wordMarkMapper: WordMarkMapper

    @Autowired
    private lateinit var wordExtraMapper: WordExtraMapper

    override fun selectWord(date: String?, pageNum: Int, pageSize: Int): ResultBean {
        val page = PageHelper.startPage<WordEntity>(pageNum, pageSize)
        var year: String? = null
        var month: String? = null
        if (date != null && date.contains("-")) {
            val array = date.split("-")
            year = array[0]
            month = array[1]
        }
        val list = mutableListOf<WordBean>()
        if (year.isNullOrEmpty() || month.isNullOrEmpty()) wordMapper.selectWord() else wordMapper.selectWordLimitDate(year, month).forEach {
            if (it.id != null && it.createTime != null) list.add(WordBean(it.id, it.contentEN, it.contentCN, it.createTime.substring(0, 10)))
        }
        return ResultUtil.success(PageWrapperBean(page.total, page.pages, list))
    }

    override fun insertWord(wordBean: WordBean): ResultBean {
        val entity = WordEntity(id = null, contentEN = wordBean.contentEN, contentCN = wordBean.contentCN, createTime = wordBean.createTime)
        val res = wordMapper.insertWord(entity)
        return if (res > 0) {
            ResultUtil.success(entity.id)
        } else {
            ResultUtil.fail("添加失败")
        }
    }

    override fun updateWord(wordBean: WordBean): ResultBean {
        val res = wordMapper.updateWord(WordEntity(id = wordBean.id, contentEN = wordBean.contentEN, contentCN = wordBean.contentCN))
        return if (res > 0) {
            ResultUtil.success(res, "修改成功")
        } else {
            ResultUtil.fail("修改失败")
        }
    }

    override fun deleteWord(id: Long): ResultBean {
        wordMarkMapper.deleteWordMarkByWordId(id)
        val res = wordMapper.deleteWord(id)
        return if (res > 0) {
            ResultUtil.success(res, "删除成功")
        } else {
            ResultUtil.fail("删除失败")
        }
    }

    override fun deleteWords(ids: LongArray): ResultBean {
        wordMarkMapper.deleteWordMarkByWordIds(ids)
        val res = wordMapper.deleteWords(ids)
        return if (res > 0) {
            ResultUtil.success(res, "删除成功")
        } else {
            ResultUtil.fail("删除失败")
        }
    }

    override fun insertExtraWord(wordExtraBean: WordExtraBean): ResultBean {
        val entity = WordExtraEntity(null, wordExtraBean.wordId, wordExtraBean.contentEN, wordExtraBean.contentCN, null)
        val res = wordExtraMapper.insertWordExtra(entity)
        return if (res > 0) {
            ResultUtil.success(entity.id)
        } else {
            ResultUtil.fail("添加失败")
        }
    }

    override fun selectExtraWords(wordId: Long): ResultBean {
        val list = mutableListOf<WordExtraBean>()
        wordExtraMapper.selectWordExtra(wordId).forEach {
            if (it.id != null) list.add(WordExtraBean(it.id, wordId, it.contentEN, it.contentCN))
        }
        return ResultUtil.success(list)
    }

    override fun randomWords(count: Int?, startDate: String?, endDate: String?): ResultBean {
        val list = mutableListOf<WordBean>()
        wordMapper.selectRandomWord(count ?: 1,
                SimpleDateFormat("yyyy-MM-dd").parse(if (startDate.isNullOrEmpty()) "2020-05-01" else startDate),
                if (endDate.isNullOrEmpty()) Date() else SimpleDateFormat("yyyy-MM-dd").parse(endDate)
        ).forEach {
            if (it.id != null && it.createTime != null) list.add(WordBean(it.id, it.contentEN, it.contentCN, it.createTime.substring(0, 10)))
        }
        return ResultUtil.success(list)
    }

    override fun randomWords(count: Int?, monthDate: String?): ResultBean {
        val dateFormat = SimpleDateFormat("yyyy-MM")
        val dateStr: String
        val date: Date
        if (monthDate.isNullOrEmpty()) {
            date = Date()
            dateStr = dateFormat.format(date)
        } else {
            dateStr = monthDate
            date = dateFormat.parse(monthDate)
        }
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        return randomWords(count, "$dateStr-01", "$dateStr-${calendar.getActualMaximum(Calendar.DAY_OF_MONTH)}")
    }

    override fun markWord(userPhone: String, wordMarkBean: WordMarkBean): ResultBean {
        val entity = wordMarkMapper.selectWordMark(userPhone, wordMarkBean.wordId)
        val res = if (entity != null) {
            var markCount = entity.markCount + (if (wordMarkBean.markUp) 1 else -1)
            if (markCount < -10) markCount = -10 else if (markCount > 10) markCount == 10
            wordMarkMapper.updateWordMark(entity.id, markCount)
        } else {
            wordMarkMapper.insertWordMark(userPhone, wordMarkBean.wordId, if (wordMarkBean.markUp) 1 else 0)
        }
        //学习记录+1
        val learnTime = wordMarkMapper.selectWordLearnRecord(userPhone, Date())
        if (learnTime != null) {
            wordMarkMapper.updateWordLearnRecord(learnTime.id, learnTime.count + 1)
        } else {
            wordMarkMapper.insertWordLearnRecord(userPhone)
        }
        return if (res > 0) ResultUtil.success() else ResultUtil.fail("操作失败")
    }

    override fun selectWordMarks(userPhone: String, pageNum: Int, pageSize: Int) = ResultUtil.success(wordMarkMapper.selectWordMarks(userPhone))

    override fun selectWordLearnRecords(userPhone: String) = ResultUtil.success(wordMarkMapper.selectWordLearnRecords(userPhone))
}