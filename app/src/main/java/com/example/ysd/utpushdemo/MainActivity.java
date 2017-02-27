package com.example.ysd.utpushdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.pushlibrary.UTPushUtil;
import com.example.pushlibrary.service.BeepManager;
import com.example.pushlibrary.service.ICallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_add_alias).setOnClickListener(this);
        findViewById(R.id.btn_bind_alias).setOnClickListener(this);
        findViewById(R.id.btn_tip_type).setOnClickListener(this);
        findViewById(R.id.btn_msg_vibrate).setOnClickListener(this);
        findViewById(R.id.btn_msg_vibrate_sound).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_alias:
                addAlias();
                break;
            case R.id.btn_bind_alias:
                bindAlias();
                break;
            case R.id.btn_tip_type:
                setType(2);
                i++;
            case R.id.btn_msg_vibrate:
                BeepManager.getInstance(this).setVibrate(this,false);
                BeepManager.getInstance(this).setMsgSound(this,false);
                break;
            case R.id.btn_msg_vibrate_sound:
                BeepManager.getInstance(this).setVibrate(this,true);
                BeepManager.getInstance(this).setMsgSound(this,true);
                break;
            case R.id.add:
                add();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    private void delete() {

    }

    private void add() {
        File file=new File("D:/file_One/a");
        if(!file.exists()){
            file.mkdirs();
        }
        try {

            InputStream is=new FileInputStream(file);
            OutputStream os = new FileOutputStream(file);
            String s="hellowword";
            byte[]bytes=s.getBytes();
            os.write(bytes);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setType(int i) {
        UTPushUtil.getUtSetting().setRemindType(i);
    }

    private void bindAlias() {
        UTPushUtil.getUtSetting().bindTag(3, new String[]{"单身狗", "好人"}, "zzz", new ICallback() {
            @Override
            public void onError(String msg) {
                Log.i("bindsuccess",  "error");

            }

            @Override
            public void onSuccess(String msg) {
                Log.i("bindsuccess", msg + "success");
            }

            @Override
            public void onFailed(String code, String msg) {
                Log.i("bindsuccess", msg + "faile");

            }
        });
    }

    private void addAlias() {
        UTPushUtil.getUtSetting().addAlias("zzz", new ICallback() {
            @Override
            public void onSuccess(String msg) {
                Log.e("mainactivity", msg);

            }

            @Override
            public void onFailed(String code, String msg) {
                Log.e("mainactivity", code + "----" + msg);

            }

            @Override
            public void onError(String meg) {

            }
        });
    }
}
