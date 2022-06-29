package cn.yanjingtp.utils.utils

/**
 * 如果为null,返回block内容
 */
inline fun <T:Any> T?.ifNull(block: () -> T): T = this ?: block()


/**
 * String如果为empty,返回block内容
 */
inline fun String.ifEmpty(block: () -> String): String =
        if (this.isEmpty()) block() else this