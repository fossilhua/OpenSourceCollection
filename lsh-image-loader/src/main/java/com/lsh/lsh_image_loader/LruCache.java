package com.lsh.lsh_image_loader;

import android.graphics.Bitmap;

import java.util.LinkedHashMap;

/**
 * Created by hua on 2016/10/17.
 */

public class LruCache {
   private LinkedHashMap<String, Bitmap> cacheMap = new LinkedHashMap<>();

    public Bitmap get(String key) {
        return cacheMap.get(key);
    }

    public void put(String key, Bitmap bit) {
        cacheMap.put(key, bit);
    }
}
