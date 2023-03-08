//
//  BtData_Weight.h
//  CsBtUtil
//
//  Created by iChipsea on 2018/11/13.
//  Copyright © 2018 chipsea. All rights reserved.
//  体重相关信息

#import "BtData.h"

//设备类别
typedef NS_ENUM(NSInteger, BtScaleType)
{
    BtScaleType_Weigher     = 0,        //人体秤              C0
    BtScaleType_FatC4       = 1,        //脂肪秤(四电极数据电阻) C4 阻抗单频率测量
    BtScaleType_FatC8       = 2,        //脂肪秤(八电极数据电阻) C8 阻抗单频率测量
    BtScaleType_FatCA20     = 3,        //CA20广播脂肪秤 P21广播秤
    BtScaleType_FatP23      = 4,         //P23广播脂肪秤
    BtScaleType_FatC5       = 5,        // 脂肪秤 C5（四电极，阻抗单频率测量/带心率）
    BtScaleType_FatC6       = 6,        // 脂肪秤 C6（四电极，阻抗多频率测量/带心率）
    BtScaleType_FatC9       = 7,        // 脂肪秤 C9 八电极（阻抗单频率测量/带心率） 多包数据
    BtScaleType_FatCA       = 8,        // 脂肪秤 CA 八电极（阻抗多频率测量/带心率） 多包数据
};


typedef enum
{
    ScaleProtocolTypeChipsea = 10000,       //芯海秤 芯海协议
    ScaleProtocolTypeChipseaYun = 10006,    //芯海蓝牙秤云端版通讯协议 v2
    ScaleProtocolTypeChipseaYunV3 = 10007,  //芯海蓝牙秤云端版通讯协议 v3

} ScaleProtocolType;


typedef enum {
    DataStatus_Received,       //结果数据
    DataStatus_Wait,            //等待下一包数据
}DataStatus;

@interface BtData_Weight : BtData

/**
 *  Bit       7      6      5      4      3      2      1      0
 *          保留    保留   保留    单位    单位  小数位  小数位   是否锁定
 */
@property (assign, nonatomic) Byte scaleProperty;       // 消息体属性    |   message body properties |   メッセージ本文のプロパティ
@property (assign, nonatomic) long product_id;          // 产品id |   product id  |   製品番号
@property (retain, nonatomic) NSString * product_modelNumber;   // 型号  |   Model number | モデル
@property (retain, nonatomic) NSString * product_ver;   // 版本号  |   Version number | バージョン番号です
@property (retain, nonatomic) NSString * product_name;  // 制造商 |   manufacturer | メーカーです

@property (assign, nonatomic) int protocol_ver;         //
@property (assign, nonatomic) BtScaleType type_id;      // 秤类型 | scale type | スケールタイプ
@property (assign, nonatomic) BOOL locked;              // 是否锁定 | Whether to lock | ロックするかどうか

@property (assign, nonatomic) float weight;             // 体重 | weight | 重さ
@property (retain, nonatomic) NSString * weightStr;
@property (assign, nonatomic) float axunge;             // 脂肪 | Fat | 肥満
@property (assign, nonatomic) float water;              // 水份 | Moisture | 水分
@property (assign, nonatomic) float muscle;             // 肌肉 | muscle | 筋
@property (assign, nonatomic) float bmr;                // 基础代谢 | Basal metabolism | 基礎代謝
@property (assign, nonatomic) float visceral_fat;       // 内脏脂肪 | Visceral fat | 内臓脂肪


// 下面两项可忽略 | The following two items can be ignored | 次の 2 項目は無視できます
@property (assign, nonatomic) float ori_visceral_fat;   // ---  在 WeihtFrame_Cs中使用，0是正常，1，性别要反转下传
@property (assign, nonatomic) int scaleFlag;            // ---   在 WeihtFrame_Cs中使用，0是正常，1，性别要反转下传


@property (assign, nonatomic) float bone;               // 骨量 | bone mass | 骨量
@property (assign, nonatomic) float bmi;                // bmi
@property (assign, nonatomic) float body_age;           // 身体年龄 | physical age | 身体年齢
@property (assign, nonatomic) int roleId;               // 角色id
@property (retain, nonatomic) NSDate * weightDate;      // 测量时间 | measure time | 時間を計る
@property (assign, nonatomic) float r1;                 // 第一电极电阻，精度0.1 | The first electrode resistance, accuracy 0.1 | 最初の電極抵抗、精度 0.1



// 下面两项可忽略 | The following two items can be ignored | 次の 2 項目は無視できます
@property (retain, nonatomic) NSString * remark;        // 电阻列表字符串 （remark = "1:" + Z12 + "," + Z13 + "," + Z14 + "," + Z23 + "," + Z24;），格式定义 type1:value | type2:value
@property(nonatomic) int serialNumber;      //流水号（只有芯海广播体脂秤有，它没有时间）为0时，表示没有体重数据 从1开始流水；每次测量后结果加1，到255后又从1开始流水，0保留




@end

