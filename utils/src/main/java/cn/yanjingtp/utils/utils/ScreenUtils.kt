package cn.yanjingtp.utils.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 获取屏幕宽度
 */
fun getScreenWidth(context: Context = CtxUtil.getCtx()): Int {
    val metrics = DisplayMetrics()
    (context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(
        metrics)
    return metrics.widthPixels
}

/**
 * 获取屏幕高度
 */
fun getScreenHeight(context: Context = CtxUtil.getCtx()): Int {
    val metrics = DisplayMetrics()
    (context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(
        metrics)
    return metrics.heightPixels
}