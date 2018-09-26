package com.test.newproject.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import com.test.newproject.R;
import com.test.newproject.base.BaseActivity;
import com.test.newproject.utils.PermissionUtils;

import butterknife.InjectView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;


/**
 * <pre>
 *     author : created by ljn
 *     e-mail : liujinan@edreamtree.com
 *     time   : 2018/9/18
 *     desc   :
 *     modify :
 * </pre>
 */

public class ZXingActivity extends BaseActivity implements QRCodeView.Delegate {
    @InjectView(R.id.zxingview)
    ZXingView mZXingView;

    private static final String TAG = ZXingActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zxing;
    }

    public static void actionStart(Context context) {
        Intent starter = new Intent(context, ZXingActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        super.initView();
        PermissionUtils.hasPermission(mContext, 100, Manifest.permission.CAMERA);
        mZXingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.e("TAG", "result:" + result);
        setTitle("扫描结果为：" + result);
        vibrate();

        mZXingView.startSpot(); // 延迟0.5秒后开始识别
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

}
