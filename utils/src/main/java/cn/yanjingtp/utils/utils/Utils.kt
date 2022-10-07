package cn.yanjingtp.utils.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by yangyanjing on 2022-10-07 17:12
 * Describe:
 */

/**
 *  判断手机的网络状态（是否联网）-1 表示未联网
 */
fun getNetWorkInfo(context: Context): Int {
    var type = -1
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo ?: return type
    val type1 = activeNetworkInfo.type
    type = when (type1) {
        ConnectivityManager.TYPE_MOBILE -> 0
        ConnectivityManager.TYPE_WIFI -> 1
        else -> -1
    }
    return type
}

/**
 * 获取首字母大写(将String全部转成小写)
 */
fun String?.getFirstUpper(): String? {
    this?.apply {
        val chars = this.lowercase().toCharArray()
        chars[0] = chars[0] - 32
        return String(chars)
    }
    return null
}