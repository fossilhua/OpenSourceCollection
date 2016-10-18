package com.lsh.opensourcecollection.imageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lsh.lsh_image_loader.LshImageLoader;
import com.lsh.opensourcecollection.R;

public class MainImageLoaderAct extends AppCompatActivity {
    private static final String IMAG_URL = "http://tse1.mm.bing.net/th?&id=OIP.M4e26361208c4af00b35a803ba7555cc4o0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image_loader);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        LshImageLoader.instance(this).load(IMAG_URL).into(image);
    }
}
