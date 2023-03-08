//
//  ChipseaBtUtil.h
//  ChipseaCloudV3SDK
//
//  Created by iChipsea on 2023/1/13.
//

#import <Foundation/Foundation.h>
#import <CoreBluetooth/CoreBluetooth.h>
#import "BleDefines.h"


#import "BleDeviceDelegate.h"

#import "BtData.h"
//#import "BaseProtocol.h"

@class BaseProtocol;

//#import "DeviceInfo.h"

///==========================实体============================
@interface ChipseaBtUtil : NSObject <CBCentralManagerDelegate,CBPeripheralDelegate,CBPeripheralManagerDelegate>
{
    CBCentralManager *_manager;

    ///设备 Device
    BtData *_btDevice;
}

#pragma mark -单例模式获取
+(ChipseaBtUtil *)getInstance;

#pragma mark 自定义变量
@property(nonatomic,weak) id<BleDeviceDelegate> delegate;
@property(nonatomic,assign) CsScaleState state;

@property(nonatomic) BOOL stopAdvertisementState;//停止广播标志
@property(nonatomic,strong) CBPeripheral *activePeripheral; //当前透传连接设备

@property (nonatomic, retain) BaseProtocol * curProtocol;               // 当前要执行的协议
@property (nonatomic, assign) BtDeviceType curDeviceType;               // 当前要连接的设备类型 （体重秤）
@property (nonatomic, retain) NSString * curDeviceMacAddress;           // 当前要连接的设备的mac 地址
@property (nonatomic, assign) BOOL isBounding;                          // 当前状态，是否为绑定状态
@property (nonatomic, assign) NSUInteger deviceProtocolType;



/**
 设置当前要搜索和执行的设备类别
 Set the current device category to search and execute
 現在のデバイス カテゴリを検索して実行するように設定する

 @param macAddress mac地址    |   mac address | Macアドレス
 @param isBounding 是否绑定     |   Whether to bind     |   バインドするかどうか
 */
-(void)setCurBtDeviceBymacAddress:(NSString *)macAddress isBounding:(BOOL)isBounding;

/**
 设置当前要搜索和执行的设备类别
 Set the current device category to search and execute
 現在のデバイス カテゴリを検索して実行するように設定する
 
 @param type 类别
 @param macAddress mac地址    |   mac address | Macアドレス
 @param isBounding 是否绑定状态     |   Whether to bind     |   バインドするかどうか
 */
-(void)setCurBtDeviceType:(BtDeviceType )type macAddress:(NSString *)macAddress isBounding:(BOOL)isBounding;


/**
 设置当前要搜索和执行的设备类别
 Set the current device category to search and execute
 現在のデバイス カテゴリを検索して実行するように設定する
 
 @param type 类别
 @param macAddress mac地址    |   mac address | Macアドレス
 @param isBounding 是否绑定     |   Whether to bind     |   バインドするかどうか
 @param deviceProtocolType 类型
 */
-(void)setCurBtDeviceType:(BtDeviceType )type macAddress:(NSString *)macAddress isBounding:(BOOL)isBounding deviceProtocolType:(NSUInteger) deviceProtocolType;


/**
 *  向下发送数据
 *  send data down
 *  データを送信する
 *
 *@param optionIndex 操作序号   |   Operation number    |   操作番号
 *@param dataDic 操作数据
 */
-(void)sendOption:(int)optionIndex dataDic:(NSDictionary *)dataDic;



/*
  开始查找蓝牙设备 |
  Start scanning for bluetooth devices    |
 Bluetooth デバイスのスキャンを開始します
 */
-(void)startScanBluetoothDevice;

/*
 停止查找蓝牙设备
 Stop scanning for bluetooth devices
 Bluetooth デバイスのスキャンを停止する
 */
-(void)stopScanBluetoothDevice;

/**
 联接蓝牙设备
 Connect Bluetooth device
 Bluetooth デバイスを接続する
 */
-(BOOL)connect:(CBPeripheral *)peripheral;

/**
 写数据到蓝牙设备
 Write data to bluetooth device
 Bluetooth デバイスにデータを書き込む
 */
-(void)writeValueToPeripheral:(CBPeripheral *)peripheral Data:(NSData *)data;

-(void)writeValueToPeripheral:(CBPeripheral *)peripheral Data:(NSData *)data serviceUUID:(NSString *)serviceUUID writeUUID:(NSString *)writeUUID;

-(void)writeValue:(int)serviceUUID characteristicUUID:(int)characteristicUUID p:(CBPeripheral *)p data:(NSData *)data;

/**
 开始同步历史数据
 Start synchronizing historical data
 履歴データの同期を開始します
 */
-(void)enableRead_upLoadHistoryData;

/**
 使能读，这样会在通道中读取到函数
 Enable reading, so that the function will be read in the channel
 関数がチャネルで読み取られるように、読み取りを有効にします。
 */
-(void)enableRead:(CBPeripheral*)p;

/**
 使能读，这样会在通道中读取到函数
 Enable reading, so that the function will be read in the channel
 関数がチャネルで読み取られるように、読み取りを有効にします。
 
 @param uuidArray 注意：按顺序 两个两个放，  ServiceUUID1, CharaterUUID1, ServiceUUID2, CharaterUUID2 ...
 @param on 是否开启
 */
#pragma mark 使能读
-(void)enableRead:(CBPeripheral*)p ServiceUUID:(NSArray *)uuidArray on:(BOOL)on;


/*
 断开蓝牙连接
 disconnect bluetooth
 ブルートゥースを切断する
 */
-(void)disconnectWithBt;

/**
 开启使能通道
 Enable channel
 チャネルを有効にする
 
 @param serviceUUID serviceUUID
 @param characteristicUUID characteristicUUID
 @param p 连接对象
 @param on 是否开启
 */
-(void)notificationWithUUID:(NSString *)serviceUUID characteristicUUID:(NSString *)characteristicUUID p:(CBPeripheral *)p on:(BOOL)on;


-(void)writeValueWithUUID:(NSString *)serviceUUID characteristicUUID:(NSString *)characteristicUUID p:(CBPeripheral *)p data:(NSData *)data;

@end
