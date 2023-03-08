//
//  SearchDeviceVC.m
//  ChipseaCloudV3Demo
//
//  Created by iChipsea on 2023/1/14.
//

#import "SearchDeviceVC.h"

@interface SearchDeviceVC ()<UITableViewDataSource,UITableViewDelegate,BleDeviceDelegate>

@property (nonatomic, retain) ChipseaBtUtil * btUtil;
@property (nonatomic, retain) NSMutableArray * dataArray;
@property (nonatomic, retain) NSMutableArray * peripheralArray;

@end

@implementation SearchDeviceVC

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initAll];
}

-(void)initAll
{
    _btUtil = [ChipseaBtUtil getInstance];
    _btUtil.delegate = self;
    _btUtil.stopAdvertisementState = NO;
    [_btUtil disconnectWithBt];
    [_btUtil startScanBluetoothDevice];
    
    _kTableView.dataSource = self;
    _kTableView.delegate = self;
    _kTableView.rowHeight = 60;
    
    _dataArray = [[NSMutableArray alloc] init];
    _peripheralArray = [[NSMutableArray alloc] init];
    

    /**
     设置当前要搜索和执行的设备类别
     
     @param type 类别
     @param macAddress mac地址
     @param isBounding 是否绑定状态
     */
    
    [_btUtil setCurBtDeviceType:_deviceType macAddress:nil isBounding:YES];
}

#pragma mark BleDeviceDelegate
/**
 广播数据解析后的回调
 
 @param peripheral xxx
 @param device 数据
 */
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device
{
    for(BtData * data in _dataArray)
    {
        if([data.mac isEqualToString:device.mac])
        {
            return;
        }
    }
    [_dataArray addObject:device];
    [_peripheralArray addObject:peripheral];
    [_kTableView reloadData];
}

/**
 透传数据解析后的回调
 
 @param device 数据
 */
-(void)afterConnectBtData:(BtData *)device
{
    
}

/**
 已经连接的外设
 */
-(void)connectedPeripheral:(CBPeripheral *)peripheral
{
    
}

/**
 连接断开回调
 */
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error
{
    
}



#pragma mark - UITableViewDelegate
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _dataArray.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
    if(cell == nil)
    {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:@"Cell"];
    }
    BtData * data = _dataArray[indexPath.row];
    cell.textLabel.text = data.name;
    cell.detailTextLabel.text = data.mac;
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if(_onClick_SelectAction)
    {
        BtData * data = _dataArray[indexPath.row];
        CBPeripheral * peripheral = _peripheralArray[indexPath.row];
        _onClick_SelectAction(data,peripheral);
    }
    [self.navigationController popViewControllerAnimated:YES];
}

@end
