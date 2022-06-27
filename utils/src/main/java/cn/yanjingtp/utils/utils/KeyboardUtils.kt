package cn.yanjingtp.utils.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * 隐藏键盘
 */
fun hideKeyBoard(activity: Activity) {
    val im = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(activity.window.peekDecorView().windowToken, 0)
}