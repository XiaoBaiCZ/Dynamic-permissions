package cc.xiaobaicz.permissions;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

/**
 * 权限申请碎片
 * @author BC
 */
@TargetApi(Build.VERSION_CODES.M)
public final class PermissionsFragment extends Fragment {

    private final int CODE_REQUEST = 0x1000;

    private String[] mPermissions;

    private Callback mCallback;

    static PermissionsFragment newInstance(final String[] permissions, final Callback callback) {
        if (permissions == null || permissions.length == 0) {
            throw new NullPointerException();
        }
        PermissionsFragment fragment = new PermissionsFragment();
        fragment.mPermissions = new String[permissions.length];
        fragment.mCallback = callback;
        System.arraycopy(permissions, 0, fragment.mPermissions, 0, permissions.length);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(mPermissions, CODE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_REQUEST && grantResults.length != 0) {
            boolean success = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    success = false;
                }
            }
            if (success) {
                mCallback.success();
            } else {
                mCallback.failure();
            }
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.remove(this);
            transaction.commit();
        }
    }

}
