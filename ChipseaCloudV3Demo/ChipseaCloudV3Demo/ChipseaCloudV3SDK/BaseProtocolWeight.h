//
//  BaseProtocolWeight.h
//  CsBtUtil
//
//  Created by iChipsea on 2018/11/14.
//  Copyright © 2018 chipsea. All rights reserved.
//

#import "BaseProtocol.h"
#import "BtData_Weight.h"


@interface BaseProtocolWeight : BaseProtocol

@property (assign, nonatomic) ScaleProtocolType protocol_type;  // 协议类型



//// 是否为1970年时间判断
-(BOOL)isTimeError1970:(NSDate *)date;


/// 体重是否在0 到300kg中间 是否不合法
-(BOOL)isWeightNotLegal:(float)weight;

@end

