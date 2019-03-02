package com.sample.camera1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SampleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = SampleSurfaceView.class.getSimpleName();

    private SurfaceHolder mSurfaceHolder;

    public SampleSurfaceView(Context context) {
        super(context);
        init();
    }

    public SampleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SampleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // CameraUtils.openFrontCamera(CameraUtils.DESIRED_PREVIEW_FPS);
        CameraUtils.openCamera(0, CameraUtils.DESIRED_PREVIEW_FPS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        CameraUtils.startPreviewDisplay(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        CameraUtils.releaseCamera();
    }
}