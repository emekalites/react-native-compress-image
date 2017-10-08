package com.emekalites.react.compress.image;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by emnity on 10/7/17.
 */

class UriPath {
    private static final String TAG = UriPath.class.getSimpleName();
    private Context mContext;

    public UriPath(Application context) {
        mContext = context;
    }

    public String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = mContext.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
