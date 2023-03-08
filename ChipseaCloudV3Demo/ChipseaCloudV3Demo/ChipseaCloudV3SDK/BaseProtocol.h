//
//  BaseProtocol.h
//  CsBtUtil
//
//  Created by iChipsea on 2018/11/13.
//  Copyright © 2018 chipsea. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BleDeviceProtocol.h"

@interface BaseProtocol : NSObject <BleDeviceProtocol>

@property (nonatomic, retain) CBPeripheral * peripheral;
@property (nonatomic, retain) NSString * macAddress;

@end

