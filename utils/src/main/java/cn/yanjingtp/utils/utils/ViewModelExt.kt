package cn.yanjingtp.utils.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 处理retrofit等的协程异常捕获
 */
fun ViewModel.launch(block: suspend CoroutineScope.() -> Unit,
        onError: (e: Throwable) -> Unit,
        onStart: () -> Unit,
        onComplete: () -> Unit = {}) {
    viewModelScope.launch {
        try {
            onStart()
            block.invoke(this)
        } catch (e: Exception) {
            onError(e)
            e.message.logE()
        } finally {
            onComplete()
        }
    }
}