package cc.xiaobaicz.permissions;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 动态权限申请类
 * @author BC
 */
public final class Permissions {

    /**
     * 申请权限
     */
    public static void checkPermissions(@NonNull final FragmentActivity activity, @NonNull final  String[] permissions, @NonNull final  Callback callback) {
        PermissionsFragment fragment = PermissionsFragment.newInstance(permissions, callback);
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(fragment, "permissions");
        transaction.commit();
    }

    /**
     * 申请权限
     */
    public static void checkPermissions(@NonNull final FragmentActivity activity, @NonNull final  String permissions, @NonNull final  Callback callback) {
        checkPermissions(activity, new String[]{permissions}, callback);
    }

}
