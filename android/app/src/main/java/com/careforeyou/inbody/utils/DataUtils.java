package com.careforeyou.inbody.utils;

import android.content.Context;
import android.os.Parcel;

import com.careforeyou.inbody.R;
import com.careforeyou.inbody.bean.IndexItemBean;
import com.careforeyou.library.bean.RoleInfo;
import com.careforeyou.library.bean.WeightEntity;
import com.careforeyou.library.utils.DecimalFormatUtils;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public List<IndexItemBean> getReportDetalis(Context context, RoleInfo currRoleInfo, WeightEntity weightEntity) {

        List<IndexItemBean> indexItemBeans = new ArrayList<>();
        int age = currRoleInfo.getAge();
        if (weightEntity.getR1() > 0) {
            if (age > 5) {
                getRlTagergetItem(context, weightEntity, indexItemBeans);
            } else {
                getWeightTargetItem(context, weightEntity, indexItemBeans);
            }
        } else {
            if (weightEntity.getAxunge() > 0) {
                getRlTagergetItem(context, weightEntity, indexItemBeans);
            } else {
                getWeightTargetItem(context, weightEntity, indexItemBeans);
            }
        }
        return indexItemBeans;
    }

    private void getWeightTargetItem(Context context, WeightEntity weightEntity,
                                     List<IndexItemBean> indexItemBeans) {
        IndexItemBean indexItemBeanWeight = new IndexItemBean(Parcel.obtain());
        indexItemBeanWeight.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getWeight()));
        indexItemBeanWeight.setItemUnit(context.getString(R.string.unit));
        indexItemBeanWeight.setItemIcon(R.mipmap.report_weight_icon);
        indexItemBeanWeight.setItemName(context.getString(R.string.tips_26));

        IndexItemBean indexItemBeanBmi = new IndexItemBean(Parcel.obtain());
        indexItemBeanBmi.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getBmi()));
        indexItemBeanBmi.setItemUnit("");
        indexItemBeanBmi.setItemIcon(R.mipmap.report_bmi_icon);
        indexItemBeanBmi.setItemName(context.getString(R.string.tips_27));

        indexItemBeans.add(indexItemBeanWeight);
        indexItemBeans.add(indexItemBeanBmi);
    }


    private List<IndexItemBean> getRlTagergetItem(Context context, WeightEntity weightEntity,
                                                  List<IndexItemBean> indexItemBeans) {
        //分数
        IndexItemBean indexItemBeanScore = new IndexItemBean(Parcel.obtain());
        indexItemBeanScore.setItemValue(String.valueOf(weightEntity.getScore()));
        indexItemBeanScore.setItemUnit("");
        indexItemBeanScore.setItemIcon(R.mipmap.report_bodily_icon);
        indexItemBeanScore.setItemName(context.getString(R.string.tips_13));

        //体脂率
        IndexItemBean indexItemBeanFat = new IndexItemBean(Parcel.obtain());
        indexItemBeanFat.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getAxunge()));
        indexItemBeanFat.setItemUnit(context.getString(R.string.unit_1));
        indexItemBeanFat.setItemIcon(R.mipmap.report_axunge_icon);
        indexItemBeanFat.setItemName(context.getString(R.string.tips_17));


        //肌肉重量
        IndexItemBean indexItemBeanMusleWeight = new IndexItemBean(Parcel.obtain());
        indexItemBeanMusleWeight.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getMuscleWeight()));
        indexItemBeanMusleWeight.setItemUnit(context.getString(R.string.unit));
        indexItemBeanMusleWeight.setItemIcon(R.mipmap.report_gu_muscle_icon);
        indexItemBeanMusleWeight.setItemName(context.getString(R.string.tips_19));

