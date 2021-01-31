package com.kkks.project.zoom.features.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.view.Gravity;
import android.view.TextureView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class CameraStreamView extends TextureView implements CameraStreamCallback {
    private BitmapFactory.Options bitmapOption;

    public CameraStreamView(Context context) {
        super(context);
        this.bitmapOption = new BitmapFactory.Options();
    }



    @Override
    public void drawStream(byte[] buffer, Camera.Size size, boolean isFront) {
        Bitmap image = BitmapFactory.decodeByteArray(buffer, 0, buffer.length, this.bitmapOption);
        if (image == null) {
            return;
        }
        Bitmap drawableImage = image.copy(Bitmap.Config.ARGB_8888, true);

        Matrix matrix = new Matrix();
        if(isFront) {
            matrix.setScale(-1,1);
            matrix.postRotate(-270);
        } else {
            matrix.postRotate(90);
        }

        int width = (int)(size.width * 1.5);
        int height = (int)(size.height * 1.5);

        Bitmap rotatedImage = Bitmap.createBitmap(drawableImage,0,0,drawableImage.getWidth(),
                drawableImage.getHeight(), matrix, false);
        Bitmap scaledImage = Bitmap.createBitmap(rotatedImage, 0, 0, drawableImage.getWidth(), drawableImage.getHeight(), matrix, false);
        this.setLayoutParams(new LinearLayout.LayoutParams(width, height, Gravity.LEFT));

        Canvas canvas = this.lockCanvas();
        if(canvas != null){
            canvas.drawBitmap(scaledImage,0,0, null);
            this.unlockCanvasAndPost(canvas);
        }
    }
}
