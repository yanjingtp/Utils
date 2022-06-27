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
     * @param activityName 需要关闭的Activity类名
     */
    fun finishOneActivity(vararg activityName: String) {
        //在activities集合中找到类名与指定类名相同的Activity就关闭
        for (activity in activities) {
            val name = activity.javaClass.name//activity的类名
            if (activityName.contains(name)) {
                if(activity.isFinishing){
                    activities.remove(activity)
                }else{
                    activity.finish()
                }
            }
        }
    }

    /**
     * 只保留某个Activity，关闭其他所有Activity
     * @param activityName 要保留的Activity类名
     */
    fun finishOtherActivity(vararg activityName: String) {
        for (activity in activities) {
            val name = activity.javaClass.name //activity的类名
            if (!activityName.contains(name)) {
                if(activity.isFinishing){
                    activities.remove(activity)
                }else{
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