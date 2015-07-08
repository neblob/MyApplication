package com.example.toshiba.myapplication;

import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView
                            implements SurfaceHolder.Callback   {
    private static Context mContext;
    private static Camera mCamera;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    @SuppressWarnings("deprecation")
        public MySurfaceView(Context context, AttributeSet attrs,
                             int defaultsyStyle) {
        super(context, attrs, defaultsyStyle);
        mContext = context;

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            mCamera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        Camera.Parameters params = mCamera.getParameters();
        List<Camera.Size> sizes = params.getSupportedPreviewSizes();
        Camera.Size selected = sizes.get(0);
        params.setPreviewSize(selected.width, selected.height);
        mCamera.setParameters(params);
        mCamera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
    }


}