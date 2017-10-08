package com.emekalites.react.compress.image;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;

/**
 * Created by emnity on 10/8/17.
 */

public class ImageCompress {
    private static final String TAG = ImageCompress.class.getSimpleName();

    //max width and height values of the compressed image is taken as 612x816
    private int maxWidth = 612;
    private int maxHeight = 816;
    private Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private int quality = 80;
    private String destinationDirectoryPath;

    public ImageCompress(Context context) {
        destinationDirectoryPath = context.getCacheDir().getPath() + File.separator + "images";
    }

    public ImageCompress setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public ImageCompress setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public ImageCompress setCompressFormat(Bitmap.CompressFormat compressFormat) {
        this.compressFormat = compressFormat;
        return this;
    }

    public ImageCompress setQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public ImageCompress setDestinationDirectoryPath(String destinationDirectoryPath) {
        this.destinationDirectoryPath = destinationDirectoryPath;
        return this;
    }

    public File compressToFile(File imageFile, String path) throws IOException {
        return compressToFile(imageFile, System.currentTimeMillis()+".jpg", path);
    }

    private File compressToFile(File imageFile, String compressedFileName, String path) throws IOException {
        File file = new File(destinationDirectoryPath, path);
        return ImageUtil.compressImage(imageFile, maxWidth, maxHeight, compressFormat, quality,
                file.getAbsolutePath() + File.separator + compressedFileName);
    }
}
