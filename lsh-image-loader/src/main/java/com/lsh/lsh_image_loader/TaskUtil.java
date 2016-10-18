package com.lsh.lsh_image_loader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by hua on 2016/10/17.
 */

public class TaskUtil {
    private static final String TAG = TaskUtil.class.getSimpleName();
    Task task;
    ImageView imageView;
    IDownLoader downLoader;
    LruCache lruCache;
    DiskCache diskCache;

    public TaskUtil(Task task, LruCache lruCache, DiskCache diskCache, IDownLoader downLoader) {
        this.task = task;
        this.lruCache = lruCache;
        this.diskCache = diskCache;
        this.downLoader = downLoader;
    }

    public void into(ImageView imageView) {
        this.imageView = imageView;
        Bitmap bitmap = null;
        //内存
        bitmap = lruCache.get(task.getUrl());
        if (bitmap != null) {
            Log.d(TAG,"LruCache");
            imageView.setImageBitmap(bitmap);
            return;
        }
        //硬盘
        bitmap = diskCache.getBit(task.getUrl());
        if (bitmap != null) {
            Log.d(TAG,"DiskCache");
            imageView.setImageBitmap(bitmap);
            lruCache.put(task.getUrl(), bitmap);
            return;
        }
        //网络
        thread();
    }

    private void thread() {
        Log.d(TAG,"thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downLoader.down(task);

                if (bitmap != null) {
                    Log.d(TAG, bitmap.getHeight() + "");
                    Message msg = handler.obtainMessage();
                    msg.what = MESSAGE_WHAT_SUCCESS;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bit", bitmap);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = MESSAGE_WHAT_FAIL;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private static final int MESSAGE_WHAT_SUCCESS = 100;
    private static final int MESSAGE_WHAT_FAIL = -1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_WHAT_SUCCESS:
                    Bitmap bitmap = msg.getData().getParcelable("bit");
                    imageView.setImageBitmap(bitmap);
                    lruCache.put(task.getUrl(), bitmap);
                    diskCache.saveBit(bitmap, task.getUrl());
                    break;
                case MESSAGE_WHAT_FAIL:
                    Log.d(TAG,"load img error");
                    break;
            }
        }
    };
}
