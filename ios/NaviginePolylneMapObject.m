#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>
#import <math.h>
#import "NaviginePolylineMapObject.h"
#import "RNNavigineModule.h"

#import "View/NaviginePolylineMapObjectView.h"
#import "View/RNNCLocationView.h"

#import "Converter/RCTConvert+RNNavigineView.m"

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

@implementation NaviginePolylineMapObject

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
    return [[NaviginePolylineMapObjectView alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY (polyline, NCLocationPolyline, NaviginePolylineMapObjectView) {
    if (json != nil) {
         [view setPolyline: [RCTConvert NCLocationPolyline:json]];
    }
}

RCT_CUSTOM_VIEW_PROPERTY(visible, NSNumber, NaviginePolylineMapObjectView) {
    [view setVisible:json];
}

RCT_CUSTOM_VIEW_PROPERTY(interactive, NSNumber, NaviginePolylineMapObjectView) {
    [view setInteractive:json];
}

RCT_CUSTOM_VIEW_PROPERTY(lineWidth, NSNumber, NaviginePolylineMapObjectView) {
    [view setLineWidth:[json floatValue]];
}

RCT_CUSTOM_VIEW_PROPERTY(styling, NSDictionary, NaviginePolylineMapObjectView) {
    [view setStyling: json];
}

RCT_CUSTOM_VIEW_PROPERTY(lineColor, NSNumber, NaviginePolylineMapObjectView) {
    [view setLineColor: [RCTConvert UIColor:json]];
}

@end
