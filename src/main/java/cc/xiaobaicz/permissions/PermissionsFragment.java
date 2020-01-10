package cc.xiaobaicz.permissions;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 动态权限申请碎片
 * @author BC
 */
@TargetApi(Build.VERSION_CODES.M)
public final class PermissionsFragment extends BaseCloseFragment {

    private final int CODE_REQUEST = 0x1000;

    private String[] mPermissions;
    private List<String> mRequestPermissions = new ArrayList<>();
    private List<String> mNeverPromptPermissions = new ArrayList<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return new View(getContext());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getContext();
        boolean isSuccess = true;
        for (String p : mPermissions) {
            if (context.checkSelfPermission(p) != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(p)) {
                    mNeverPromptPermissions.add(p);
                } else {
                    mRequestPermissions.add(p);
                }
                isSuccess = false;
            }
        }
        if (mRequestPermissions.size() == 0) {
            onFailure(getRequestPermissions());
            close();
            return;
        }
        if (isSuccess) {
            mCallback.success();
            close();
            return;
        }
        requestPermissions(getRequestPermissions(), CODE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_REQUEST && grantResults.length != 0) {
            boolean success = true;
            HashSet<String> failureSet = new HashSet<>();
            int index = 0;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    success = false;
                    failureSet.add(permissions[index]);
                }
                index++;
            }
            String[] failures = new String[failureSet.size()];
            failureSet.toArray(failures);
            onFailure(failures);
            if (success) {
                mCallback.success();
            }
            close();
        }
    }

    /**
     * @return 永不提示权限
     * @since 1.4.0
     */
    private String[] getNeverPromptPermissions() {
        final String[] ps = new String[mNeverPromptPermissions.size()];
        return mNeverPromptPermissions.toArray(ps);
    }

    /**
     * @return 需请求权限
     * @since 1.4.0
     */
    private String[] getRequestPermissions() {
        final String[] ps = new String[mRequestPermissions.size()];
        return mRequestPermissions.toArray(ps);
    }

    /**
     * @param failures 授权失败权限
     * @since 1.4.0
     */
    private void onFailure(String[] failures) {
        if (failures == null || failures.length > 0) {
            mCallback.failure();
            mCallback.failure(failures);
        }
        String[] neverPromptPermissions = getNeverPromptPermissions();
        if (neverPromptPermissions.length > 0) {
            mCallback.neverPrompt(getNeverPromptPermissions());
        }
    }

}
