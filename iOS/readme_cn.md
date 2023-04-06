
## 概述
本文档提供了 [ChipseaCloudV3SDK iOS] 的详细规格说明。该 SDK 提供了 [功能简介] 功能，支持 [操作系统和语言环境] 等。


## 系统要求
开发工具：Xcode
集成方式：手动导入SDK
SDK版本支持：SDK支持Xcode 9.1.0, iOS8.0+及以上版本

## 使用指南

1、引入SDK相关文件。
下载sdk后，解压得到示例代码
将ChipseaCloudV3SDK的文件添加到到工程中即可


2、在主要界面引入ChipseaCloudV3SDK.h

```
#import "ChipseaCloudV3SDK.h"
```
3、初始化蓝牙库和算法库

```
ChipseaBtUtil * btUtil  =[ChipseaBtUtil getInstance];
btUtil.delegate = self;
   btUtil.stopAdvertisementState = NO;
    
ChipseaScaleDetail * scaleDetail = [ChipseaScaleDetail getInstance];
```

4、实现相关代理方法，代理方法内具体内容可根据实际逻辑需要修改
主要要实现的代理方法有：
4.1 广播数据解析后的回调

```
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device;
```

在本方法中可实现判断搜索到的广播信号是否是你想要连接的，如果是则调用连接方法。


4.2已经连接，连接成功回调

```
-(void)connectedPeripheral:(CBPeripheral *)peripheral;
```

4.3透传数据解析后的回调

```
-(void)afterConnectBtData:(BtData *)device;

```
在本方法中实现收到透传数据后的逻辑处理
根据过程数据、实时数据，分别实现相关逻辑。

4.4蓝牙连接断开回调

```
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error;
```
当蓝牙断开连接，会回调本方法


4.5发生蓝牙状态变化时候的回调

```
-(void)didUpdateCsScaleState:(CsScaleState)state;
```
在本方法中返回的蓝牙状态的变化

4.6返回错误回调

```
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType;
```
在本方法中返回错误，包括BundleID 不匹配，Mac地址不匹配。


5、在绑定搜索蓝牙设备时，设置搜索参数

```
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:nil isBounding:YES];
```
在连接搜索蓝牙设备时，设置搜索参数

```
// mac  指定的Mac地址
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:mac isBounding:NO];

/**
 已经连接，连接成功
 Already connected, the connection is successful
 接続済み、接続成功
 */
-(void)connectedPeripheral:(CBPeripheral *)peripheral
{
    NSLog(@"接続済み、接続成功");
}


```


6、算法调用相关：身高( cm)  、 体重(kg)  、 性别(男-1 女-0) 、 年龄 、 电阻

```
[ChipseaScaleDetail setUserInfo_height:身高 weight:体重 sex:性别 age:年龄 resistance:电阻];
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

具体可看Demo.




## version history

| Version |                         Description                          |
| :-----: | :----------------------------------------------------------: |
| 1.0.3 | add model、version、manufacturer information|


