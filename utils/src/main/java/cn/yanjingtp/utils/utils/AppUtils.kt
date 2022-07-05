package cn.yanjingtp.utils.utils

import android.app.ActivityManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageInstaller
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import cn.yanjingtp.utils.base.CtxUtil
import java.io.*
import java.lang.reflect.Constructor
import java.lang.reflect.Method

/**
 * 获取 App 图标
 */
fun getAppIcon(context: Context, packageName: String?): Drawable? {
    return if (packageName.isNullOrEmpty()) null else try {
        val pm: PackageManager = context.packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.applicationInfo?.loadIcon(pm)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }
}

/**
 * 获取 App 图标
 */
fun getAppIcon(packageName: String?): Drawable? {
    return if (packageName.isNullOrEmpty()) null else try {
        val pm: PackageManager = CtxUtil.getCtx().packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.applicationInfo?.loadIcon(pm)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        null
    }
}

/**
 * 获取 App 名称
 */
fun getAppName(context: Context, packageName: String?): String {
    return if (packageName.isNullOrEmpty()) "" else try {
        val pm: PackageManager = context.packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.applicationInfo?.loadLabel(pm)?.toString() ?: ""
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

/**
 * 获取 App 名称
 */
fun getAppName(packageName: String?): String {
    return if (packageName.isNullOrEmpty()) "" else try {
        val pm: PackageManager = CtxUtil.getCtx().packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.applicationInfo?.loadLabel(pm)?.toString() ?: ""
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

/**
 * 获取 App 版本号
 */
fun getAppVersionName(context: Context, packageName: String?): String {
    return if (packageName.isNullOrEmpty()) "" else try {
        val pm: PackageManager = context.packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        if (pi == null) "" else pi.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

/**
 * 获取 App 版本号
 */
fun getAppVersionName(packageName: String?): String {
    return if (packageName.isNullOrEmpty()) "" else try {
        val pm: PackageManager = CtxUtil.getCtx().packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        if (pi == null) "" else pi.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        ""
    }
}

/**
 * 获取 App 版本码
 */
fun getAppVersionCode(context: Context, packageName: String?): Int {
    return if (packageName.isNullOrEmpty()) -1 else try {
        val pm: PackageManager = context.packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.versionCode ?: -1
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        -1
    }
}
/**
 * 获取 App 版本码
 */
fun getAppVersionCode(packageName: String?): Int {
    return if (packageName.isNullOrEmpty()) -1 else try {
        val pm: PackageManager = CtxUtil.getCtx().packageManager
        val pi = pm.getPackageInfo(packageName, 0)
        pi?.versionCode ?: -1
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        -1
    }
}

/**
 * 判断应用是否首次安装
 */
fun isFirstTimeInstalled(context: Context,packageName: String?): Boolean {
    return if (packageName.isNullOrEmpty()) false else try {
        val pi: PackageInfo = context.packageManager
                .getPackageInfo(packageName, 0)
        pi.firstInstallTime == pi.lastUpdateTime
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        true
    }
}

/**
 * 判断应用是否首次安装
 */
fun isFirstTimeInstalled(packageName: String?): Boolean {
    return if (packageName.isNullOrEmpty()) false else try {
        val pi: PackageInfo = CtxUtil.getCtx().packageManager
                .getPackageInfo(packageName, 0)
        pi.firstInstallTime == pi.lastUpdateTime
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        true
    }
}

/**
 *   静默安装
 *   @param apkPath 需要安装的apk所在位置
 */
@WorkerThread
fun silentInstall(context: Context, apkPath: String): Boolean {
    return if (Build.VERSION.SDK_INT < 28) {
        installBelow28(context.packageManager, apkPath)
    } else {
        installOver28(context, apkPath)
    }
}

/**
 *   静默安装
 *   @param apkPath 需要安装的apk所在位置
 */
@WorkerThread
fun silentInstall(apkPath: String): Boolean {
    return if (Build.VERSION.SDK_INT < 28) {
        installBelow28(CtxUtil.getCtx().packageManager, apkPath)
    } else {
        installOver28(CtxUtil.getCtx(), apkPath)
    }
}


//静默安装 9.0以下
private fun installBelow28(packageManager: PackageManager, apkPath: String?): Boolean {
    val pmClz: Class<*> = packageManager.javaClass
    try {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 28) {
            val aClass = Class.forName("android.utils.PackageInstallObserver")
            val constructor: Constructor<*> = aClass.getDeclaredConstructor()
            constructor.isAccessible = true
            val installObserver: Any = constructor.newInstance()
            val method: Method = pmClz.getDeclaredMethod("installPackage",
                Uri::class.java,
                aClass,
                Int::class.javaPrimitiveType,
                String::class.java)
            method.isAccessible = true
            method.invoke(packageManager, Uri.fromFile(File(apkPath)), installObserver, 2, null)
        } else {
            val method: Method = pmClz.getDeclaredMethod("installPackage",
                Uri::class.java,
                Class.forName("android.content.pm.IPackageInstallObserver"),
                Int::class.javaPrimitiveType,
                String::class.java)
            method.isAccessible = true
            method.invoke(packageManager, Uri.fromFile(File(apkPath)), null, 2, null)
        }
        return true
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return false
}

//静默安装 9.0及以上
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
private fun installOver28(context: Context, apkPath: String?): Boolean {
    val packageInstaller = context.packageManager.packageInstaller
    val params = PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL)
    val pkgName: String = getPkgName(context, apkPath) ?: return false
    params.setAppPackageName(pkgName)
    try {
        val allowDowngrade =
                PackageInstaller.SessionParams::class.java.getMethod("setAllowDowngrade",
                    Boolean::class.javaPrimitiveType)
        allowDowngrade.isAccessible = true
        allowDowngrade.invoke(params, true)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    var os: OutputStream? = null
    var `is`: InputStream? = null
    try {
        val sessionId = packageInstaller.createSession(params)
        val session = packageInstaller.openSession(sessionId)
        os = session.openWrite(pkgName, 0, -1)
        `is` = FileInputStream(apkPath)
        val buffer = ByteArray(1024)
        var len: Int
        while (`is`.read(buffer).also { len = it } != -1) {
            os.write(buffer, 0, len)
        }
        session.fsync(os)
        os.close()
        os = null
        `is`.close()
        `is` = null
        session.commit(PendingIntent.getBroadcast(context,
            sessionId,
            Intent(Intent.ACTION_MAIN),
            0).intentSender)
    } catch (e: java.lang.Exception) {
        return false
    } finally {
        if (os != null) {
            try {
                os.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (`is` != null) {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return true
}

/**
 * 获取apk的包名
 */
fun getPkgName(context: Context, apkPath: String?): String? {
    val pm = context.packageManager
    val info = pm.getPackageArchiveInfo(apkPath!!, 0)
    return info?.packageName
}
/**
 * 获取apk的包名
 */
fun getPkgName(apkPath: String?): String? {
    val pm = CtxUtil.getCtx().packageManager
    val info = pm.getPackageArchiveInfo(apkPath!!, 0)
    return info?.packageName
}

/**
 * 判断两个版本号哪个大
 * @param versionOld 现有版本号
 * @param versionNew 服务器版本号
 * @return true 服务器版本号为最新
 */
fun checkVersion(versionOld: String?, versionNew: String?): Boolean {
    try {
        versionOld ?: return false
        versionNew ?: return false
        val split = versionOld.split(".")
        val split2 = versionNew.split(".")
        if (split.size < split2.size) return true
        else if (split.size > split2.size) return false

        for (i in split.indices) {
            if (split[i].toIntOrNull() ?: 0 < split2[i].toIntOrNull() ?: 0) return true
            else if (split[i].toIntOrNull() ?: 0 > split2[i].toIntOrNull() ?: 0) return false
        }
        return false
    } catch (e: Exception) {
        return false
    }
}

/**
 * 用于多进程中在application中判断是否是主进程
 */
fun isAppMainProcess(context: Context):Boolean {
    val pid = android.os.Process.myPid()
    val packageName = context.packageName
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    activityManager.runningAppProcesses.forEach {
        if (it.pid == pid) {
            return it.processName == packageName
        }
    }
    return false
}