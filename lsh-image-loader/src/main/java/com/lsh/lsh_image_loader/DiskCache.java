package com.lsh.lsh_image_loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by hua on 2016/10/17.
 */

public class DiskCache {
    private static final String BASE_CACHE = "lsh-loader-cache";
    private static final String BASE_FOLDER = Environment
            .getExternalStorageDirectory()
            + File.separator
            + BASE_CACHE
            + File.separator;
    private static final String FILE_CACHE = BASE_FOLDER + "cache"
            + File.separator;


    public Bitmap getBit(String url) {
        File fileDir = new File(FILE_CACHE);
        if (!fileDir.exists()) {
            if (!fileDir.mkdir()) {
                return null;
            }
        }
        File[] files = fileDir.listFiles();
        if (files == null) {
            return null;
        }
        for (File file : files) {
            String key = ImageLoaderUtil.getKey(url);
            if (file.exists() && TextUtils.equals(file.getName(), key)) {
                return BitmapFactory.decodeFile(key);
            }
        }
        return null;
    }

    public void saveBit(Bitmap bitmap, String url) {
        String key = ImageLoaderUtil.getKey(url);
        File file = new File(FILE_CACHE + key);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
