package com.kkks.project.zoom.features.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

import java.awt.font.NumericShaper;

public class CameraManager {
    private static CameraManager cameraManager;

    private CameraManager(){}

    public static CameraManager getCameraManager(){
        if (cameraManager == null){
            cameraManager = new CameraManager();
        }
        return cameraManager;
    }
    public boolean checkCameraUsable(Context context){
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            return true;
        }
        else{
            return false;
        }
    }
   public Camera getCamera(){
        Camera camera = null;

        try{
            camera = Camera.open();
            Camera.Parameters cameraParameters = camera.getParameters();
            if(cameraParamerers.gerSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){
                cameraParameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(cameraParameters);

            }
            catch (Exception  ex){
                Log.e( tag: "CameraManager",ex.toString());
                System.exit(status:1);
                
            }
            return camera;
        }
   }
}