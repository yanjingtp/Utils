package cn.yanjingtp.utils.utils

import android.content.Context
import androidx.annotation.StringRes
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 从String中获取
 */
fun Int.getString(context: Context = CtxUtil.getCtx()): String {
    return context.resources.getString(this)
}

/**
 * 从String中获取
 */
fun getString(context: Context,@StringRes resId: Int):String {
  return  resId.getString(context)
}
