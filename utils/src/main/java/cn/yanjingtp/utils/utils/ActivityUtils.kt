package cn.yanjingtp.utils.utils

import android.content.Context
import android.content.Intent
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 回到桌面
 */
fun startHomeActivity(context: Context = CtxUtil.getCtx()) {
    val homeIntent = Intent(Intent.ACTION_MAIN)
    homeIntent.addCategory(Intent.CATEGORY_HOME)
    homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(homeIntent)
}

/**
 * 判断Intent 是否有效
 */
fun isValidIntent(context: Context, intent: Intent?): Boolean {
    val packageManager = context.packageManager
    val activities = packageManager.queryIntentActivities(intent!!, 0)
    return activities.isNotEmpty()
}

/**
 * 判断Intent 是否有效
 */
fun isValidIntent(intent: Intent?): Boolean {
    val packageManager = CtxUtil.getCtx().packageManager
    val activities = packageManager.queryIntentActivities(intent!!, 0)
    return activities.isNotEmpty()
}


