### 1.4.1
修改回调逻辑顺序

调整：回调优先级 failure() -> failure(...) -> neverPrompt(...) -> success()

修复：部分权限永不提示 & 部分权限拒绝 之后，再次申请剩余权限全部通过时，永不提示回调不调用

### 1.4.0
新增永不提示回调
~~~java
Permissions.checkInstallPackagePermission(activity, new Callback2 {
      @Override
      public void neverPrompt(String[] s) {
          Log.e(TAG, "failure");
      }
});
~~~
新增打开App设置函数
~~~java
Permissions.openAppSettings(activity);
~~~

### 1.2.0
新增8.0安装Apk权限
~~~java
Permissions.checkInstallPackagePermission(activity, new Callback2 {
      @Override
      public void success() {
          Log.e(TAG, "success");
      }

      @Override
      public void failure(String[] s) {
          Log.e(TAG, "failure");
      }
});
~~~
