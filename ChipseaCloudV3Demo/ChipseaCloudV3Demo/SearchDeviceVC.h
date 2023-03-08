//
//  SearchDeviceVC.h
//  ChipseaCloudV3Demo
//
//  Created by iChipsea on 2023/1/14.
//

#import <UIKit/UIKit.h>
#import "ChipseaCloudV3SDK.h"

NS_ASSUME_NONNULL_BEGIN

@interface SearchDeviceVC : UIViewController

@property (weak, nonatomic) IBOutlet UITableView *kTableView;

@property (nonatomic, copy) void(^onClick_SelectAction)(BtData * selectbtData,CBPeripheral * peripheral);

@property (nonatomic, assign) BtDeviceType deviceType;

@end

NS_ASSUME_NONNULL_END
