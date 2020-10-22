# Dynamic-permissions
Android6.0 动态权限 库

~~~ Kotlin
//申请权限
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.apply(Manifest.permission.READ_EXTERNAL_STORAGE) { reject, forever ->
            forever.forEach {
                println("永久拒绝的权限：$it")
            }
            if (reject.isEmpty()) {
                println("ok")
            } else {
                println("no")
            }
        }

    }

}
~~~

~~~ Kotlin
//批量申请权限
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.apply(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)) { reject, forever ->
            forever.forEach {
                println("永久拒绝的权限：$it")
            }
            if (reject.isEmpty()) {
                println("ok")
            } else {
                println("no")
            }
        }

    }

}
~~~

~~~ Kotlin
//申请apk安装权限
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.applyInstallPackage { reject, _ ->
            if (reject.isEmpty()) {
                println("ok")
            } else {
                println("no")
            }
        }

    }

}
~~~

~~~ Kotlin
//打开app设置页
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.openAppSettings()

    }

}
~~~