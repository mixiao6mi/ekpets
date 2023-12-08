package com.me.base.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.me.base.R
import com.me.base.ext.hideNavigation
import com.me.base.ui.dialog.LoadingDialog
import com.me.base.ui.fragment.OnBackPressedListener

abstract class BaseActivity: AppCompatActivity(),IUi{
    private  val mLoadingDialog:LoadingDialog by lazy{
        LoadingDialog(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideNavigation()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

    }
    override fun getWindowContainer(): Any? {
        return this
    }
    override fun finishUI() {
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_in,R.anim.activity_out)
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.activity_in,R.anim.activity_out)
    }
    override fun showLoadingDialog() {
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show()
        }
    }

    override fun hideLoadingDialog() {
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideNavigation()
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount

        if (backStackEntryCount > 0) {
            val topBackStackEntry = fragmentManager.getBackStackEntryAt(backStackEntryCount - 1)
            val topFragmentTag = topBackStackEntry.name
            val currentFragment = fragmentManager.findFragmentByTag(topFragmentTag)

            if (currentFragment is OnBackPressedListener) {
                val handled = currentFragment.onBackPressed()
                if (!handled) {
                    super.onBackPressed()
                }else{
                    fragmentManager.popBackStack()
                }
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}