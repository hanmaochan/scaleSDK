//
//  MainVC.m
//  ChipseaCloudV3Demo
//
//  Created by iChipsea on 2023/1/14.
//

#import "MainVC.h"
#import "SearchDeviceVC.h"

@interface MainVC ()<BleDeviceDelegate>
@property (nonatomic, retain) ChipseaBtUtil * btUtil;
@property (nonatomic, assign) BtDeviceType deviceType;
@property (nonatomic, retain) CBPeripheral * curperipheral;
@property (nonatomic, retain) ChipseaScaleDetail * scaleDetail;
@property (nonatomic, retain) NSTimer * upLoadHistoryDataTimer;         // 阿里小智云端版，开启历史数据通道，上传历史数据，一定时间内没有历史数据，再开启实时测量数据通道

@property (nonatomic, retain) UIButton * bindBtn;

@property (nonatomic, retain) NSString * curMacAddress;
@end

@implementation MainVC

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self initAll];
}

-(void)initAll
{
    _btUtil = [ChipseaBtUtil getInstance];
    _btUtil.delegate = self;
    _deviceType = BtDeviceType_Weight;
    _btUtil.stopAdvertisementState = NO;
    
    _scaleDetail = [ChipseaScaleDetail getInstance];
    
    UIButton * editBtn = [[UIButton alloc] init];
    editBtn.bounds = CGRectMake(0, 0, 60, 40);
    editBtn.titleLabel.font = [UIFont systemFontOfSize:14];
    editBtn.contentEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 3);
    [editBtn setTitle:@"Search" forState:UIControlStateNormal];
    [editBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [editBtn addTarget:self action:@selector(onClickBtn_SearchDevice) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *item = [[UIBarButtonItem alloc] initWithCustomView:editBtn];
    self.navigationItem.rightBarButtonItem = item;
    
    
    _bindBtn = [[UIButton alloc] init];
    _bindBtn.bounds = CGRectMake(0, 0, 60, 40);
    _bindBtn.titleLabel.font = [UIFont systemFontOfSize:14];
    _bindBtn.contentEdgeInsets = UIEdgeInsetsMake(0, 0, 0, 3);
    [_bindBtn setTitle:@"Untie" forState:UIControlStateNormal];
    [_bindBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [_bindBtn addTarget:self action:@selector(onClickBtn_UnBind:) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem * binditem = [[UIBarButtonItem alloc] initWithCustomView:_bindBtn];
    self.navigationItem.leftBarButtonItem = binditem;
    
    _curMacAddress = [self getBindMacAddress];
    if(_curMacAddress.length > 5)
    {
        _bindBtn.hidden = NO;
        _kOther.text = @"";
        [self toConnectDevice];
    }else{
        _bindBtn.hidden = YES;
        _kOther.text = @"検索ボタンをクリックしてデバイスを検索してください";
    }
}

/**
 点击搜索设备
 Click to search for devices
 クリックしてデバイスを検索
 */
-(void)onClickBtn_SearchDevice
{
    _curperipheral = nil;
    SearchDeviceVC * vc = [[SearchDeviceVC alloc] init];
    vc.title = @"Search Devices";
    vc.deviceType = _deviceType;
    vc.onClick_SelectAction = ^(BtData * selectbtData, CBPeripheral * peripheral) {
        self->_curMacAddress = selectbtData.mac;
        self->_bindBtn.hidden = NO;
        [self setBindMacAddress:selectbtData.mac];
        
        [self toConnectDevice];

        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            BtData_Weight * weData = (BtData_Weight *)selectbtData;
            self->_kMacAddress.text = [NSString stringWithFormat:@"Mac:%@",weData.mac];
            self->_kProductID.text = [NSString stringWithFormat:@"Product ID:%ld",weData.product_id];

        });
    };
    [self.navigationController pushViewController:vc animated:YES];
}

/**
 点击解绑
 Click to unbind
 クリックしてバインドを解除します
 */
-(void)onClickBtn_UnBind:(UIButton *)btn
{
    [self setBindMacAddress:nil];
    btn.hidden = YES;
    _curMacAddress = nil;
    _kMacAddress.text = @"MacAddress:";
    _kProductID.text = @"Product ID:";
    _kOther.text = @"検索ボタンをクリックしてデバイスを検索してください";
    [_btUtil disconnectWithBt];
}

/**
 去连接设备
 to connect the device
 デバイスを接続するには
 */
-(void)toConnectDevice
{
    _kMacAddress.text = [NSString stringWithFormat:@"Mac:%@",_curMacAddress];
    
    /* 设置蓝牙代理
     Set up bluetooth proxy
     Bluetooth プロキシを設定する
     */
    _btUtil.delegate = self;
    
    /* 设置搜索相关参数
     Set search related parameters
     検索関連のパラメータを設定する
    */
    [_btUtil setCurBtDeviceType:BtDeviceType_Weight macAddress:_curMacAddress isBounding:NO];
    
    /* 开启蓝牙扫描
     Turn on Bluetooth scanning
     Bluetooth スキャンをオンにする
     */
    [_btUtil startScanBluetoothDevice];
   
}

#pragma mark BleDeviceDelegate
/**
 广播数据解析后的回调
 The callback after the broadcast data is parsed
 ブロードキャスト データが解析された後のコールバック
 
 @param peripheral xxx
 @param device 数据
 */
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device
{
    NSLog(@"ブロードキャストデータを受信");
    BtData_Weight * weData = (BtData_Weight *)device;
    _kMacAddress.text = [NSString stringWithFormat:@"Mac:%@",weData.mac];
    _kProductID.text = [NSString stringWithFormat:@"Product ID:%ld",weData.product_id];
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        if (device.connType == DeviceConnType_BlueToothPassthrough)
        {
            self->_curperipheral = peripheral;
            [self->_btUtil connect:peripheral];
        }
    });
}

