package com.lsh.opensourcecollection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lsh.lshrecyclerviewadapter.BaseAdapter;
import com.lsh.opensourcecollection.imageloader.MainImageLoaderAct;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mDataList = new ArrayList<>();
    private RecyclerView mRvMainList;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        initView();
        setListener();
    }

    private void findView() {
        mRvMainList = (RecyclerView) findViewById(R.id.rv_main_list);
    }

    private void initData() {
        mDataList.add("Lsh-Image-Loader");
        mDataList.add("come soon");
    }

    private void initView() {
        mRvMainList.setLayoutManager(new LinearLayoutManager(this));
        mRvMainList.addItemDecoration(new MainItemDecoration(this));
        mRvMainList.setAdapter(mAdapter = new MainAdapter(this, mDataList));
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    private BaseAdapter.OnItemClickListener onItemClickListener = new BaseAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int pos) {
            switch (pos) {
                case 0://
                    startAct(MainImageLoaderAct.class);
                    break;
                default:
                    t("come soon");
                    break;

            }
        }
    };

    /********************************************************/
    private void startAct(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
    private void t(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
