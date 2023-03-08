//
//  BleDefines.h
//
//  Created by Tony You on 10/31/11.
//  Copyright (c) 2011 Jess Tech. All rights reserved.
//
#ifndef BleDefines_h
#define BleDefines_h


//ISSC
#define ISSC_SERVICE_UUID                                   @"FFF0"
#define ISSC_CHAR_RX_UUID                                   @"FFF1"
#define ISSC_CHAR_TX_UUID                                   @"FFF2"

//HR
#define HR_SERVICE_UUID                                     0x180D
#define HR_HEART_RATE_MEASUREMENT                           0x2A37  //notify
#define HR_BODY_SENSOR_LOCATION                             0x2A38  //read
#define HR_HEARE_RATE_CONTROL_POINT                         0x2A39  //write


//芯海蓝牙秤云端版通讯协议 v0.2
//非锁定数据  上行Service_uuid
#define CS_Cloud_UnClock_Service_UUID                       @"FFF0"
//非锁定数据  charateristic UUID: notify
#define CS_Cloud_UnClock_Charater_UUID_Notify               @"FFF1"
//非锁定数据  charateristic UUID: write
#define CS_Cloud_UnClock_Charater_UUID_Write                @"FFF2"
//锁定数据    上行Service_uuid
#define CS_Cloud_Clock_Service_UUID                         @"181B"
//锁定数据   charateristic UUID read,indicate
#define CS_Cloud_Clock_Charater_UUID_Weight                 @"2A9C"
//历史数据  charateristic UUID
#define CS_Cloud_Clock_Charater_UUID_History                @"FA9C"
//下传Service_uuid ，下传时间
#define CS_Cloud_Service_UUID_Time                          @"1805"
//下传时间 charateristic UUID
#define CS_Cloud_Charater_UUID_Time                         @"2A08"


//下传 Service_uuid , 下传app单位
#define CS_Cloud_Service_UUID_App_Unit                      @"FFF0"
//下传 charateristic UUID ，下传App单位
#define CS_Cloud_Charater_UUID_App_Unit                     @"FFF2"

//芯海蓝牙秤云端版通讯协议 V3  (和V0.2版一样，并增加部分协议)
//OTA在线固件升级
#define CS_Cloud_Service_UUID_OTA                           @"FAA0"
#define CS_Cloud_Charater_UUID_OTA_Notify                   @"FAA1"
#define CS_Cloud_Charater_UUID_OTA_Write                    @"FAA2"




#endif












