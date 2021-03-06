package com.sample.camera1;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends Activity {

    private static final int REQUEST_CAMERA = 154;
    private static final String[] ALL_PERMISSIONS = new String[] {Manifest.permission.CAMERA};

    private SampleSurfaceView mSampleSurfaceView;

    private boolean mCameraRequested = false;

    private int mOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasNecessaryPermission()) {
            initView();
        } else {
            requestPermissions(ALL_PERMISSIONS, REQUEST_CAMERA);
        }
    }

    private boolean hasNecessaryPermission() {
        List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : ALL_PERMISSIONS) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsList.add(permission);
                }
            }
        }
        return permissionsList.size() == 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // 相机权限
            case REQUEST_CAMERA:
                if (grantResults != null && grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mCameraRequested = true;
                    initView();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        mSampleSurfaceView = findViewById(R.id.camera_view);
        mOrientation = CameraUtils.calculateCameraPreviewOrientation(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraRequested) {
            CameraUtils.startPreview();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CameraUtils.stopPreview();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
