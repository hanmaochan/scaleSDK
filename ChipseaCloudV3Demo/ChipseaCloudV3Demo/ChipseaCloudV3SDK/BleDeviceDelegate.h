//
//  BleDeviceDelegate.h
//  CsBtUtil
//
//  Created by ChipSea on 15/12/26.
//  Copyright © 2015年 chipsea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BtData.h"
#import <CoreBluetooth/CoreBluetooth.h>


///==========================设备状态============================

typedef NS_ENUM(NSInteger, CsScaleState) {
    CsScaleStateUnknown, // 状态未知    |   status unknown  |   状態不明
    CsScaleStateUnauthorized, //未授权 |   unauthorized    |   無許可
    CsScaleStateOpened, //蓝牙已经打开，未连接    | Bluetooth is turned on but not connected  |   Bluetooth はオンになっていますが、接続されていません
    CsScaleStateClosed, // 蓝牙已经关闭   | bluetooth is off  |   ブルートゥースがオフです
    CsScaleStateBroadcast, // 接受广播中 |   receiving broadcast |   ブロードキャストの受信
    CsScaleStateConnecting,//   正在连接    |   connecting  |   接続中
    CsScaleStateConnected,// 已经连接   |   already connected   |   すでに接続されています
    CsScaleStateCalculating, // 透传建立后,且秤上有数据
    CsScaleStateWaitCalculat // 透传建立后,且秤上无数据
};

typedef NS_ENUM(NSInteger, ChipseaErrorType) {
    ChipseaErrorTypeBundleIDIsNotMatch,     // 包名不正确 | Bundle Identifier is not match
    ChipseaErrorTypeMacAddressIsNotMatch, // mac地址不正确 |  Mac address is not match
};

///==========================设备代理==========================
@protocol BleDeviceDelegate <NSObject>


/**
 广播数据解析后的回调
 The callback after the broadcast data is parsed
 ブロードキャスト データが解析された後のコールバック

 @param peripheral xxx
 @param device 数据
 */
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device;

@optional
/**
 透传数据解析后的回调
 The callback after the transparent data is parsed
 透過データが解析された後のコールバック

 @param device 数据
 */
-(void)afterConnectBtData:(BtData *)device;

/**
 已经连接，连接成功
 Already connected, the connection is successful
 接続済み、接続成功
 */
-(void)connectedPeripheral:(CBPeripheral *)peripheral;

/**
 连接断开回调
 Connection disconnection callback
 接続切断コールバック
 */
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error;



/**
 发生蓝牙状态变化时候的回调
 Callback when bluetooth status changes
 Bluetooth ステータスが変化したときのコールバック
 */
-(void)didUpdateCsScaleState:(CsScaleState)state;



#pragma mark 获得蓝牙广播数据
/**
 获得广播数据
 get broadcast data
 放送データを取得する
 
 @param data  获得的广播数据
 */
-(void)currentBroadcastData:(NSData *)data;


/**
 透传连接后，透传数据
 After transparent transmission connection, transparent transmission data
 透過伝送接続後、透過伝送データ
 */
-(void)afterConnectData:(NSData *)data;

/**
 返回错误
 return error
 エラーを返す
 */
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType;

@end