/**
 已经连接，连接成功
 Already connected, the connection is successful
 接続済み、接続成功
 */
-(void)connectedPeripheral:(CBPeripheral *)peripheral
{
    NSLog(@"接続済み、接続成功");
}

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



/**
 连接断开回调
 Connection disconnection callback
 接続切断コールバック
 */
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error
{
    /* 马上发起重连搜索
     Immediately initiate a reconnection search
     すぐに再接続検索を開始します
    */
    [self startScale];
}

/**
 发生蓝牙状态变化时候的回调
 Callback when bluetooth status changes
 Bluetooth ステータスが変化したときのコールバック
 */
-(void)didUpdateCsScaleState:(CsScaleState)state
{
  
}
/**
 返回错误
 return error
 エラーを返す
 */
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType
{
    switch (errorType) {
        case ChipseaErrorTypeBundleIDIsNotMatch:
        {
            NSLog(@"BundleID Is Not Match");
            break;
        }
        case ChipseaErrorTypeMacAddressIsNotMatch:
        {
            NSLog(@"Mac Address Is Not Match");
            break;
        }
        default:
            break;
    }
}

-(void)startScale
{
    /* 马上发起重连搜索
     Immediately initiate a reconnection search
     すぐに再接続検索を開始します
    */
    if(_curperipheral)
    {
        [_btUtil connect:_curperipheral];
    }else
    {
        [_btUtil startScanBluetoothDevice];
    }
}


/**
 保存Mac 地址，下次可以直接使用mac地址连接
 Save the Mac address, you can directly use the mac address to connect next time
 Macアドレスを保存すると、次回接続するときに直接MACアドレスを使用できます
 */
-(void)setBindMacAddress:(NSString *)macAddress
{
    NSUserDefaults * userDefaults = [NSUserDefaults standardUserDefaults];
    if(macAddress.length > 5)
    {
        [userDefaults setObject:macAddress forKey:@"cur_bind_mac_address"];
    }else{
        [userDefaults removeObjectForKey:@"cur_bind_mac_address"];
    }
    [userDefaults synchronize];
}

/**
 获取上次保存的mac地址
 Get the last saved mac address
 最後に保存された MAC アドレスを取得する
 **/
-(NSString *)getBindMacAddress
{
    NSUserDefaults * userDefaults = [NSUserDefaults standardUserDefaults];
    NSString * macAddress = [userDefaults objectForKey:@"cur_bind_mac_address"];
    return macAddress;
}
@end
