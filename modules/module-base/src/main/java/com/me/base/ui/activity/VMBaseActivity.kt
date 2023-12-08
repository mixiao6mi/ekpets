package com.me.base.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.me.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType

/**
 * 绑定了ViewModel的Activity基类
 */
abstract class VMBaseActivity<B : ViewDataBinding, M : BaseViewModel> : BaseActivity() {
    protected lateinit var binding: B
    protected lateinit var viewModel: M
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        val genType = this.javaClass.genericSuperclass
        val params = (genType as? ParameterizedType)?.actualTypeArguments
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[params?.get(1) as Class<M>]
        viewModel.attachUi(this)
        binding.setVariable(getViewModelId(), viewModel)
        init()

    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModelId(): Int

    open fun init() {
        bindLifecycle(viewModel)
    }

    open fun bindLifecycle(viewModel: ViewModel?) {
        viewModel?.let {
            if (it is LifecycleObserver) {
                lifecycle.addObserver(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.detachUi()
        binding.unbind()
    }
}