package cn.yanjingtp.utils.utils

import android.content.Context
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 获取状态栏高度
 */
fun getBarHeight(context: Context = CtxUtil.getCtx()): Int {
    var statusBarHeight = 0 //获取status_bar_height资源的ID
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) { //根据资源ID获取响应的尺寸值
        statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}