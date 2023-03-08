//
//  BleDeviceProtocol.h
//  CsBtUtil
//
//  Created by ChipSea on 18/11/13.
//  Copyright © 2018年 chipsea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreBluetooth/CoreBluetooth.h>
#import "BtData.h"
#import "ChipseaBtUtil.h"


//@class CsBtUtil;


///==========================数据协议代理==========================
@protocol BleDeviceProtocol <NSObject>


/**
 0、根据数据获取 mac地址

 @param data 数据
 @return mac地址
 */
-(NSString *)getDeviceMacAddressByData:(NSData *)data peripheralName:(NSString *)name;

/**
 1、根据广播数据，判断是否符合该协议，判断广播协议

 @param data 广播数据
 @return YES/NO 是否符合
 */
-(BOOL)getJudgeBroadcastProtocolByData:(NSData *)data peripheralName:(NSString *)name serviceUUIDs:(NSArray *)serviceUUIDs deviceProtocolType:(NSUInteger)deviceProtocolType;



/**
 2、根据广播数据，进行解析

 @param data 广播数据
 @return 解析后通用对象
 */
-(BtData *)getParsingBroadcastByData:(NSData *)data csBtutil:(ChipseaBtUtil *) btUtil;

/**
 3、1--找服务--找到对应服务之后,回到函数 是否保存服务列表，默认NO
 ----  CsBtUtil.m 中，供调用-(void)peripheral:(CBPeripheral *)peripheral didDiscoverServices:(NSError *)error
 @return 是否保存服务列表，默认NO
 */
-(BOOL)peripheral_IsSaveServices;
/**
 3、2--找服务--找到对应服务之后,回到函数 是否保存服务列表，默认空
 ----  CsBtUtil.m 中，供调用-(void)peripheral:(CBPeripheral *)peripheral didDiscoverServices:(NSError *)error
 @return 需要处理的ServiceUUID 列表
 */
-(NSMutableArray *)peripheral_discoverChar_ServiceUUIDArray;


/**
 4.0- 连接成功后的操作
 ---- CsBtUtil.m 中，供调用  -(void)peripheral:(CBPeripheral *)peripheral didDiscoverCharacteristicsForService:(CBService *)service error:(NSError *)error
 
 @param peripheral xxx
 */
-(void)connectedPeripheral:(CBPeripheral *)peripheral csBtutil:(ChipseaBtUtil *) btUtil;

/**
 4、--找特征-----找到外设相关的特性回调函数
 ---- CsBtUtil.m 中，供调用  -(void)peripheral:(CBPeripheral *)peripheral didDiscoverCharacteristicsForService:(CBService *)service error:(NSError *)error

 @param peripheral xxx
 @param service xxx
 */
-(CBCharacteristic *)peripheral:(CBPeripheral *)peripheral didDiscoverCharacteristicsForService:(CBService *)service;



/**
 5、透传数据过来，要处理！！

 @param data 透传数据
 @param UUIDStr UUID
 @return 解析后的数据
 */
-(BtData *)getParsingPassthroughByData:(NSData *)data UUIDStr:(NSString *)UUIDStr csBtutil:(ChipseaBtUtil *) btUtil;



/**
 6、在写数据到底层蓝牙设备时，要ServiceUUID 和 TxUUID
 注意：按顺序 两个两个放，  ServiceUUID, TxUUID,
 
 @param data 要下发的数据
 @return 列表
 */
-(NSMutableArray *)getWriteValue_ServiceUUID_TxUUID_ByData:(NSData *)data;


/**
 7、使能读历史数据，这样会在通道中读取到函数  ServiceUUID 和 CharaterUUID
 通知秤上传历史数据
 注意：按顺序 两个两个放，  ServiceUUID1, CharaterUUID1, ServiceUUID2, CharaterUUID2 ...

 @return 列表
 */
-(NSMutableArray *)getEnable_Read_UpLodadHistoryData_ServiceUUID_CharaterUUID;



/**
 8、使能读实时数据，这样会在通道中读取到函数  ServiceUUID 和 CharaterUUID
 通知秤上传实时数据
 注意：按顺序 两个两个放，  ServiceUUID1, CharaterUUID1, ServiceUUID2, CharaterUUID2 ...
 
 @return 列表
 */
-(NSMutableArray *)getEnableRead_Data_ServiceUUID_CharaterUUID;


/**
 *9、界面统一调用，下发数据发送操作
 *@param optionIndex 操作序号
 *@param dataDic 操作数据
 */
-(void)sendOption:(int)optionIndex dataDic:(NSDictionary *)dataDic csBtutil:(ChipseaBtUtil *) btUtil;


@end
