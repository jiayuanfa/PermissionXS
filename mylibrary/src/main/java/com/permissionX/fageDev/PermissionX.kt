package com.permissionX.fageDev

import android.app.Activity
import androidx.fragment.app.FragmentActivity

/**
 * 权限库的单例类
 */
object PermissionX {

    private const val TAG = "InvisibleFragment"

    /**
     * 提供给外界的请求权限的方法
     * @param activity FragmentActivity
     * @param permissions 权限字符串
     * @param callback 回调
     */
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {

        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }

        // 这里的*号表示把字符串数字转换为一个可变长度参数传递过去
        fragment.requestNow(callback, *permissions)
    }
}