/*
        IndexItemBean indexItemBeanMusle = new IndexItemBean(Parcel.obtain());
        indexItemBeanMusle.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getMuscle()));
        indexItemBeanMusle.setItemUnit(context.getString(R.string.unit));
        indexItemBeanMusle.setItemIcon(R.mipmap.report_muscle_weight_icon);
        indexItemBeanMusle.setItemName(context.getString(R.string.tips_19));*/


        IndexItemBean indexItemBeanViscera = new IndexItemBean(Parcel.obtain());
        indexItemBeanViscera.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getViscera()));
        indexItemBeanViscera.setItemUnit("");
        indexItemBeanViscera.setItemIcon(R.mipmap.report_viscera_icon);
        indexItemBeanViscera.setItemName(context.getString(R.string.tips_20));


        IndexItemBean indexItemBeanWater = new IndexItemBean(Parcel.obtain());
        indexItemBeanWater.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getWater()));
        indexItemBeanWater.setItemUnit(context.getString(R.string.unit_1));
        indexItemBeanWater.setItemIcon(R.mipmap.report_water_icon);
        indexItemBeanWater.setItemName(context.getString(R.string.tips_21));


        IndexItemBean indexItemBeanMetabolism = new IndexItemBean(Parcel.obtain());
        indexItemBeanMetabolism.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getMetabolism()));
        indexItemBeanMetabolism.setItemUnit(context.getString(R.string.tips_23));
        indexItemBeanMetabolism.setItemIcon(R.mipmap.report_metabolism_icon);
        indexItemBeanMetabolism.setItemName(context.getString(R.string.tips_22));


        IndexItemBean indexItemBeanBone = new IndexItemBean(Parcel.obtain());
        indexItemBeanBone.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getBone()));
        indexItemBeanBone.setItemUnit(context.getString(R.string.unit));
        indexItemBeanBone.setItemIcon(R.mipmap.report_bone_icon);
        indexItemBeanBone.setItemName(context.getString(R.string.tips_24));


        IndexItemBean indexItemBeanProtain = new IndexItemBean(Parcel.obtain());
        indexItemBeanProtain.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getProtein()));
        indexItemBeanProtain.setItemUnit(context.getString(R.string.unit_1));
        indexItemBeanProtain.setItemIcon(R.mipmap.report_protein_icon);
        indexItemBeanProtain.setItemName(context.getString(R.string.tips_25));


        IndexItemBean indexItemBeanBodyAge = new IndexItemBean(Parcel.obtain());
        indexItemBeanBodyAge.setItemValue(weightEntity.getBody_age() + "");
        indexItemBeanBodyAge.setItemUnit("");
        indexItemBeanBodyAge.setItemIcon(R.mipmap.report_bodyage_icon);
        indexItemBeanBodyAge.setItemName(context.getString(R.string.tips_15));

        //身体等级
        IndexItemBean indexItemBodyLeve = new IndexItemBean(Parcel.obtain());
        indexItemBodyLeve.setItemValue(weightEntity.getBodyLeve());
        indexItemBodyLeve.setItemUnit("");
        indexItemBodyLeve.setItemIcon(R.mipmap.report_bodyage_icon);
        indexItemBodyLeve.setItemName(context.getString(R.string.tips_52));


        //去脂体重
        IndexItemBean indexItemThinWeight = new IndexItemBean(Parcel.obtain());
        indexItemThinWeight.setItemValue(DecimalFormatUtils.getTwo(weightEntity.getThinWeight()) + "");
        indexItemThinWeight.setItemUnit(context.getString(R.string.unit));
        indexItemThinWeight.setItemIcon(R.mipmap.report_weight_icon);
        indexItemThinWeight.setItemName(context.getString(R.string.tips_53));


        indexItemBeans.add(indexItemBeanScore);
        getWeightTargetItem(context, weightEntity, indexItemBeans);
        indexItemBeans.add(indexItemBeanFat);
        indexItemBeans.add(indexItemBeanMusleWeight);
        indexItemBeans.add(indexItemBodyLeve);
        indexItemBeans.add(indexItemBeanViscera);
        indexItemBeans.add(indexItemBeanWater);
        indexItemBeans.add(indexItemBeanMetabolism);
        indexItemBeans.add(indexItemBeanBone);
        indexItemBeans.add(indexItemBeanProtain);
        indexItemBeans.add(indexItemBeanBodyAge);
        indexItemBeans.add(indexItemThinWeight);
        return indexItemBeans;
    }


}
