package com.kkks.project.zoom.features.camera;

import android.hardware.Camera;

public interface CameraStreamCallback {
    void drawStream(byte[] buffer, Camera.Size size, boolean isFront);
}
