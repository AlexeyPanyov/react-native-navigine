#pragma once

#import <React/RCTComponent.h>
#import <Navigine/NCAnimationType.h>

@class RCTBridge;
@class NCLocationPolyline;
@class NCPolylineMapObject;

@interface NaviginePolylineMapObjectView: UIView<RCTComponent>

// props
- (void) setPolyline:(NCLocationPolyline *) _polyline;
- (void) setVisible:(NSNumber*) _visible;
- (void) setLineWidth:(float) width;
- (void) setStyling:(NSString *) style;
- (void) setInteractive:(NSNumber *) interactive;
- (void) setLineColor:(UIColor*) color;

-(NCLocationPolyline*) getPosition;
-(NCPolylineMapObject*) getMapObject;
-(void) setMapObject:(NCPolylineMapObject*) mapObject;

@end
