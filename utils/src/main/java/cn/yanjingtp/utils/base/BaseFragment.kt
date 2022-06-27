package cn.yanjingtp.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VM : BaseVM, VDB : ViewDataBinding> : Fragment() {
    var binding: VDB? = null
    var viewModel: VM? = null
    abstract fun initData()
    abstract fun initListener()
    abstract val contentViewId: Int

    override fun onCreateView(inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, contentViewId, container, false)
        binding!!.lifecycleOwner = this


        //viewModel
        createVM()
        requireActivity().window.setDimAmount(0f) //解决fragment中dialog闪屏问题
        initData()
        initListener()
        return binding!!.root
    }


    private fun createVM() {
        if (null == viewModel) {
            val modelClass: Class<*>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[0] as Class<*>
            } else {
                BaseVM::class.java
            }
            viewModel =
                    ViewModelProvider(requireActivity())[modelClass.asSubclass(ViewModel::class.java)] as VM
        }

    }
}
