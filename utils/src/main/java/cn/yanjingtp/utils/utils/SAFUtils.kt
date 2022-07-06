package cn.yanjingtp.utils.utils

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import cn.yanjingtp.utils.base.CtxUtil
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * 存储访问框架 (SAF)相关
 * 1.在onCreate中注册registerForActivityResult
 * 2.调用getSAFGrant判断目标目录是否有权限
 * 3.没有权限调用getSAFGrant
 * 4.当有权限的时候调用getDocumentFilePath获取name,uri等信息
 */

/**
 * 判断是否已经获取了指定Data目录权限
 * @param targetPath 目标目录 如:Android/data/com.tencent.mm/MicroMsg/Download
 */
fun getSAFGrant(targetPath: String): Boolean {
    for (persistedUriPermission in CtxUtil.getCtx.contentResolver.persistedUriPermissions) {
        if (persistedUriPermission.isReadPermission && persistedUriPermission.uri.toString() == "content://com.android.externalstorage.documents/tree/primary%3A${
                    URLEncoder.encode(targetPath, StandardCharsets.UTF_8.name())
                }") {
            return true
        }
    }
    return false
}

/**
 * 获取指定Data目录权限
 * @param targetPath 目标目录 如:Android/data/com.tencent.mm/MicroMsg/Download
 */
fun getSAFGrant(targetPath: String, startActivity: ActivityResultLauncher<Intent>?) {
    val uri: String =  changeToUri(targetPath)
    val parse = Uri.parse(uri)
    val intent = Intent("android.intent.action.OPEN_DOCUMENT_TREE")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION or Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, parse)
    }
    startActivity?.launch(intent)
}

/**
 * 在onActivityResult中的回调,并保存这个目录的访问权限
 * 此方法需要onCreate中先初始化
 */
@SuppressLint("WrongConstant")
fun registerForActivityResult(activity: AppCompatActivity): ActivityResultLauncher<Intent> {
    return activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        //保存这个目录的访问权限
        activityResult.data?.data?.let {
            CtxUtil.getCtx.contentResolver.takePersistableUriPermission(it,
                (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION) and activityResult.data!!.flags)
        }
    }
}

/**
 * 直接返回DocumentFile,可通过DocumentFile获取到name,uri等信息
 * @param targetPath 目标目录 如:Android/data/com.tencent.mm/MicroMsg/Download
 */
fun getDocumentFilePath(targetPath: String): DocumentFile? {
    return DocumentFile.fromTreeUri(CtxUtil.getCtx, Uri.parse(changeToUri(targetPath)))
}

/**
 * 转换至uriTree的路径
 */
private fun changeToUri(path: String):String {
    val dealPath =   if (path.startsWith("/storage/emulated/0/")) path.replace("/storage/emulated/0/","") else path
    return "content://com.android.externalstorage.documents/tree/primary%3A${URLEncoder.encode(dealPath, StandardCharsets.UTF_8.name())}"
}