package cn.yanjingtp.utils.utils

import android.app.Activity

/**
 * 一个专门对所有的活动（ Activity）进行管理
 */
object ActivityCollector {
    var activities = mutableListOf<Activity>()

    /**
     * 添加Activity
     * @param activity 添加的Activity对象
     */
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    /**
     * 删除Activity
     * @param activity 删除的Activity对象
     */
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    /**
     * 关闭指定的Activity
     * @param activityName 需要关闭的Activity类名,即activity.javaClass.name
     */
    fun finishOneActivity(vararg activityName: String) {
        //在activities集合中找到类名与指定类名相同的Activity就关闭
        val iterator = activities.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            val name = activity.javaClass.name//activity的类名
            if (activityName.contains(name)) {
                if (activity.isFinishing) {
                    iterator.remove()
                } else {
                    activity.finish()
                }
            }
        }
    }

    /**
     * 只保留某个Activity，关闭其他所有Activity
     * @param activityName 要保留的Activity,即activity.javaClass.name
     */
    fun finishOtherActivity(vararg activityName: String) {
        val iterator = activities.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            val name = activity.javaClass.name //activity的类名
            if (!activityName.contains(name)) {
                if (activity.isFinishing) {
                    iterator.remove()
                } else {
                    activity.finish()
                }
            }
        }
    }

    /**
     * 关闭所有Activity
     */
    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }


}