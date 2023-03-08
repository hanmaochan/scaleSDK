//
//  BtData.h
//  test
//
//  Created by mac on 15/4/13.
//  Copyright (c) 2015年 mac. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CsCommon.h"

typedef enum : NSUInteger {
    BtDeviceType_Unknown = 0,       // 类型：未知，| Type: unknown | タイプ: 不明
    BtDeviceType_Weight = 1,        // 类型：体重秤 | Type: Scale | タイプ: スケール
} BtDeviceType;             // 设备类型 | Equipment type | 機器の種類

// 设备连接方式 | Device connection method | デバイス接続方法
typedef NS_ENUM(NSInteger,DeviceConnType) {
    DeviceConnType_Unknown = 0,                 // 未知 | unknown | 不明
    DeviceConnType_BlueToothBroadcast = 1,      // 蓝牙广播方式 | Bluetooth broadcast method | Bluetoothブロードキャスト方式
    DeviceConnType_BlueToothPassthrough = 2,    // 蓝牙透传方式 | Bluetooth transparent transmission method | Bluetooth透過伝送方式
};

@interface BtData : NSObject

@property (nonatomic,strong) NSString * mac;            // mac地址 | mac address | Macアドレス
@property (nonatomic,strong) NSString * name;           // 名称 | name | 名前
@property (nonatomic,assign) BtDeviceType deviceType;   // 设备类型 体重秤 | Equipment Type Scale | 機器の種類のスケール
@property (nonatomic,assign) DeviceConnType connType;   // 设备连接方式 | Device connection method | デバイス接続方法
@property (assign, nonatomic) BOOL historyData;         // 是否为历史数据 | Is it historical data | ヒストリカルデータか

@property (nonatomic, assign) BOOL selected;    // 在绑定的时候，是否被选择 | 製本時に選択されているか

@property(nonatomic,assign)int protocol_type;  // 协议类型 | agreement type | 契約タイプ
/**
 *  根据制定字符串和制定的日期转换格式进行转换日期
 *  Convert the date according to the specified string and the specified date conversion format
 *  指定された文字列と指定された日付変換形式に従って日付を変換します
 *
 *  @param dateString 日期字符串
 *  @param format     格式字符串
 *
 *  @return 获得日期
 */
+(NSDate *)dateFromString:(NSString *)dateString byFormat:(NSString *)format;

@property (nonatomic,retain) NSData * mydata;   // 透传、广播，原始数据 | Transparent transmission, broadcast, raw data | 透過伝送、ブロードキャスト、生データ

@end
