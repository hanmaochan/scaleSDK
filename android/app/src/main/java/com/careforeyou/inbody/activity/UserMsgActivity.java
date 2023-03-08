package com.careforeyou.inbody.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.activity.base.CommonWhiteActivity;
import com.careforeyou.inbody.db.sp.Account;
import com.careforeyou.inbody.view.SwitchTextView;
import com.careforeyou.library.bean.RoleInfo;

import butterknife.BindView;


/**
 * @Description:
 * @Author: mihaiwei
 * @CreateDate: 2019/11/8 8:47
 * @UpdateUser:
 * @UpdateDate: 2019/11/8 8:47
 * @UpdateRemark:
 */
public class UserMsgActivity extends CommonWhiteActivity {

    SwitchTextView sexStv;
    EditText heightEt;
    EditText ageEt;
    private RoleInfo roleInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentSubView(R.layout.activity_user_msg_layout, getString(R.string.tips_2));


        sexStv = findViewById(R.id.sex_stv);
        heightEt = findViewById(R.id.height_et);
        ageEt = findViewById(R.id.age_et);

        backTv.setText(getString(R.string.tips_60));
        rightTv.setText(getString(R.string.tips_3));
        roleInfo = Account.getInstance(this).getRoleInfo();
        initViews();

    }

    private void initViews() {
        sexStv.requestFocus();
        if (roleInfo != null) {
            if (roleInfo.getSex() == 1) {
                sexStv.setChecked(true);
            } else {
                sexStv.setChecked(false);
            }
            heightEt.setText(roleInfo.getHeight() + "");
            ageEt.setText(roleInfo.getAge() + "");
        }
        sexStv.setOnChangedListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    roleInfo.setSex(1);
                } else {
                    roleInfo.setSex(0);
                }
            }
        });
    }

    @Override
    protected void onRightClick(View v) {
        super.onRightClick(v);
        int height = Integer.valueOf(heightEt.getText().toString());
        int age = Integer.valueOf(ageEt.getText().toString());
        roleInfo.setHeight(height);
        roleInfo.setAge(age);
        Account.getInstance(this).setRoleInfo(roleInfo);
        finish();
    }


    @Override
    protected void onOtherClick(View v) {
        super.onOtherClick(v);
        finish();
    }
}
