package com.careforeyou.inbody.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.activity.base.CommonWhiteActivity;
import com.careforeyou.inbody.utils.DataUtils;
import com.careforeyou.library.bt.util.LocationUtil;
import com.careforeyou.library.utils.PermissionUtils;
import com.careforeyou.library.BIAWorker;
import com.careforeyou.library.BIAWorkerFactory;
import com.careforeyou.inbody.bean.IndexItemBean;
import com.careforeyou.library.bean.RoleInfo;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.bt.Code;
import com.careforeyou.library.intface.BIACallback;
import com.careforeyou.inbody.db.sp.Account;
import com.careforeyou.library.utils.LogUtil;

import java.util.List;

/**
 * @Description:
 * @Author: mihaiwei
 * @CreateDate: 2019/11/8 10:11
 * @UpdateUser:
 * @UpdateDate: 2019/11/8 10:11
 * @UpdateRemark:
 */
public class MeasurActivity extends CommonWhiteActivity {

    private static final String TAG = "MeasurActivity";
    private static final int USER_MSG_CODE = 1;
    TextView weightTv;
    TextView stateTv;
    TextView macTv;
    TextView rssiTv;
    TextView workStateTv;
    TextView itemScoreValueTv;
    TextView itemScoreUnitTv;
    TextView itemWeightValueTv;
    TextView itemWeightUnitTv;
    TextView itemBmiValueTv;
    TextView itemBmiUnitTv;
    TextView itemFatValueTv;
    TextView itemFatUnitTv;
    TextView itemMuscleWeightValueTv;
    TextView itemMuscleWeightUnitTv;
    TextView itemVisceraValueTv;
    TextView itemVisceraUnitTv;
    TextView itemWaterValueTv;
    TextView itemWaterUnitTv;
    TextView itemMetabolismValueTv;
    TextView itemMetabolismUnitTv;
    TextView itemBoneValueTv;
    TextView itemBoneUnitTv;
    TextView itemProteinValueTv;
    TextView itemProteinUnitTv;
    TextView itemBodyAgeValueTv;
    TextView itemBodyAgeUnitTv;
    TextView itemBodyTypeValueTv;
    TextView itemThinWeightValueTv;
    TextView itemThinWeightUnitTv;
    LinearLayout indexLl;
    LinearLayout scoreLl;
    LinearLayout weightLl;
    LinearLayout bmiLl;
    LinearLayout fatLl;
    LinearLayout muscleWeightLl;
    LinearLayout viserLl;
    LinearLayout waterLl;
    LinearLayout meatorLl;
    LinearLayout boneLl;
    LinearLayout proneLl;
    LinearLayout bodyLl;
    LinearLayout bodyTypeLl;
    LinearLayout thinWeightLl;
    TextView historyDataTv;
    FrameLayout toHistoryListFl;
    private List<IndexItemBean> indexItemBeanList;
    private RoleInfo roleInfo;
    private TextView curBindDeviceTv;
    private Button bindBt, toMeasureBt;

    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 7;
    private BIAWorker biaWorker;
    private int curUnit = 0;
    private Handler handler;
    private String stateMsg;
    private WeightEntity weightEntity;
    private boolean isStop;
    private String curBindDeviceMac, curBindDeviceName, curManufacturer, curModel, curVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate");
        setContentSubView(R.layout.activity_measur_layout, getString(R.string.tips_4));
        backImg.setVisibility(View.GONE);
        backTv.setText("");
        rightTv.setText(getString(R.string.tips_2));
        roleInfo = Account.getInstance(this).getRoleInfo();

