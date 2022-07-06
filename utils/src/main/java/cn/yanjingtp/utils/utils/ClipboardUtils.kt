package cn.yanjingtp.utils.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 复制文本到剪贴板
 */
fun copyText(context: Context,text: CharSequence?) {
    val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText(context.packageName, text))
}

/**
 * 复制文本到剪贴板
 */
fun copyText(text: CharSequence?) {
    val cm = CtxUtil.getCtx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText(CtxUtil.getCtx.packageName, text))
}

/**
 * 获取剪贴板最近的文本
 */
fun getText(context: Context): CharSequence {
    val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = cm.primaryClip
    if (clip != null && clip.itemCount > 0) {
        val text = clip.getItemAt(0).coerceToText(context)
        if (text != null) {
            return text
        }
    }
    return ""
}

/**
 * 获取剪贴板最近的文本
 */
fun getText(): CharSequence {
    val cm = CtxUtil.getCtx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = cm.primaryClip
    if (clip != null && clip.itemCount > 0) {
        val text = clip.getItemAt(0).coerceToText(CtxUtil.getCtx)
        if (text != null) {
            return text
        }
    }
    return ""
}