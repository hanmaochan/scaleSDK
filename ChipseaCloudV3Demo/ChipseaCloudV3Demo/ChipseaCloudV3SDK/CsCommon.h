//
//  CsCommon.h
//  CsBtUtil
//
//  Created by ChipSea on 15/12/25.
//  Copyright © 2015年 chipsea. All rights reserved.
//

#import <Foundation/Foundation.h>


///==========================宏定义==========================
#define     TAG         @"CsBtUil"
#define     BT_NAME     @"Chipsea-BLE"
#define     JD_BT_NAME  @"JD-BLE"

typedef enum { //秤协议支持的单位，其中的数值与App和服务器的单位数值保持相同
    CsDeviceWeightUnitKg    = 1400,
    CsDeviceWeightUnitJin   = 1402,
    CsDeviceWeightUnitLb    = 1401,
    CsDeviceWeightUnitStLb  = 1403
} CsDeviceWeightUnit;

///==========================设备标示============================
typedef NS_ENUM(NSInteger, CSDEVICE)
{
    CSDEVICE_WEIGHER = 0x00,        //广播体重秤：只发广播，不连接蓝牙，只有体重信息
    CSDEVICE_FATSCALE = 0x01,       //透传体脂秤：要连接蓝牙，有体重、电阻或体脂率等其它信息
    CSDEVICE_FAT_BROADCAST = 0x02,   //广播体脂秤：只发广播，不连接蓝牙，有体重、电阻信息
    CSDEVICE_FAT_BROADCAST_CA20 = 0x03,   //CA20开头的广播体脂秤：只发广播，不连接蓝牙，有体重、电阻信息
    CSDEVICE_FAT_BROADCAST_P23 = 0x04   //P23的广播体脂秤：只发广播，不连接蓝牙，有体重、电阻信息
};




@interface CsCommon : NSObject

/**
 *  根据单位和小数位数获得称量属性
 *  称量属性说明:
 *  Bit       7      6      5      4      3      2      1      0
 *          保留    保留   保留    单位    单位  小数位  小数位   是否锁定
 *
 *  @param unit      单位
 *  @param precision 小数位数
 *
 *  @return 称量属性
 */
+(Byte)getScalePropertyByUnit:(CsDeviceWeightUnit)unit precision:(int)precision;

/**
 *  根据称量属性获得单位
 *
 *  @param scaleProperty 称量属性
 *  称量属性说明:
 *  Bit       7      6      5      4      3      2      1      0
 *          保留    保留   保留    单位    单位  小数位  小数位   是否锁定
 *
 *  @return 单位
 */
+(CsDeviceWeightUnit)getUnitFromScaleProperty:(Byte)scaleProperty;

/**
 *  根据称量属性获得小数位数
 *
 *  @param scaleProperty 称量属性
 *  称量属性说明:
 *  Bit       7      6      5      4      3      2      1      0
 *          保留    保留   保留    单位    单位  小数位  小数位   是否锁定
 *
 *  @return 小数位数
 */
+(int)getValuePrecisionFromScaleProperty:(Byte)scaleProperty;

/**
 * 传入两个data,转换成字符串，交换拼接，再转换成 int
  （注：此方法作废，请调用下面的 changeByteToLongByData  或者 changeByteToLongByByte 方法）
 
 @param data1 低位数 (在后面)
 @param data2 高位数 (在前面)
 @return 转换后得到的数字
 */
+(int)genNumFromData1:(NSData *)data1 Data2:(NSData *)data2;

/**
 * 传入两个data,转换成字符串 ST:LB
 * @param data1 ST
 * @param data2 LB
 * @return return @[@(weight),weightStr];  体重kg , 体重st:lb
 **/
+(NSArray *)genNumFromDataST_LB:(NSData *)data1 Data2:(NSData *)data2;


+(NSData *)getBooleanArray:(Byte)b;


/**
 从NSData中，取数转换成 Long型
 
 @param data NSData
 @param index 从第几位开始
 @param length 长度
 @param isAsc 是否为顺序（YES:顺序，高位在前，低位在后；   NO：反序，低们在前，高位在后）
 @return 返回数
 */
+(long)changeByteToLongByData:(NSData *)data from:(int)index length:(int)length isAsc:(BOOL)isAsc;

/**
 从Byte中，取数转换成 Long型
 
 @param dataByte Byte**
 @param index 从第几位开始
 @param length 长度
 @param isAsc 是否为顺序（YES:顺序，高位在前，低位在后；   NO：反序，低们在前，高位在后）
 @return 返回数
 */
+(long)changeByteToLongByByte:(Byte **)dataByte from:(int)index length:(int)length isAsc:(BOOL)isAsc;

#pragma mark - long long转NSData
+ (NSData *)longToNSData:(long)data;


/**
 * 十六进制数据转化为数组
 *
 * @param data 十六进制数据
 * @return 转化后的数组
 */
+ (NSMutableArray *)convertDataToHexStr:(NSData *)data;

/**
 * 将十六进制数 转化十进制数
 * @return 返回的数据NSString
 */
+ (NSString *)handStrtoulStr:(NSString *)str;

//将16进制转化为十进制 返回的数为NSInteger
+ (NSInteger)getDecimalByHex:(NSString *)hex;

+ (NSData *)getCheckSum:(NSString *)byteStr;

/// data 转字符串
/// @param dataValue data
+ (NSString *)transformStringFromData:(NSData *)dataValue;



/**
 智能水杯：温度转换
 @return 温度
 */
+(float)getCupTemperatureByByte0:(Byte)sw0 Byte1:(Byte)sw1;

/**
 智能水杯：水量转换
 @return 水量
 */
+(float)getCupWaterByByte0:(Byte)sl0 Byte1:(Byte)sl1;

/**
 * 智能水杯：温度转 Data
 */
+(NSData *)getCupWaterDataByTemp:(float)temp;


/**
 检查mac是否合法
 
 @param mac mac地址
 @return 合法返回YES, 不合法NO
 */
+ (BOOL)checkMacIsLegal:(NSString*)mac;



/**
 * 将十进制转化为二进制,设置返回NSString 长度
 **/
+ (NSString *)decimalTOBinary:(uint16_t)tmpid backLength:(int)length;

/**
 * 将十进制转化为十六进制
 **/
+(NSString *)ToHex:(uint16_t)tmpid;

/**
 * 将16进制转化为二进制
 **/
+(NSString *)getBinaryByhex:(NSString *)hex;

@end
