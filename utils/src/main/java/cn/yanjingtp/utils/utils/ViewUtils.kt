package cn.yanjingtp.utils.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.appcompat.widget.Toolbar

/**
 * 从view中获取bitmap
 */
fun getBitmapByView(v: View): Bitmap? {
    val bitmap = Bitmap.createBitmap(v.width, v.height,
        Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    v.draw(canvas)
    return bitmap
}

/**
 * OnClickListener再次封装
 * 防止用户快速点击
 */
var lastClickTime = 0L
fun <T : View> T.click(defaultTime: Int = 500, click: (T) -> Unit): YClickListener {
    return object : YClickListener(defaultTime) {
        override fun doClick(view: View) {
            click(this@click)
        }
    }.apply {
        setOnClickListener(this)
    }
}

abstract class YClickListener(private val defaultTime: Int) : View.OnClickListener {
    override fun onClick(view: View) {
        if (System.nanoTime() - lastClickTime > defaultTime * 1000000) {
            lastClickTime = System.nanoTime()
            doClick(view)
        }
    }

    abstract fun doClick(view: View)
}
