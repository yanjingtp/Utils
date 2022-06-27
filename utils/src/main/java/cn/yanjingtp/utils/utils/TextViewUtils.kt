package cn.yanjingtp.utils.utils

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * 动态设置textview的drawableLeft
 */
fun TextView.drawableLeft(@DrawableRes resId: Int,left:Int = 0) {
    val drawable = resources.getDrawable(resId)
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)

}

/**
 * 动态设置textview的drawableRight
 */
fun TextView.drawableRight(@DrawableRes resId: Int) {
    val drawable = resources.getDrawable(resId)
    this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
}

/**
 * 设置textview字体颜色
 */
fun TextView.textColor(@ColorRes colorId: Int) {
    setTextColor(resources.getColor(colorId))
}

/**
 * 清除textView的drawable
 */
fun TextView.clearDrawable(){
    setCompoundDrawables(null,null,null,null);
}
