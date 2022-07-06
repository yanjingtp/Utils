package cn.yanjingtp.utils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.annotation.RequiresApi
import cn.yanjingtp.utils.base.CtxUtil

import java.io.File

/**
 * 判断设备是否 root
 */
fun isDeviceRooted(): Boolean {
    val su = "su"
    val locations = arrayOf("/system/bin/",
        "/system/xbin/",
        "/sbin/",
        "/system/sd/xbin/",
        "/system/bin/failsafe/",
        "/data/local/xbin/",
        "/data/local/bin/",
        "/data/local/",
        "/system/sbin/",
        "/usr/bin/",
        "/vendor/bin/")
    for (location in locations) {
        if (File(location + su).exists()) {
            return true
        }
    }
    return false
}

/**
 * 判断设备 ADB 是否可用
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun isAdbEnabled(context: Context = CtxUtil.getCtx): Boolean {
    return Settings.Secure.getInt(context.contentResolver,
        Settings.Global.ADB_ENABLED,
        0) > 0
}

/**
 * 获取设备系统版本号
 */
fun getSDKVersionName(): String? {
    return Build.VERSION.RELEASE
}

/**
 * 获取设备系统版本码
 */
fun getSDKVersionCode(): Int {
    return Build.VERSION.SDK_INT
}

/**
 * 获取设备 AndroidID
 */
@SuppressLint("HardwareIds")
fun getAndroidID(context: Context = CtxUtil.getCtx): String {
    val id = Settings.Secure.getString(context.contentResolver,
        Settings.Secure.ANDROID_ID)
    return if ("9774d56d682e549c" == id) "" else id ?: ""
}

/**
 * 获取设备厂商
 */
fun getManufacturer(): String? {
    return Build.MANUFACTURER
}

/**
 * 获取设备型号
 */
fun getModel(): String {
    var model = Build.MODEL
    model = model?.trim { it <= ' ' }?.replace("\\s*".toRegex(), "") ?: ""
    return model
}

/**
 * 获取设备 ABIs(cpu类型)
 */
fun getABIs(): Array<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Build.SUPPORTED_ABIS
    } else {
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            arrayOf<String>(Build.CPU_ABI, Build.CPU_ABI2)
        } else arrayOf<String>(Build.CPU_ABI)
    }
}

/**
 * 判断是否是平板
 */
fun isTablet(): Boolean {
    return ((Resources.getSystem().configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}

/**
 * 判断是否是模拟器
 */
fun isEmulator(context: Context = CtxUtil.getCtx): Boolean {
    val checkProperty = (Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase()
            .contains("vbox") || Build.FINGERPRINT.toLowerCase()
            .contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains(
        "Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith(
        "generic") && Build.DEVICE.startsWith("generic") || "google_sdk" == Build.PRODUCT)
    if (checkProperty) return true
    var operatorName = ""
    val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
    if (tm != null) {
        val name = tm.networkOperatorName
        if (name != null) {
            operatorName = name
        }
    }
    val checkOperatorName = operatorName.toLowerCase() == "android"
    if (checkOperatorName) return true
    val url = "tel:" + "123456"
    val intent = Intent()
    intent.data = Uri.parse(url)
    intent.action = Intent.ACTION_DIAL
    return intent.resolveActivity(context.packageManager) == null

}

/**
 * 开发者选项是否打开
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun isDevelopmentSettingsEnabled(context: Context = CtxUtil.getCtx): Boolean {
    return Settings.Global.getInt(context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0) > 0
}