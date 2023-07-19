## Overview
This document provides detailed specifications for the [ChipseaCloudV3SDK iOS]. This SDK provides the [Function Introduction] function, supporting [Operating System and Language Environment], etc.

## System requirements
Development tool: Xcode
Integration method: manually importing SDK
SDK version support: SDK supports Xcode 9.1.0, iOS8.0+and above versions

## Usage Guidelines
1. Import SDK related files.
After downloading the SDK, extract the sample code
Add the files of ChipseaCloudV3SDK to the project
2. Introducing ChipseaCloudV3SDK. h in the main interface

```
#import "ChipseaCloudV3SDK.h"
```
3. Initialize Bluetooth library and algorithm library


```
ChipseaBtUtil * btUtil  =[ChipseaBtUtil getInstance];
btUtil.delegate = self;
btUtil.stopAdvertisementState = NO;
ChipseaScaleDetail * scaleDetail = [ChipseaScaleDetail getInstance];
```

4. Implement relevant proxy methods, and the specific content within the proxy method can be modified according to actual logical needs
The main proxy methods to be implemented are:
4.1 Callback after parsing broadcast data


```
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device;
```

In this method, it is possible to determine whether the searched broadcast signal is the one you want to connect to, and if so, call the connection method.
4.2 Connected, successful connection callback

```
-(void)connectedPeripheral:(CBPeripheral *)peripheral;
```
4.3 Callback after parsing transparent data

```
-(void)afterConnectBtData:(BtData *)device;
```
Implement logical processing after receiving transparent data in this method
Implement relevant logic based on process data and real-time data.


4.4 Bluetooth connection disconnection callback

```
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error;
```
When Bluetooth is disconnected, this method will be called back
4.5 Callback when Bluetooth status changes

```
-(void)didUpdateCsScaleState:(CsScaleState)state;
```
Changes in Bluetooth status returned in this method


4.6 Return Error Callback

```
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType;
```
Errors were returned in this method, including BundleID mismatch and Mac address mismatch.


5. Set search parameters when binding to search for Bluetooth devices


```
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:nil isBounding:YES];
```
Set search parameters when connecting to search for Bluetooth devices

```
//Mac address specified by Mac
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:mac isBounding:NO];

/**
Connected, successfully connected
Already connected, the connection is successful
Successful acquisition of suitable economic resources
*/
-(void)connectedPeripheral:(CBPeripheral *)peripheral
{
    NSLog (@ "Connected to appropriate economy, successfully connected to appropriate");
}
```
6. Algorithm call related: height (cm), weight (kg), gender (male female 0), age, resistance

```
[ChipseaScaleDetail setUserInfo_height: Height weight: Weight sex: Gender age: Age resistance: Resistance];

```
```
/**
 透传数据解析后的回调
 The callback after the transparent data is parsed
 透過データが解析された後のコールバック
 
 @param device 数据
 */
-(void)afterConnectBtData:(BtData *)device
{
    BtData_Weight * weData = (BtData_Weight *)device;
    
    NSMutableString * typeString = [[NSMutableString alloc] init];
    [typeString appendFormat:@"weight ：%.2f\n",weData.weight];
    [typeString appendFormat:@"time ：%@\n",weData.weightDate];
    [typeString appendFormat:@"ロックデータ：%@\n",weData.locked?@"YES":@"NO"];
    
    if(weData.locked)
    {
        // locked
        if(weData.r1 > 0)
        {
            float height = 174.0f;
            Byte sex = 1;
            int age = 38;
            [_scaleDetail setUserInfo_height:height weight:weData.weight sex:sex age:age resistance:weData.r1];

            [typeString appendFormat:@"脂肪率を取得:%0.1f \n", [_scaleDetail getBFR]];
            [typeString appendFormat:@"骨格筋を得る(kg):%0.1f \n", [_scaleDetail getSMM]];
            [typeString appendFormat:@"筋肉量を増やす（水で）:%0.1f \n", [_scaleDetail getSLM]];
            [typeString appendFormat:@"骨(無機塩)を入手する:%0.1f \n", [_scaleDetail getMSW]];
            [typeString appendFormat:@"内臓脂肪レベルを取得する:%d \n",(int)( [_scaleDetail getVFR] + 0.5f)];
            [typeString appendFormat:@"基礎代謝を得る:%d \n",(int)( [_scaleDetail getBMR] + 0.5f)];
            [typeString appendFormat:@"身体年齢:%d \n",(int)( [_scaleDetail getBodyAge] + 0.5f)];
            [typeString appendFormat:@"水分率:%0.1f \n", [_scaleDetail getTFR]];
            [typeString appendFormat:@"スコア:%d \n",(int)( [_scaleDetail getTotalScore] + 0.5f)];
            
            [typeString appendString:@"=============================\n"];
            
            [typeString appendFormat:@"太る:%f \n", [_scaleDetail getFM]];
            [typeString appendFormat:@"水の総重量を取得する:%f \n", [_scaleDetail getTF]];
            [typeString appendFormat:@"タンパク質を得る:%f \n", [_scaleDetail getPM]];
            [typeString appendFormat:@" 筋肉率を得る:%f \n", [_scaleDetail getSLMPrecent]];
            [typeString appendFormat:@"除脂肪体重:%f \n", [_scaleDetail getLBM]];
            [typeString appendFormat:@"標準体重:%f \n", [_scaleDetail getBW]];
            [typeString appendFormat:@"体重管理:%f \n", [_scaleDetail getWC]];
            [typeString appendFormat:@"脂肪をコントロールする:%f \n", [_scaleDetail getFC]];
            [typeString appendFormat:@"筋肉のコントロールを得る:%f \n", [_scaleDetail getMC]];
        }
        
        
    }else{
        // unlocked
    }
    
    _kOther.text = typeString;
    
}
```
Please refer to Demo for details

## How to update
1.unzip *zip

2.replace the old libChipseaCloudV3SDK.a with new libChipseaCloudV3SDK.a


## version history
| Version |                         Description                          |
| :-----: | :----------------------------------------------------------: |
|  1.0.4  | Added new Device Protocol |
| 1.0.3 | add model、version、manufacturer information|

