package com.emekalites.react.compress.image;

import android.app.Application;
import android.net.Uri;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.io.File;
import java.io.IOException;

public class ImageCompressModule extends ReactContextBaseJavaModule {
    private final static String TAG = ImageCompressModule.class.getCanonicalName();
    private CompressImage compressImage;

    public ImageCompressModule(ReactApplicationContext reactContext) {
        super(reactContext);
        compressImage = new CompressImage((Application) reactContext.getApplicationContext());
    }

    @Override
    public String getName() {
        return "ImageCompressAndroid";
    }

    @ReactMethod
    public void createCompressedImage(String imagePath, int newWidth, int newHeight, String directoryPath, final Callback successCb, final Callback failureCb) {
        try {
            createCompressedImageWithExceptions(imagePath, newWidth, newHeight, directoryPath, successCb, failureCb);
        } catch (IOException e) {
            failureCb.invoke(e.getMessage());
        }
    }

    private void createCompressedImageWithExceptions(String imageString, int newWidth, int newHeight, String directoryPath, final Callback successCb, final Callback failureCb) throws IOException {
        String compressedImage = compressImage.doCompressImage(imageString, directoryPath, newWidth, newHeight);

        if (compressedImage != "") {
            File imageFile = new File(compressImage.getRealPathFromURI(Uri.parse(compressedImage)));

            WritableMap response = Arguments.createMap();
            response.putString("path", imageFile.getAbsolutePath());
            response.putString("uri", Uri.fromFile(imageFile).toString());
            response.putString("name", imageFile.getName());
            response.putDouble("size", imageFile.length());

            // Invoke success
            successCb.invoke(response);
        } else {
            failureCb.invoke("Error getting compressed image path");
        }
    }
}