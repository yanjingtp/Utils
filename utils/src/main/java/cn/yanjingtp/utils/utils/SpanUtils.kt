package cn.yanjingtp.utils.utils

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan

/**
 * 富文本
 * @param target 需要展示富文本文字
 * @param origin 原始文本
 */
fun getSpannableString(target:String,origin:String,color:String): SpannableString {
    val start = origin.indexOf(target)
    val spannableString = SpannableString(origin)
    spannableString.setSpan(ForegroundColorSpan(Color.parseColor(color)),start,start+target.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}