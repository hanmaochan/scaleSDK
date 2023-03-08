package com.careforeyou.inbody.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.activity.base.CommonWhiteActivity;
import com.careforeyou.inbody.db.sp.Account;
import com.careforeyou.library.BIAWorker;
import com.careforeyou.library.BIAWorkerFactory;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.bt.Code;
import com.careforeyou.library.intface.BIACallback;


import java.util.List;

/**
 * Created by mihw on 2023/1/14.
 * Describe:
 */
public class DeviceBindActivity extends CommonWhiteActivity {

    private LinearLayout searchingLl;
    private TextView curBinedDeviceTv, macTv;
    private BIAWorker biaWorker;
    private String curBinedDeviceMac, curBinedDeviceName, curManufacturer, curModel, curVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentSubView(R.layout.activity_device_bind_layout, "Bind");
        backTv.setText(getString(R.string.tips_60));
        rightTv.setText(getString(R.string.tips_3));
        rightTv.setVisibility(View.INVISIBLE);

        searchingLl = findViewById(R.id.searching_ll);
        macTv = findViewById(R.id.device_name_tv);
        curBinedDeviceTv = findViewById(R.id.cur_bined_device_tv);

        curBinedDeviceMac = Account.getInstance(this).getCurBindDevice();
        curBinedDeviceName = Account.getInstance(this).getCurBindDeviceName();
        curManufacturer = Account.getInstance(this).getCurManufacturer();
        curModel = Account.getInstance(this).getCurModel();
        curVersion = Account.getInstance(this).getCurVersion();
        curBinedDeviceTv.setText(getString(R.string.tips_57, curBinedDeviceName, curBinedDeviceMac,
                curManufacturer,curModel,curVersion));

        //Initializes the work class
        biaWorker = BIAWorkerFactory.createWorker(this);
    }

    @Override
    protected void onOtherClick(View v) {
        super.onOtherClick(v);
        finish();
    }

    @Override
    protected void onRightClick(View v) {
        super.onRightClick(v);
        if (!TextUtils.isEmpty(curBinedDeviceMac)) {
            Account.getInstance(this).setCurBindDevice(curBinedDeviceMac);
        }
        if (!TextUtils.isEmpty(curBinedDeviceName)) {
            Account.getInstance(this).setCurBindDeviceName(curBinedDeviceName);
        }
        if (!TextUtils.isEmpty(curManufacturer)) {
            Account.getInstance(this).setCurManufacturer(curManufacturer);
        }
        if (!TextUtils.isEmpty(curModel)) {
            Account.getInstance(this).setCurModel(curModel);
        }
        if (!TextUtils.isEmpty(curVersion)) {
            Account.getInstance(this).setCurVersion(curVersion);
        }
        finish();
    }

    public void startBind(View view) {
        searchingLl.setVisibility(View.VISIBLE);
        macTv.setVisibility(View.GONE);
        rightTv.setVisibility(View.INVISIBLE);
        //Start binding
        biaWorker.bindDevice(new BIACallback() {
            @Override
            public void onState(int nCode, String msg) {//Binding state callback
                if (nCode == Code.STATE_TIME_OUT) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchingLl.setVisibility(View.GONE);
                            macTv.setVisibility(View.VISIBLE);
                            macTv.setText(msg);
                        }
                    });
                }

            }

            @Override
            public void onResult(int result, WeightEntity data) {

            }


            @Override
            public void onDeviceInfo(String name, String mac, String manufacturer, String model,
                                     String version) {//Bind a successful callback
                curBinedDeviceMac = mac;
                curBinedDeviceName = name;
                curManufacturer = manufacturer;
                curModel = model;
                curVersion = version;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchingLl.setVisibility(View.GONE);
                        macTv.setVisibility(View.VISIBLE);
                        macTv.setText(getString(R.string.tips_56, name, mac,manufacturer,model,version));
                        rightTv.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Release work class
        biaWorker.stop();
        biaWorker = null;
    }
}
