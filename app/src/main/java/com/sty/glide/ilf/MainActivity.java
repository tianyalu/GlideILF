package com.sty.glide.ilf;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivNetLocalPic;
    private RecyclerView rcvNetLocalPic;
    private Button btnShowNetPic;
    private Button btnShowLocalPic;
    private Button btnShowNetRcv;
    private Button btnShowLocalRcv;

    private MyRcvAdapter adapter;

    private static String NET_URL = "http://img3.xiazaizhijia.com/walls/20160711/1440x900_f2c34c027c1c0b9.jpg";
    private static String LOCAL_URL = Environment.getExternalStorageDirectory().getPath()
            + File.separator + "sty" + File.separator + "cool_dog.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews(){
        ivNetLocalPic = findViewById(R.id.iv_net_local_pic);
        rcvNetLocalPic = findViewById(R.id.rcv_net_local_pic);
        btnShowNetPic = findViewById(R.id.btn_show_net_pic);
        btnShowLocalPic = findViewById(R.id.btn_show_local_pic);
        btnShowNetRcv = findViewById(R.id.btn_show_net_rcv);
        btnShowLocalRcv = findViewById(R.id.btn_show_local_rcv);
    }

    private void setListeners(){
        btnShowNetPic.setOnClickListener(this);
        btnShowLocalPic.setOnClickListener(this);
        btnShowNetRcv.setOnClickListener(this);
        btnShowLocalRcv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_show_net_pic:
                rcvNetLocalPic.setVisibility(View.GONE);
                Glide.with(this).load(NET_URL).into(ivNetLocalPic);
                ivNetLocalPic.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_show_local_pic:
                rcvNetLocalPic.setVisibility(View.GONE);
                Glide.with(this).load(LOCAL_URL).into(ivNetLocalPic);
                ivNetLocalPic.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_show_net_rcv:
                ivNetLocalPic.setVisibility(View.GONE);
                showNetImageList();
                rcvNetLocalPic.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_show_local_rcv:
                ivNetLocalPic.setVisibility(View.GONE);
                showLocalImageList();
                rcvNetLocalPic.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void showNetImageList() {
        adapter = new MyRcvAdapter(this, getNetListDatas());
        rcvNetLocalPic.setLayoutManager(new LinearLayoutManager(this));
        rcvNetLocalPic.setAdapter(adapter);
        //rcvNetLocalPic.scrollToPosition(adapter.getItemCount()-1);
    }

    private void showLocalImageList() {
        adapter = new MyRcvAdapter(this, getLocalListDatas());
        rcvNetLocalPic.setLayoutManager(new LinearLayoutManager(this));
        rcvNetLocalPic.setAdapter(adapter);
        //rcvNetLocalPic.scrollToPosition(adapter.getItemCount()-1);
    }

    private List<String> getNetListDatas(){
        List<String> netListDatas = new ArrayList<>();
        for(int i = 0; i < Constants.url_datas.length; i++){
            netListDatas.add(Constants.url_datas[i]);
        }
        return netListDatas;
    }

    private List<String> getLocalListDatas(){
        List<String> localListDatas = new ArrayList<>();
        String baseLocalUrl = Environment.getExternalStorageDirectory().getPath() + "/sty/100000/";
        for(int i = 100000; i < 100100; i++){
            String realUrl = baseLocalUrl + i + ".jpeg";
            localListDatas.add(realUrl);
        }
        return localListDatas;
    }
}
