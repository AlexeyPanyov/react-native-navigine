#pragma once

#import <React/RCTComponent.h>
#import <Navigine/NCAnimationType.h>

@class RCTBridge;
@class NCLocationPoint;
@class NCCircleMapObject;

@interface NavigineCircleMapObjectView: UIView<RCTComponent>

// props
- (void) setCenter:(NCLocationPoint *) _center;
- (void) setVisible:(NSNumber*) _visible;
- (void) setRadius:(float) radius;
- (void) setStyling:(NSString *) style;
- (void) setInteractive:(NSNumber *) interactive;
- (void) setCircleColor:(UIColor*) color;

-(NCLocationPoint*) getCenter;
-(NCCircleMapObject*) getMapObject;
-(void) setMapObject:(NCCircleMapObject*) mapObject;

@end
