package com.careforeyou.inbody.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description: java类作用描述
 * @Author: mihaiwei
 * @CreateDate: 2019/11/11 9:19
 * @UpdateUser:
 * @UpdateDate: 2019/11/11 9:19
 * @UpdateRemark: 更新说明
 */
public class IndexItemBean implements Parcelable {

    private String itemValue;
    private String itemUnit;
    private int itemIcon;
    private String itemName;

    public IndexItemBean(Parcel in) {
        itemValue = in.readString();
        itemUnit = in.readString();
        itemIcon = in.readInt();
        itemName = in.readString();
    }

    public static final Creator<IndexItemBean> CREATOR = new Creator<IndexItemBean>() {
        @Override
        public IndexItemBean createFromParcel(Parcel in) {
            return new IndexItemBean(in);
        }

        @Override
        public IndexItemBean[] newArray(int size) {
            return new IndexItemBean[size];
        }
    };


    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(int itemIcon) {
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "IndexItemBean{" +
                "itemValue='" + itemValue + '\'' +
                ", itemUnit='" + itemUnit + '\'' +
                ", itemIcon=" + itemIcon +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemValue);
        dest.writeString(itemUnit);
        dest.writeInt(itemIcon);
        dest.writeString(itemName);
    }
}
