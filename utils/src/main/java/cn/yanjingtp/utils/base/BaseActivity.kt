package cn.yanjingtp.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cn.yanjingtp.utils.utils.ActivityCollector
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<VM : BaseVM, VDB : ViewDataBinding> : AppCompatActivity() {
    abstract fun getContentViewId(): Int
    abstract fun initData()
    abstract fun initListener()
    var binding: VDB? = null
    var mViewModel: VM? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //binding
        binding = DataBindingUtil.setContentView(this, getContentViewId())
        binding!!.lifecycleOwner = this

        ActivityCollector.addActivity(this)

        //viewModel
        createViewModel()

        initData()
        initListener()
    }

    private fun createViewModel() {
        if (mViewModel == null) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                (type as ParameterizedType).actualTypeArguments[0] as Class<*>
            } else { //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseVM::class.java
            }
            mViewModel =
                    ViewModelProvider(this)[modelClass.asSubclass(ViewModel::class.java)] as VM
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}