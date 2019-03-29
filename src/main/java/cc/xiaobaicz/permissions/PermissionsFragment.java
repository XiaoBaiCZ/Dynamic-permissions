package cc.xiaobaicz.permissions;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * 权限申请碎片
 * @author BC
 */
public final class PermissionsFragment extends Fragment {

    private final int CODE_REQUEST = 0x1000;

    private String[] mPermissions;

    private Callback mCallback;

    static PermissionsFragment newInstance(@NonNull final String[] permissions, @NonNull final Callback callback) {
        if (permissions.length == 0) {
            throw new NullPointerException();
        }
        PermissionsFragment fragment = new PermissionsFragment();
        fragment.mPermissions = new String[permissions.length];
        fragment.mCallback = callback;
        System.arraycopy(permissions, 0, fragment.mPermissions, 0, permissions.length);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(mPermissions, CODE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
