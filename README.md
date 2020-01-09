# Dynamic-permissions
Android6.0 动态权限 库

### 使用方式
~~~java
String[] permissions = new String[]{申请的权限};
Permissions.checkPermissions(activity, permissions, new Callback2 {
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

# Apache License
~~~
Copyright (c) 2019, xiaobai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~
