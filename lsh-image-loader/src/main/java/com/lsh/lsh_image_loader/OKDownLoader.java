package com.lsh.lsh_image_loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hua on 2016/10/17.
 */

public class OKDownLoader implements IDownLoader {
    private static final String TAG = OKDownLoader.class.getSimpleName();
    @Override
    public Bitmap down(Task task) {
        String url = task.getUrl();
        Log.d(TAG,url);
        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return BitmapFactory.decodeStream(response.body().byteStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
