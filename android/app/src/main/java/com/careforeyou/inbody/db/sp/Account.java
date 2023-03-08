package com.careforeyou.inbody.db.sp;

import android.content.Context;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.bean.json.JsonMapper;
import com.careforeyou.library.bean.RoleInfo;
import com.careforeyou.library.utils.PrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hfei on 2016/5/9.
 */
public class Account extends PrefsUtil {

    private static Account instance;
    private Context context;

    public static Account getInstance(Context context) {
        if (instance == null) {
            synchronized (Account.class) {
                if (null == instance) {
                    instance = new Account(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * 析构函数
     *
     * @param context
     */
    public Account(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 设置当前角色信息
     */
    public void setRoleInfo(RoleInfo role) {
        setValue("cur_role_info", JsonMapper.toJson(role));
    }

    /**
     * 得到当前角色信息
     */
    public RoleInfo getRoleInfo() {
        RoleInfo roleInfo = JsonMapper.fromJson(getValue("cur_role_info", ""), RoleInfo.class);
        if (roleInfo == null) {
            roleInfo = new RoleInfo();
            roleInfo.setId(1);
            roleInfo.setSex(1);
            roleInfo.setHeight(170);
            roleInfo.setAge(28);
            setRoleInfo(roleInfo);
        }

        return roleInfo;
    }


    public void setCurRssi(int rssi) {
        setValue("rssi", rssi);
    }

    public int getCurRssi() {
        return getValue("rssi", -100);
    }


    public void setCurBindDevice(String mac) {
        setValue("CurBindDevice", mac);
    }

    public String getCurBindDevice() {
        return getValue("CurBindDevice", "");
    }
    public void setCurBindDeviceName(String mac) {
        setValue("CurBindDeviceName", mac);
    }

    public String getCurBindDeviceName() {
        return getValue("CurBindDeviceName", "");
    }

    public void setCurManufacturer(String manufacturer) {
        setValue("CurManufacturer", manufacturer);
    }

    public String getCurManufacturer() {
        return getValue("CurManufacturer", "");
    }

    public void setCurModel(String model) {
        setValue("CurModel", model);
    }

    public String getCurModel() {
        return getValue("CurModel", "");
    }

    public void setCurVersion(String version) {
        setValue("CurVersion", version);
    }

    public String getCurVersion() {
        return getValue("CurVersion", "");
    }
}
