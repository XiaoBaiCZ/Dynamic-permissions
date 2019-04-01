package cc.xiaobaicz.permissions;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;

/**
 * 动态权限申请类
 * @author BC
 */
public final class Permissions {

    /**
     * 申请权限
     */
    public static void checkPermissions(final Activity activity, final  String[] permissions, final  Callback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity == null) {
                throw new NullPointerException();
            }
            PermissionsFragment fragment = PermissionsFragment.newInstance(permissions, callback);
            FragmentManager fm = activity.getFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(fragment, "permissions");
            transaction.commit();
        } else {
            callback.success();
        }
    }

    /**
     * 申请权限
     */
    public static void checkPermissions(final Activity activity, final  String permissions, final  Callback callback) {
        checkPermissions(activity, new String[]{permissions}, callback);
    }

}
