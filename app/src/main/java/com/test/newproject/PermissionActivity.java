package com.test.newproject;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.test.newproject.base.BaseActivity;
import com.test.newproject.utils.PermissionUtils;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/7/20
 *     desc   :
 *     modify :
 * </pre>
 */

public class PermissionActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_permission;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, PermissionActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        if(!PermissionUtils.hasPermission(mContext, 100, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA)) {
            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, List<String> grantPermissions) {
                if(requestCode == 100) {
                    Toast.makeText(PermissionActivity.this, "权限申请成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int requestCode, List<String> deniedPermissions) {
                if(requestCode == 100) {
                    for (String permission:permissions){
                        if(!PermissionUtils.hasPermission(mContext, permission)) {
                            if(TextUtils.equals(permission, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                PermissionUtils.FailedForPermissions(mContext
                                        , requestCode
                                        , deniedPermissions
                                        , "本应用需要存储权限，是否现在就去设置？"
                                        , "退出程序"
                                        , new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(PermissionActivity.this, "退出程序", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            if(TextUtils.equals(permission, Manifest.permission.READ_PHONE_STATE)) {
                                PermissionUtils.FailedForPermissions(mContext
                                        , requestCode, deniedPermissions, "本应用需要手机状态权限，是否现在就去设置？");
                            }
                            if(TextUtils.equals(permission, Manifest.permission.CAMERA)) {
                                PermissionUtils.FailedForPermissions(mContext
                                        , requestCode, deniedPermissions, "本应用需要相机权限，是否现在就去设置？");
                            }
                        }

                    }
                }
            }
        });
    }
}
