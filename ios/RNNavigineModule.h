#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import "NavigineLocationView.h"

@interface NavigineModule : NSObject <RCTBridgeModule>

@property (nonatomic, strong) NavigineLocationView *view;

@end
