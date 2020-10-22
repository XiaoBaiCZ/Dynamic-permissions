package cc.xiaobaicz.permissions

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 动态权限申请碎片
 */
@TargetApi(Build.VERSION_CODES.M)
class PermissionsFragment : BaseCloseFragment() {

    private val CODE_REQUEST = 0x1000

    private lateinit var mResult: Result

    private lateinit var mPermission: Array<String>

    private val mReject = HashSet<String>()
    private val mForever = HashSet<String>()
    private val mApply = HashSet<String>()

    companion object {
        fun newInstance(permission: Array<String>, result: Result): PermissionsFragment {
            val fragment = PermissionsFragment()
            fragment.mPermission = permission
            fragment.mResult = result
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = View(context)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var isSuccess = true
        for (p in mPermission) {
            //检查权限
            if (context.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                isSuccess = false
                //检查是否永久拒绝
                if (!shouldShowRequestPermissionRationale(p)) {
                    mForever.add(p)
                }
                mApply.add(p)
            }
        }
        if (isSuccess) {
            mResult(listOf(), listOf())
            close()
            return
        }
        val apply = Array(mApply.size) {
            return@Array ""
        }
        mApply.toArray(apply)
        requestPermissions(apply, CODE_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == CODE_REQUEST && grantResults.isNotEmpty()) {
            grantResults.forEachIndexed { index, result ->
                val p = permissions[index]
                if (result != PackageManager.PERMISSION_GRANTED) {
                    mReject.add(p)
                    if (shouldShowRequestPermissionRationale(p)) {
                        mForever.remove(p)
                    } else {
                        mForever.add(p)
                    }
                } else {
                    mForever.remove(p)
                }
            }
        }
        mResult(mReject.toList(), mForever.toList())
        close()
    }

}