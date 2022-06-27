package cn.yanjingtp.utils.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import cn.yanjingtp.utils.base.CtxUtil


/**
 *获取颜色
 */
fun getColor(context: Context,@ColorRes id: Int): Int {
    return id.getColor(context)
}

/**
 *获取颜色
 */
fun Int.getColor(context: Context = CtxUtil.getCtx()):Int{
    return ContextCompat.getColor(context, this)
}