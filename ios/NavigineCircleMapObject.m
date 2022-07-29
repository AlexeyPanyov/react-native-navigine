#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>
#import <math.h>
#import "NavigineCircleMapObject.h"
#import "RNNavigineModule.h"

#import "View/NavigineCircleMapObjectView.h"
#import "View/RNNCLocationView.h"

#import "Converter/RCTConvert+RNNavigineView.m"

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

@implementation NavigineCircleMapObject

RCT_EXPORT_MODULE()

- (instancetype)init {
    self = [super init];
    return self;
}
+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (UIView *_Nullable)view {
    return [[NavigineCircleMapObjectView alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY (center, NCLocationPoint, NavigineCircleMapObjectView) {
    if (json != nil) {
         [view setCenter: [RCTConvert NCLocationPoint:json]];
    }
}

RCT_CUSTOM_VIEW_PROPERTY(visible, NSNumber, NavigineCircleMapObjectView) {
    [view setVisible:json];
}

RCT_CUSTOM_VIEW_PROPERTY(interactive, NSNumber, NavigineCircleMapObjectView) {
    [view setInteractive:json];
}

RCT_CUSTOM_VIEW_PROPERTY(radius, NSNumber, NavigineCircleMapObjectView) {
    [view setRadius:[json floatValue]];
}

RCT_CUSTOM_VIEW_PROPERTY(styling, NSDictionary, NavigineCircleMapObjectView) {
    [view setStyling: json];
}

RCT_CUSTOM_VIEW_PROPERTY(circleColor, NSNumber, NavigineCircleMapObjectView) {
    [view setCircleColor: [RCTConvert UIColor:json]];
}

@end
