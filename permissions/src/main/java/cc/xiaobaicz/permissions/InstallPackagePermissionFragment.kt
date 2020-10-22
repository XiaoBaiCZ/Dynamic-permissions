package cc.xiaobaicz.permissions

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.String


/**
 * 安装Apk权限申请碎片
 */
@TargetApi(Build.VERSION_CODES.O)
class InstallPackagePermissionFragment : BaseCloseFragment() {

    private val CODE_REQUEST = 0x1000

    private lateinit var mResult: Result

    companion object {
        fun newInstance(result: Result): InstallPackagePermissionFragment {
            val fragment = InstallPackagePermissionFragment()
            fragment.mResult = result
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = View(context)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        if (context.packageManager.canRequestPackageInstalls()) {
            mResult(listOf(), listOf())
            close()
            return
        }
        val uri = Uri.parse(String.format("package:%s", context.packageName))
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, uri)
        startActivityForResult(intent, CODE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CODE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                mResult(listOf(), listOf())
            } else {
                mResult(listOf(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES), listOf())
            }
            close()
        }
    }

}