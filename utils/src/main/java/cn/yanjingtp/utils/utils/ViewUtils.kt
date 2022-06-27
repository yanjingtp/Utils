package cn.yanjingtp.utils.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

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