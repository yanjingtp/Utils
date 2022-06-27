package cn.yanjingtp.utils.utils

import android.content.Context
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun dp2px(dpValue: Float): Int {
    val scale = CtxUtil.getCtx().resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 */
fun px2dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
 */
fun px2dp(pxValue: Float): Int {
    val scale = CtxUtil.getCtx().resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}