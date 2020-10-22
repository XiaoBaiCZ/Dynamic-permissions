package cc.xiaobaicz.demo

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.demo.databinding.ActivityMainBinding
import cc.xiaobaicz.permissions.apply

class MainActivity : AppCompatActivity() {

    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        bind.btnCheck.setOnClickListener {
            apply(Manifest.permission.CAMERA) { r, f -> //r 拒绝的权限集合，f 永久拒绝的权限集合
                if (f.isNotEmpty()) {
                    //永久拒绝
                    showToast("永久拒绝")
                    return@apply
                }
                if (r.isNotEmpty()) {
                    //拒绝一次
                    showToast("拒绝一次")
                    return@apply
                }
                //成功授权
                showToast("成功授权")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}