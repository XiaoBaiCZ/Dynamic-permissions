package cc.xiaobaicz.permissions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

/**
 * 申请动态权限
 */
fun Activity.apply(permission: Array<String>, result: Result) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val fragment = PermissionsFragment.newInstance(permission, result)
        val fm = fragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(fragment, "permissions")
        transaction.commitAllowingStateLoss()
    } else {
        result(listOf(), listOf())
    }
}

/**
 * 申请动态权限
 */
fun Activity.apply(permission: String, result: Result) {
    apply(arrayOf(permission), result)
}

/**
 * 申请安装权限
 */
fun Activity.applyInstallPackage(result: Result) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val fragment = InstallPackagePermissionFragment.newInstance(result)
        val fm = fragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(fragment, "permissions-installpackage")
        transaction.commitAllowingStateLoss()
    } else {
        result(listOf(), listOf())
    }
}

/**
 * 打开应用设置页
 */
fun Activity.openAppSettings() {
    Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.parse(java.lang.String.format("package:%s", packageName))
        startActivity(this)
    }
}
