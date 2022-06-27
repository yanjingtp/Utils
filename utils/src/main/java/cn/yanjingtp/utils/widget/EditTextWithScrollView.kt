package cn.yanjingtp.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import cn.yanjingtp.utils.R

class EditTextWithScrollView : AppCompatEditText {
    //滑动距离的最大边界
    private var mOffsetHeight = 0
    private var mHeight = 0
    private var mVert = 0

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttribute(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        initAttribute(context, attrs, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val paddingTop: Int
        val paddingBottom: Int
        val height: Int
        val mLayoutHeight: Int

        //获得内容面板
        val mLayout = layout //获得内容面板的高度
        mLayoutHeight = mLayout.height //获取上内边距
        paddingTop = totalPaddingTop //获取下内边距
        paddingBottom = totalPaddingBottom

        //获得控件的实际高度
        height = mHeight // getHeight()

        //计算滑动距离的边界
        mOffsetHeight = mLayoutHeight + paddingTop + paddingBottom - height
        setOnTouchListener() //        if(getId() == R.id.edittext2) {
        //            Log.d(TAG, "ffffaaaa onMeasure == " + mOffsetHeight);
        //        }
    }

    private fun initAttribute(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val array = context.obtainStyledAttributes(attrs,
            R.styleable.ScrollMulrowsEditText,
            defStyleAttr,
            0)
        val count = array.indexCount
        for (i in 0 until count) {
            val attr = array.getIndex(i)
            when (attr) {
                R.styleable.ScrollMulrowsEditText_sc_mul_edit_height -> mHeight =
                        array.getDimensionPixelSize(attr, 0)
            }
        }
        array.recycle()
    }

    override fun onScrollChanged(horiz: Int, vert: Int, oldHoriz: Int, oldVert: Int) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert)
        mVert = vert
        if (vert == mOffsetHeight || vert == 0) { //这里触发父布局或祖父布局的滑动事件
            parent.requestDisallowInterceptTouchEvent(false)
        }
    } //滑动到上边缘

    val isUpperEdge: Boolean
        get() = mVert == 0

    //滑动到下边缘
    val isLowerEdge: Boolean
        get() = mVert == mOffsetHeight
    private var scrollBeginY = 0f
    fun setOnTouchListener() {
        setOnTouchListener(OnTouchListener { v, event -> //canScrollVertically()方法为判断指定方向上是否可以滚动,参数为正数或负数,负数检查向上是否可以滚动,正数为检查向下是否可以滚动
            if (MotionEvent.ACTION_DOWN == event.action) {
                scrollBeginY = event.y
                v.parent.requestDisallowInterceptTouchEvent(true) //要求父类布局不在拦截触摸事件
                return@OnTouchListener false
            }
            if (canScrollVertically(1)) { //可以向下滚动
                if (isUpperEdge && event.y >= scrollBeginY) { //已经在上边缘，向下手势滑动
                    v.parent.requestDisallowInterceptTouchEvent(false) //交给父布局
                } else {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                }
            } else if (canScrollVertically(-1)) { //可以向上滚动
                if (isLowerEdge && event.y <= scrollBeginY) { //已经在下边缘，向上手势滑动
                    v.parent.requestDisallowInterceptTouchEvent(false) //交给父布局
                } else {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                }
            } else {
                v.parent.requestDisallowInterceptTouchEvent(false) //交给父布局
            } //getY  手机屏幕上边 getY  值小
            //getY  手机屏幕下边 getY  值大
            false
        })
    }
}