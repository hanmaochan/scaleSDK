1、引入SDK相关文件。

2、在主要界面引入ChipseaCloudV3SDK.h
#import "ChipseaCloudV3SDK.h"

3、初始化蓝牙库和算法库
ChipseaBtUtil * btUtil  =[ChipseaBtUtil getInstance];
btUtil.delegate = self;
   btUtil.stopAdvertisementState = NO;
    
ChipseaScaleDetail * scaleDetail = [ChipseaScaleDetail getInstance];

4、实现相关代理方法，代理方法内具体内容可根据实际逻辑需要修改
主要要实现的代理方法有：
4.1 广播数据解析后的回调
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device;
在本方法中可实现判断搜索到的广播信号是否是你想要连接的，如果是则调用连接方法。


4.2已经连接，连接成功回调
-(void)connectedPeripheral:(CBPeripheral *)peripheral;


4.3透传数据解析后的回调
-(void)afterConnectBtData:(BtData *)device;
在本方法中实现收到透传数据后的逻辑处理
根据过程数据、实时数据，分别实现相关逻辑。

4.4蓝牙连接断开回调
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error;
当蓝牙断开连接，会回调本方法


4．5发生蓝牙状态变化时候的回调
-(void)didUpdateCsScaleState:(CsScaleState)state;
在本方法中返回的蓝牙状态的变化

4.6返回错误回调
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType;
在本方法中返回错误，包括BundleID 不匹配，Mac地址不匹配。


5、在绑定搜索蓝牙设备时，设置搜索参数
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:nil isBounding:YES];

在连接搜索蓝牙设备时，设置搜索参数
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:@”你指定的Mac地址” isBounding:NO];


6、算法调用相关：身高( cm)  、 体重(kg)  、 性别(男-1 女-0) 、 年龄 、 电阻
	[ChipseaScaleDetail setUserInfo_height:身高 weight:体重 sex:性别 age:年龄 resistance:电阻];
	具体请看Demo.