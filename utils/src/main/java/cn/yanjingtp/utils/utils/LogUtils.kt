package cn.yanjingtp.utils.utils

import android.util.Log

private const val TAG = "TAG"

fun String?.logE() {
    when {
        null == this -> {
            Log.e("${TAG}_${getLogInfo()}", "字符串为null")
        }
        this.isEmpty() -> {
            Log.e("${TAG}_${getLogInfo()}", "字符串为空")
        }
        else -> {
            Log.e("${TAG}_${getLogInfo()}", this)
        }
    }

}

fun String?.logD() {
    when {
        null == this -> {
            Log.d("${TAG}_${getLogInfo()}", "字符串为null")
        }
        this.isEmpty() -> {
            Log.d("${TAG}_${getLogInfo()}", "字符串为空")
        }
        else -> {
            Log.d("${TAG}_${getLogInfo()}", this)
        }
    }
}

fun Int.logE() {
    Log.e("${TAG}_${getLogInfo()}", this.toString())
}

fun Int.logD() {
    Log.d("${TAG}_${getLogInfo()}", this.toString())
}

fun Double.logE() {
    Log.e("${TAG}_${getLogInfo()}", this.toString())
}

fun Double.logD() {
    Log.d("${TAG}_${getLogInfo()}", this.toString())
}

fun Float.logE() {
    Log.e("${TAG}_${getLogInfo()}", this.toString())
}

fun Float.logD() {
    Log.d("${TAG}_${getLogInfo()}", this.toString())
}

fun Long.logE() {
    Log.e("${TAG}_${getLogInfo()}", this.toString())
}

fun Long.logD() {
    Log.d("${TAG}_${getLogInfo()}", this.toString())
}


/**
 * 输出日志所包含的信息
 */
private fun getLogInfo(): String {
    val traceElement = Exception().stackTrace[2]
    val toStringBuffer =
            StringBuffer().append("[").append(traceElement.fileName).append("-> Method:")
                    .append(traceElement.methodName).append(" -> Line:")
                    .append(traceElement.lineNumber).append("]")
    return toStringBuffer.toString()
}