        LogUtil.i(TAG, "roleInfo:" + roleInfo.toString());
        initViews();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    updateView(stateMsg);
                } else if (msg.what == 1) {
                    updateIndexes();
                } else if (msg.what == 2) {
                    weightTv.setText(weightEntity.getWeight() + "kg");
                }
            }
        };

        //1.Apply for Bluetooth and location permissions
        initPermission();

    }


    @Override
    protected void onResume() {
        super.onResume();
        curBindDeviceMac = Account.getInstance(this).getCurBindDevice();
        curBindDeviceName = Account.getInstance(this).getCurBindDeviceName();
        curManufacturer = Account.getInstance(this).getCurManufacturer();
        curModel = Account.getInstance(this).getCurModel();
        curVersion = Account.getInstance(this).getCurVersion();
        curBindDeviceTv.setText(getString(R.string.tips_57, curBindDeviceName, curBindDeviceMac,
                curManufacturer, curModel, curVersion));
        if (TextUtils.isEmpty(curBindDeviceMac)) {
            bindBt.setText(getString(R.string.tips_58));
            toMeasureBt.setVisibility(View.GONE);
        } else {
            bindBt.setText(getString(R.string.tips_59));
            toMeasureBt.setVisibility(View.VISIBLE);
        }

    }

    private void initViews() {
        weightTv = findViewById(R.id.weight_tv);
        stateTv = findViewById(R.id.state_tv);
        macTv = findViewById(R.id.mac_tv);
        rssiTv = findViewById(R.id.rssi_tv);
        workStateTv = findViewById(R.id.work_state_tv);
        itemScoreValueTv = findViewById(R.id.item_scor_value_tv);
        itemScoreUnitTv = findViewById(R.id.item_scor_unit_tv);
        itemWeightValueTv = findViewById(R.id.item_weight_value_tv);
        itemWeightUnitTv = findViewById(R.id.item_weight_unit_tv);
        itemBmiValueTv = findViewById(R.id.item_bmi_value_tv);
        itemBmiUnitTv = findViewById(R.id.item_bmi_unit_tv);
        itemFatValueTv = findViewById(R.id.item_fat_value_tv);
        itemFatUnitTv = findViewById(R.id.item_fat_unit_tv);
        itemMuscleWeightValueTv = findViewById(R.id.item_musle_weight_value_tv);
        itemMuscleWeightUnitTv = findViewById(R.id.item_musle_weight_unit_tv);
        itemVisceraValueTv = findViewById(R.id.item_viscera_value_tv);
        itemVisceraUnitTv = findViewById(R.id.item_viscera_unit_tv);
        itemWaterValueTv = findViewById(R.id.item_water_value_tv);
        itemWaterUnitTv = findViewById(R.id.item_water_unit_tv);
        itemMetabolismValueTv = findViewById(R.id.item_metabolism_value_tv);
        itemMetabolismUnitTv = findViewById(R.id.item_metabolism_unit_tv);
        itemBoneValueTv = findViewById(R.id.item_bone_value_tv);
        itemBoneUnitTv = findViewById(R.id.item_bone_unit_tv);
        itemProteinValueTv = findViewById(R.id.item_protein_value_tv);
        itemProteinUnitTv = findViewById(R.id.item_protein_unit_tv);
        itemBodyAgeValueTv = findViewById(R.id.item_bodyage_value_tv);
        itemBodyAgeUnitTv = findViewById(R.id.item_bodyage_unit_tv);
        itemBodyTypeValueTv = findViewById(R.id.item_body_type_value_tv);
        itemThinWeightValueTv = findViewById(R.id.item_thin_weight_value_tv);
        itemThinWeightUnitTv = findViewById(R.id.item_thin_weight_unit_tv);
        indexLl = findViewById(R.id.index_ll);
        scoreLl = findViewById(R.id.scor_ll);
        weightLl = findViewById(R.id.weight_ll);
        bmiLl = findViewById(R.id.bmi_ll);
        fatLl = findViewById(R.id.fat_ll);
        muscleWeightLl = findViewById(R.id.musle_weight_ll);
        viserLl = findViewById(R.id.viser_ll);
        waterLl = findViewById(R.id.water_ll);
        meatorLl = findViewById(R.id.meator_ll);
        boneLl = findViewById(R.id.bone_ll);
        proneLl = findViewById(R.id.prone_ll);
        bodyLl = findViewById(R.id.body_ll);
        bodyTypeLl = findViewById(R.id.body_type_ll);
        thinWeightLl = findViewById(R.id.thin_weight_ll);
        historyDataTv = findViewById(R.id.history_data_tv);
        toHistoryListFl = findViewById(R.id.to_history_list_fl);
        curBindDeviceTv = findViewById(R.id.cur_bined_device_tv);
        bindBt = findViewById(R.id.bind_bt);
        toMeasureBt = findViewById(R.id.toMeasure_bt);
    }


    private void updateView(String msg) {
        LogUtil.i(TAG, "+updateView+" + msg);
        stateTv.setText(msg);
        workStateTv.setText(getWorkState(biaWorker.getState()));
        macTv.setText(biaWorker.getAddress());
        rssiTv.setText(biaWorker.getRSSI() + "");
        LogUtil.i(TAG, "SDK_Version:" + biaWorker.getVersionCode());
    }

    private String getWorkState(int state) {
        if (state == 0) {
            return "IDLE";
        } else if (state == 1) {
            return "DOING";
        } else {
            return "DONE";
        }
    }


    private void initPermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            boolean hasSelfPermission = PermissionUtils.hasSelfPermissions(this,
                    LocationUtil.getPermistions());
            if (!hasSelfPermission) {
                ActivityCompat.requestPermissions(this,
                        LocationUtil.getPermistions(),
                        ACCESS_COARSE_LOCATION_REQUEST_CODE);
            } else {
                createWorker();
            }
        } else {
            createWorker();
        }
    }

    /**
     * Initializes the work class
     */
    private void createWorker() {
        biaWorker = BIAWorkerFactory.createWorker(this);
        // Whether to print logs Tag: ChipseaLib_
        BIAWorkerFactory.setShowLog(true);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == ACCESS_COARSE_LOCATION_REQUEST_CODE) {
            boolean hasSelfPermission = PermissionUtils.verifyPermissions(grantResults);
            if (!hasSelfPermission) {
                finish();
            } else {
                createWorker();
            }
        }

    }

    @Override
    protected void onRightClick(View v) {
        super.onRightClick(v);
        //Go to the user information page
        startActivityForResult(new Intent(this, UserMsgActivity.class), USER_MSG_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        roleInfo = Account.getInstance(this).getRoleInfo();
        LogUtil.i(TAG, "roleInfo:" + roleInfo.toString());
    }

    @Override
    protected void onOtherClick(View v) {
        super.onOtherClick(v);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        //Work class release
        if (biaWorker != null) {
            biaWorker.stop();
            biaWorker = null;
        }

    }

    private void updateIndexes() {

        LogUtil.i(TAG, "++show list++");

        indexLl.setVisibility(View.VISIBLE);
        if (indexItemBeanList.size() > 2) {

            scoreLl.setVisibility(View.VISIBLE);
            weightLl.setVisibility(View.VISIBLE);
            bmiLl.setVisibility(View.VISIBLE);
            fatLl.setVisibility(View.VISIBLE);
            muscleWeightLl.setVisibility(View.VISIBLE);
            viserLl.setVisibility(View.VISIBLE);
            waterLl.setVisibility(View.VISIBLE);
            meatorLl.setVisibility(View.VISIBLE);
            boneLl.setVisibility(View.VISIBLE);
            proneLl.setVisibility(View.VISIBLE);
            bodyLl.setVisibility(View.VISIBLE);
            bodyTypeLl.setVisibility(View.VISIBLE);
            thinWeightLl.setVisibility(View.VISIBLE);


            weightLl.setBackgroundColor(getResources().getColor(R.color.text_gray_1));
            bmiLl.setBackgroundColor(getResources().getColor(R.color.white));


            itemScoreValueTv.setText(indexItemBeanList.get(0).getItemValue());
            itemScoreUnitTv.setText(indexItemBeanList.get(0).getItemUnit());

            itemWeightValueTv.setText(indexItemBeanList.get(1).getItemValue());
            itemWeightUnitTv.setText(indexItemBeanList.get(1).getItemUnit());


            if (indexItemBeanList.size() > 2) {

                itemBmiValueTv.setText(indexItemBeanList.get(2).getItemValue());
                itemBmiUnitTv.setText(indexItemBeanList.get(2).getItemUnit());


                itemFatValueTv.setText(indexItemBeanList.get(3).getItemValue());
                itemFatUnitTv.setText(indexItemBeanList.get(3).getItemUnit());


                itemMuscleWeightValueTv.setText(indexItemBeanList.get(4).getItemValue());
                itemMuscleWeightUnitTv.setText(indexItemBeanList.get(4).getItemUnit());


                itemBodyTypeValueTv.setText(indexItemBeanList.get(5).getItemValue());


                itemVisceraValueTv.setText(indexItemBeanList.get(6).getItemValue());
                itemVisceraUnitTv.setText(indexItemBeanList.get(6).getItemUnit());

                itemWaterValueTv.setText(indexItemBeanList.get(7).getItemValue());
                itemWaterUnitTv.setText(indexItemBeanList.get(7).getItemUnit());

                itemMetabolismValueTv.setText(indexItemBeanList.get(8).getItemValue());
                itemMetabolismUnitTv.setText(indexItemBeanList.get(8).getItemUnit());

                itemBoneValueTv.setText(indexItemBeanList.get(9).getItemValue());
                itemBoneUnitTv.setText(indexItemBeanList.get(9).getItemUnit());

                itemProteinValueTv.setText(indexItemBeanList.get(10).getItemValue());
                itemProteinUnitTv.setText(indexItemBeanList.get(10).getItemUnit());

                itemBodyAgeValueTv.setText(indexItemBeanList.get(11).getItemValue());
                itemBodyAgeUnitTv.setText(indexItemBeanList.get(11).getItemUnit());

                itemThinWeightValueTv.setText(indexItemBeanList.get(12).getItemValue());
                itemThinWeightUnitTv.setText(indexItemBeanList.get(12).getItemUnit());

            }

        } else {

            scoreLl.setVisibility(View.GONE);
            weightLl.setVisibility(View.VISIBLE);
            bmiLl.setVisibility(View.VISIBLE);
            fatLl.setVisibility(View.GONE);
            muscleWeightLl.setVisibility(View.GONE);
            viserLl.setVisibility(View.GONE);
            waterLl.setVisibility(View.GONE);
            meatorLl.setVisibility(View.GONE);
            boneLl.setVisibility(View.GONE);
            proneLl.setVisibility(View.GONE);
            bodyLl.setVisibility(View.GONE);
            bodyTypeLl.setVisibility(View.GONE);
            thinWeightLl.setVisibility(View.GONE);

            weightLl.setBackgroundColor(getResources().getColor(R.color.white));
            bmiLl.setBackgroundColor(getResources().getColor(R.color.text_gray_1));

            itemWeightValueTv.setText(indexItemBeanList.get(0).getItemValue());
            itemWeightUnitTv.setText(indexItemBeanList.get(0).getItemUnit());

            itemBmiValueTv.setText(indexItemBeanList.get(1).getItemValue());
            itemBmiUnitTv.setText(indexItemBeanList.get(1).getItemUnit());

        }
    }

    /**
     * to bind scale device
     *
     * @param view
     */
    public void toBindDevice(View view) {
        if (TextUtils.isEmpty(curBindDeviceMac)) {
            startActivity(new Intent(this, DeviceBindActivity.class));
        } else {
            curBindDeviceMac = "";
            curBindDeviceName = "";
            curManufacturer = "";
            curModel = "";
            curVersion = "";
            Account.getInstance(this).setCurBindDevice(null);
            Account.getInstance(this).setCurBindDeviceName(null);
            Account.getInstance(this).setCurManufacturer(null);
            Account.getInstance(this).setCurModel(null);
            Account.getInstance(this).setCurVersion(null);
            curBindDeviceTv.setText(getString(R.string.tips_57, curBindDeviceName, curBindDeviceMac,
                    curManufacturer,curModel,curVersion));
            bindBt.setText(getString(R.string.tips_58));
            toMeasureBt.setVisibility(View.GONE);
        }

    }

    public void toMeasure(View view) {
        //Start measuring
        biaWorker.start(curBindDeviceMac, roleInfo, null, new BIACallback() {
            @Override
            public void onState(int nCode, String msg) {//Measure status callback
                LogUtil.i(TAG, "++code+" + nCode + "++msg+" + msg);
                stateMsg = msg;
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResult(int result, WeightEntity data) {//Measurement result callback
                weightEntity = data;
                LogUtil.i(TAG, "++result+" + weightEntity.toString() + "++result_code+" + result);
                if (result == Code.STABLED) {
                    DataUtils dataUtils = new DataUtils();
                    //Result presentation
                    indexItemBeanList = dataUtils.getReportDetalis(
                            MeasurActivity.this, roleInfo, weightEntity);
                    LogUtil.i(TAG, "++indexItemBeanList+" + indexItemBeanList.toString());
                    handler.sendEmptyMessage(1);

                    if (!isStop) {
                        biaWorker.stop();
                        isStop = true;
                    }


                }
                handler.sendEmptyMessage(2);
            }


            @Override
            public void onDeviceInfo(String name, String mac, String manufacturer, String model,
                                     String version) {

            }
        });
        isStop = false;
    }
}
