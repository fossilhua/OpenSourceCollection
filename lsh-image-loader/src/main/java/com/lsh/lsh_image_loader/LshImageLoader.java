package com.lsh.lsh_image_loader;

import android.content.Context;

/**
 * Created by hua on 2016/10/17.
 */

public class LshImageLoader {
    private static LshImageLoader instance = null;
    LruCache lruCache;
    IDownLoader downLoader;
    DiskCache diskCache;

    public LshImageLoader(LruCache lruCache, DiskCache diskCache, IDownLoader downLoader) {
        this.lruCache = lruCache;
        this.diskCache = diskCache;
        this.downLoader = downLoader;
    }

    public static LshImageLoader instance(Context mContext) {
        if (instance == null) {
            synchronized (LshImageLoader.class) {
                if (instance == null) {
                    instance = new Build(mContext).build();
                }
            }
        }
        return instance;
    }

    public TaskUtil load(String url) {
        Task task = new Task();
        task.setUrl(url);
        return new TaskUtil(task, lruCache, diskCache, downLoader);
    }

    private static class Build {
        private final Context context;

        public Build(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context.getApplicationContext();
        }

        LshImageLoader build() {
            LruCache lruCache = new LruCache();
            IDownLoader okDownLoader = new OKDownLoader();
            DiskCache diskCache = new DiskCache();
            return new LshImageLoader(lruCache, diskCache, okDownLoader);
        }
    }


}
