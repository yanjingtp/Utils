package cn.yanjingtp.utils.base

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.startup.Initializer
import java.lang.IllegalStateException

/**
 * 无侵入方式获取全局context
 * 在多进程中使用前需先调用AppInitializer.getInstance(context).initializeComponent(CxtUtil::class.java)
 */
class CtxUtil : Initializer<Unit> {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null
        val getCtx: Context by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            if (mContext == null) {
                throw IllegalStateException("在多进程中使用前需先调用AppInitializer.getInstance(context).initializeComponent(CxtUtil::class.java)")
            }
            mContext!!
        }
    }
    override fun create(context: Context) {
        mContext = context
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return emptyList<Class<out Initializer<*>>>().toMutableList()
    }

}