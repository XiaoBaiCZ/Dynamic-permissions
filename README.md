# Dynamic-permissions
Android6.0 动态权限 库

使用方式
~~~java
String[] permissions = new String[]{申请的权限};
Permissions.checkPermissions(activity, permissions, new Callback {
            @Override
            public void success() {
                Log.e(TAG, "success");
            }

            @Override
            public void failure() {
                Log.e(TAG, "failure");
            }
});
~~~
