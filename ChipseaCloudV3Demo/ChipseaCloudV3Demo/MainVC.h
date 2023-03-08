//
//  MainVC.h
//  ChipseaCloudV3Demo
//
//  Created by iChipsea on 2023/1/14.
//

#import <UIKit/UIKit.h>

#import "ChipseaCloudV3SDK.h"

NS_ASSUME_NONNULL_BEGIN

@interface MainVC : UIViewController

@property (weak, nonatomic) IBOutlet UILabel *kMacAddress;
@property (weak, nonatomic) IBOutlet UILabel *kScaleType;
@property (weak, nonatomic) IBOutlet UILabel *kProductID;
@property (weak, nonatomic) IBOutlet UILabel *kOther;

@end

NS_ASSUME_NONNULL_END
