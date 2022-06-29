package cn.yanjingtp.utils.utils

import android.util.Log

private const val TAG = "TAG"

/**
 * logE
 */
fun <T> T?.logE(){
    when (this) {
        is String? -> {
            when {
                null == this -> {
                    Log.e("${TAG}_${getLogInfo()}", "传入对象为null")
                }
                this.isEmpty() -> {
                    Log.e("${TAG}_${getLogInfo()}", "传入对象为空")
                }
                else -> {
                    Log.e("${TAG}_${getLogInfo()}", this)
                }
            }
        }
        else ->  {
            when{
                null == this -> Log.e("${TAG}_${getLogInfo()}", "传入对象为null")
                else ->Log.e("${TAG}_${getLogInfo()}", this.toString())
            }
        }
    }
}

/**
 * logD
 */
fun <T> T?.logD(){
    when (this) {
        is String? -> {
            when {
                null == this -> {
                    Log.d("${TAG}_${getLogInfo()}", "传入对象为null")
                }
                this.isEmpty() -> {
                    Log.d("${TAG}_${getLogInfo()}", "传入对象为空")
                }
                else -> {
                    Log.d("${TAG}_${getLogInfo()}", this)
                }
            }
        }
        else ->  {
            when{
                null == this -> Log.d("${TAG}_${getLogInfo()}", "传入对象为null")
                else ->Log.d("${TAG}_${getLogInfo()}", this.toString())
            }
        }
    }
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
