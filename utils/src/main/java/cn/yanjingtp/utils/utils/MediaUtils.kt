package cn.yanjingtp.utils.utils

import android.content.Context
import android.media.MediaScannerConnection
import cn.yanjingtp.utils.base.CtxUtil

import java.io.File


/**
 * 更新媒体库
 */
fun refresh(cxt: Context, vararg filePaths: String) {
    MediaScannerConnection.scanFile(cxt.applicationContext, filePaths, null, null)
}

/**
 * 更新媒体库
 */
fun refresh(vararg filePaths: String) {
    MediaScannerConnection.scanFile(CtxUtil.getCtx().applicationContext, filePaths, null, null)
}

/**
 * 更新媒体库
 */
fun refresh(cxt: Context, vararg files: File) {
    for (file in files) {
        val filePath: String = file.absolutePath
        refresh(cxt, filePath)
    }
}
/**
 * 更新媒体库
 */
fun refresh(vararg files: File) {
    for (file in files) {
        val filePath: String = file.absolutePath
        refresh(filePath)
    }
}

/**
 * 更新媒体库
 */
fun refresh(cxt: Context, filePathList: List<String>) {
    for (filePath in filePathList) {
        refresh(cxt, filePath)
    }
}

/**
 * 更新媒体库
 */
fun refresh(filePathList: List<String>) {
    for (filePath in filePathList) {
        refresh(filePath)
    }
}