//
//  WeightFrame_CsYunV3.h
//  CsBtUtil
//
//  Created by iChipsea on 2018/11/23.
//  Copyright © 2018 chipsea. All rights reserved.
////芯海蓝牙秤云端版通讯协议 v3

#import <Foundation/Foundation.h>
#import "BtData_Weight.h"


@interface WeightFrame_CsYunV3 : NSObject

@property (assign, nonatomic) BtScaleType type_id;   //秤的类型
@property (assign, nonatomic) long long ts;    //时间掇

@property (assign, nonatomic) float z34;    //全身电阻，精度0.1
@property (assign, nonatomic) float z12;    //电阻12，精度0.1
@property (assign, nonatomic) float z13;    //电阻13，精度0.1
@property (assign, nonatomic) float z14;    //电阻14，精度0.1
@property (assign, nonatomic) float z23;    //电阻23，精度0.1
@property (assign, nonatomic) float z24;    //电阻24，精度0.1
@property (retain, nonatomic) NSString * zRemark;   ////格式定义 type1:value | type2:value

@property (assign, nonatomic) float weight;    //体重，单位KG精度0.01
@property (retain, nonatomic) NSString * weightStr; //体重字符串

@property (retain, nonatomic) NSString * time;  //时间 格式：yyyy-MM-dd HH:mm:ss
@property (retain, nonatomic) NSDate * weightDate;    //

@property (assign, nonatomic) int product_id;        //产品编号

@property (assign, nonatomic) Byte  * frameFullByte;
@property (retain, nonatomic) NSData *frameFull;

@property(nonatomic) Byte scaleproperty;                //从秤端上传上来的消息体，
@property(nonatomic) CsDeviceWeightUnit scaleunit;      //体重单位
@property(nonatomic) int scaledecimalnum;               //小数位数


/**
 * 蓝牙连接前的广播数据解析
 **/
-(id)initWith_BroadcastData:(NSData *)data;

/**
 * 蓝牙连接透传后的数据解析
 ***/
-(id)initWith_PassthroughByData:(NSData *)data;


#pragma mark 获取数据 - 广播
-(BtData_Weight *)getBtData_Weighter_Broadcast;


#pragma mark 获取数据 - 透传
-(BtData_Weight *)getBtData_Weighter_Passthrough;

@end

