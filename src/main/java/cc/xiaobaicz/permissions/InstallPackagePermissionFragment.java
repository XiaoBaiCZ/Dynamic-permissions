package cc.xiaobaicz.permissions;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 安装Apk权限申请碎片
 * @author BC
 * @since 1.2.0
 */
@TargetApi(Build.VERSION_CODES.O)
public final class InstallPackagePermissionFragment extends BaseCloseFragment {

    private final static int CODE_REQUEST = 0x1000;

    private Callback mCallback;

    public static InstallPackagePermissionFragment newInstance(final Callback callback) {
        InstallPackagePermissionFragment fragment = new InstallPackagePermissionFragment();
        fragment.mCallback = callback;
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
        if (context.getPackageManager().canRequestPackageInstalls()) {
            mCallback.success();
            close();
            return;
        }
        final Uri uri = Uri.parse(String.format("package:%s", context.getPackageName()));
        final Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, uri);
        startActivityForResult(intent, CODE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                mCallback.success();
            } else {
                mCallback.failure();
                if (mCallback instanceof Callback2) {
                    ((Callback2)mCallback).failure(new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES});
                }
            }
            close();
        }
    }

}
