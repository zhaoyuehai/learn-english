package com.yuehai.learn.english.util

import java.util.regex.Pattern

object StringUtils {
    /**
     * 2-20位
     */
    fun isNickName(nickName: String): Boolean {
        if (nickName.isEmpty()) return false
        return nickName.matches(Regex("^[\\u4E00-\\u9FA5A-Za-z0-9]{2,20}$"))
    }

    /**
     * 4-16位字母、数字、下划线
     */
    fun isUserName(userName: String): Boolean {
        if (userName.isEmpty()) return false
        return userName.matches(Regex("^[a-zA-Z0-9_-]{4,16}$"))
    }

    /**
     * 6-16位数字或字母
     */
    fun isPassword(password: String): Boolean {
        if (password.isEmpty()) return false
        return password.matches(Regex( "^[0-9a-zA-Z]{6,16}$"))
    }

    fun isEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        return email.matches(Regex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
    }

    /**
     * 大陆号码或香港号码均可
     */
    fun isPhoneLegal(str: String): Boolean {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str)
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    fun isChinaPhoneLegal(str: String): Boolean {
        val regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(str)
        return m.matches()
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    fun isHKPhoneLegal(str: String): Boolean {
        val regExp = "^(5|6|8|9)\\d{7}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(str)
        return m.matches()
    }
}