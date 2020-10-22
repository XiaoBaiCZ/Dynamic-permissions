package cc.xiaobaicz.permissions

import android.app.Fragment
import java.io.Closeable

/**
 * 可关闭Fragment
 */
abstract class BaseCloseFragment : Fragment(), Closeable {

    override fun close() {
        fragmentManager.beginTransaction().apply {
            remove(this@BaseCloseFragment)
            commitAllowingStateLoss()
        }
    }

}