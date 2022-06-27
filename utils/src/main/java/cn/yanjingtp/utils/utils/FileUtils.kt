package cn.yanjingtp.utils.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.channels.FileChannel

/**
 * 复制单个文件
 *
 * @param oldPathName String 原文件路径+文件名 如：data/user/0/com.test/files/abc.txt
 * @param newPathName String 复制后路径+文件名 如：data/user/0/com.test/cache/abc.txt
 * @return `true` if and only if the file was copied;
 * `false` otherwise
 */
fun copyFile(oldPathName: String, newPathName: String): Boolean {
    var fileInputStream: FileInputStream? = null
    var fileOutputStream: FileOutputStream? = null
    var inputChannel: FileChannel? = null
    var outputChannel: FileChannel? = null
    return try {
        val oldFile = File(oldPathName)
        if (!oldFile.exists()) {
            "copyFile:  oldFile not exist.".logE()
            return false
        } else if (!oldFile.isFile) {
            "copyFile:  oldFile not file.".logE()
            return false
        } else if (!oldFile.canRead()) {
            "copyFile:  oldFile cannot read.".logE()
            return false
        }

        //如果没有目录,先创建
        File(newPathName).parent?.let {
            if (!File(it).exists()) {
                File(it).mkdirs()
            }
        }

        fileInputStream = FileInputStream(oldPathName)
        fileOutputStream = FileOutputStream(newPathName)
        inputChannel = fileInputStream.channel
        outputChannel = fileOutputStream.channel
        inputChannel.transferTo(0, inputChannel.size(), outputChannel)

        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    } finally {
        fileInputStream?.close()
        fileOutputStream?.close()
        inputChannel?.close()
        outputChannel?.close()
    }
}

/**
 * 从Uri 复制单个文件
 */
fun copyFileFromUri(context: Context, oldUri: Uri, newPathName: String): Boolean {
    var inputStream: InputStream? = null
    var fileInputStream: FileInputStream? = null
    var fileOutputStream: FileOutputStream? = null
    var inputChannel: FileChannel? = null
    var outputChannel: FileChannel? = null

    //如果没有目录,先创建
    File(newPathName).parent?.let {
        if (!File(it).exists()) {
            File(it).mkdirs()
        }
    }

    return try {
        //如果olrUri SCHEME_FILE = "file"则使用fileChannel处理,以加快速度
        if (ContentResolver.SCHEME_FILE == oldUri.scheme) {
            fileInputStream = FileInputStream(oldUri.path)

            fileOutputStream = FileOutputStream(newPathName)
            inputChannel = fileInputStream.channel
            outputChannel = fileOutputStream.channel
            inputChannel.transferTo(0, inputChannel.size(), outputChannel)

            true

        } else {
            inputStream = context.contentResolver.openInputStream(oldUri) //如果没有目录,先创建

            inputStream?.let {
                fileOutputStream = FileOutputStream(newPathName)
                val buffer = ByteArray(1024)
                var byteRead: Int
                while (-1 != inputStream.read(buffer).also { byteRead = it }) {
                    fileOutputStream?.write(buffer, 0, byteRead)
                }

                true
            }
        }

        false

    } catch (e: Exception) {
        e.printStackTrace()
        false
    } finally {
        inputStream?.close()
        fileInputStream?.close()
        fileOutputStream?.flush()
        fileOutputStream?.close()
        inputChannel?.close()
        outputChannel?.close()
    }
}


