package cn.yanjingtp.utils.utils

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import cn.yanjingtp.utils.base.CtxUtil


/**
 * 消息通知是否开启
 */
fun isNotificationEnabled(context: Context? = CtxUtil.getCtx()): Boolean {
    val notificationManagerCompat = NotificationManagerCompat.from(context!!)
    return notificationManagerCompat.areNotificationsEnabled()
}
