//
//  AppDelegate.m
//  ChipseaCloudV3Demo
//
//  Created by iChipsea on 2023/1/13.
//

#import "AppDelegate.h"
#import "MainVC.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    MainVC * vc = [[MainVC alloc] init];
    UINavigationController * na = [[UINavigationController alloc] initWithRootViewController:vc];
    self.window.rootViewController = na;
    return YES;
}



@end
