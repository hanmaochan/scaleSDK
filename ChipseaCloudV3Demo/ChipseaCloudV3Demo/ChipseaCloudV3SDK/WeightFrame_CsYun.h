//
//  WeightFrame_CsYun.h
//  CsBtUtil
//
//  Created by iChipsea on 2018/11/23.
//  Copyright © 2018 chipsea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BtData_Weight.h"

@interface WeightFrame_CsYun : NSObject

@property (nonatomic, assign) Byte frameVer;
@property (nonatomic, assign) Byte startByte;
@property (nonatomic, assign) Byte dataAreaLen;
@property (nonatomic, assign) Byte checkByte;
@property (nonatomic, assign) Byte * frameFullByte;
@property (nonatomic, retain) NSData * frameFull;
@property (nonatomic, retain) NSData * dataArea;


@property (assign, nonatomic) BtScaleType type_id;   //秤的类型
@property (assign, nonatomic) int year;    //年
@property (assign, nonatomic) int month;   //月
@property (assign, nonatomic) int day;     //日
@property (assign, nonatomic) int hour;    //时
@property (assign, nonatomic) int minute;  //分
@property (assign, nonatomic) int second;  //秒

@property (assign, nonatomic) float r1;    //第一电极电阻，精度0.1

@property (assign, nonatomic) float weight;    //体重，单位KG精度0.01
@property (retain, nonatomic) NSString * weightStr; //体重字符串

@property (retain, nonatomic) NSString * time;  //时间 格式：yyyy-MM-dd HH:mm:ss
@property (retain, nonatomic) NSDate * weightDate;    //

@property (assign, nonatomic) long curProduct_id;        //产品编号

@property(nonatomic) Byte scaleproperty;                //从秤端上传上来的消息体，
@property(nonatomic) CsDeviceWeightUnit scaleunit;      //体重单位
@property(nonatomic) int scaledecimalnum;               //小数位数




/**
 * 蓝牙连接前的广播数据解析
 * Broadcast data analysis before Bluetooth connection
 * Bluetooth接続前の放送データ解析
 **/
-(id)initWith_BroadcastData:(NSData *)data;

/**
 * 蓝牙连接透传后的数据解析
 * Data analysis after Bluetooth connection transparent transmission
 * Bluetooth接続透過伝送後のデータ解析
 ***/
-(id)initWith_PassthroughByData:(NSData *)data;


#pragma mark 获取数据 - 广播
-(BtData_Weight *)getBtData_Weighter_Broadcast;


#pragma mark 获取数据 - 透传
-(BtData_Weight *)getBtData_Weighter_Passthrough;



/**
 生成同步时间下发数据
 Generate synchronization time delivery data
 同期時刻配信データの生成
 
 @return data
 */
+(NSData *)genSyncTimeData;


+(NSData *)utcTimeFull;

@end

