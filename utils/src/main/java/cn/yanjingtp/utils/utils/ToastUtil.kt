package cn.yanjingtp.utils.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import cn.yanjingtp.utils.base.CtxUtil


object ToastUtil {
    private const val TOAST_DURATION = 2000
    private var lastShowTime = 0L
    private var lastShowMsg: String? = null
    private var curShowMsg: String? = null

    /**
     * @param context
     * @param message
     */
    fun show(context: Context?, message: String?) {
        if (isMainThread()) {
            show(context, message, Toast.LENGTH_SHORT, Gravity.BOTTOM)
        }else{
            //解决子线程中调用Toast失败问题
              Handler(Looper.getMainLooper()).post {
                show(context, message, Toast.LENGTH_SHORT, Gravity.BOTTOM)
            }
        }
    }

    fun showLong(context: Context?, message: String?) {
        if (isMainThread()) {
            show(context, message, Toast.LENGTH_LONG, Gravity.CENTER)
        }else{
            Handler(Looper.getMainLooper()).post{
                show(context, message, Toast.LENGTH_LONG, Gravity.CENTER)
            }
        }
    }

    /**
     * @param context
     * @param message
     */
    private fun show(context: Context?, message: String?, duration: Int, gravity: Int) {
        if (TextUtils.isEmpty(message)) {
            return
        }
        curShowMsg = message
        val curShowTime = System.currentTimeMillis()
        if (curShowMsg == lastShowMsg) {
            if (curShowTime - lastShowTime > TOAST_DURATION) { //后setText 兼容小米默认会显示app名称的问题
                val toast = Toast.makeText(context, "", duration)
                toast.setText(message)
                //toast.setGravity(gravity, 0, 0);
                toast.show()
                lastShowTime = curShowTime
                lastShowMsg = curShowMsg
            }
        } else { //后setText 兼容小米默认会显示app名称的问题
            val toast = Toast.makeText(context, "", duration)
            toast.setText(message)
            //toast.setGravity(gravity, 0, 0);
            toast.show()
            lastShowTime = curShowTime
            lastShowMsg = curShowMsg
        }
    }

    private fun isMainThread():Boolean = Looper.getMainLooper() == Looper.myLooper()
}

/**
 *  toast
 */
fun String.showLong(context: Context = CtxUtil.getCtx) {
    ToastUtil.showLong(context, this)
}

/**
 *  toast
 */
fun String.show(context: Context = CtxUtil.getCtx) {
    ToastUtil.show(context, this)
}