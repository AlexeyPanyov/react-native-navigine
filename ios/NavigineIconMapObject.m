#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>
#import <math.h>
#import "NavigineIconMapObject.h"
#import "NavigineModule.h"

#import "View/NavigineIconMapObjectView.h"
#import "View/RNNCLocationView.h"

#import "Converter/RCTConvert+RNNavigineView.m"

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

@implementation NavigineIconMapObject

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
    return [[NavigineIconMapObjectView alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY (locationPoint, NCLocationPoint, NavigineIconMapObjectView) {
    if (json != nil) {
        [view setPosition: [RCTConvert NCLocationPoint:json]];
    }
}

RCT_CUSTOM_VIEW_PROPERTY(visible, NSNumber, NavigineIconMapObjectView) {
    [view setVisible:json];
}

RCT_CUSTOM_VIEW_PROPERTY(interactive, NSNumber, NavigineIconMapObjectView) {
    [view setInteractive:json];
}

RCT_CUSTOM_VIEW_PROPERTY(size, NSDictionary, NavigineIconMapObjectView) {
    float width = 20.0;
    float height = 20.0;
    if (json) {
        width = [[json valueForKey:@"width"] floatValue];
        height = [[json valueForKey:@"height"] floatValue];
    }
    [view setSize:width height:height];
}

RCT_CUSTOM_VIEW_PROPERTY(source, NSString, NavigineIconMapObjectView) {
    [view setSource: json];
}

RCT_CUSTOM_VIEW_PROPERTY(styling, NSDictionary, NavigineIconMapObjectView) {
    [view setStyling: json];
}

// ref
RCT_EXPORT_METHOD(setPositionAnimated:(nonnull NSNumber*) reactTag json:(NSDictionary*) json duration: (NSNumber*_Nonnull) duration animation: (NSNumber*_Nonnull) animation) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *,UIView *> *viewRegistry) {
      NavigineIconMapObjectView *view = (NavigineIconMapObjectView*) viewRegistry[reactTag];
        if (!view || ![view isKindOfClass:[NavigineIconMapObjectView class]]) {
            RCTLogError(@"Cannot find NativeView with tag #%@", reactTag);
            return;
        }
        NCLocationPoint* locationPoint = [RCTConvert NCLocationPoint:json];
        [view setPositionAnimated:locationPoint duration:duration type: [animation integerValue]];
    }];
}

@end
