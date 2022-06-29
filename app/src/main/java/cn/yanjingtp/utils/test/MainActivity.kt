package cn.yanjingtp.utils.test

import cn.yanjingtp.utils.base.BaseActivity
import cn.yanjingtp.utils.test.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainVM,ActivityMainBinding>() {
    override fun getContentViewId() = R.layout.activity_main

    override fun initData() {
        binding?.vm = mViewModel

    }

    override fun initListener() {

    }


}