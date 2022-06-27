package cn.yanjingtp.utils.base

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri

/**
 * 无侵入方式获取全局context
 */
class CtxUtil : ContentProvider() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null
        fun getCtx(): Context {
            return mContext ?: throw IllegalAccessException("Cannot find context")
        }

    }

    override fun onCreate(): Boolean {
        mContext = context
        return false
    }

    override fun query(uri: Uri,
            projection: Array<out String>?,
            selection: String?,
            selectionArgs: Array<out String>?,
            sortOrder: String?): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(uri: Uri,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<out String>?): Int = 0

}