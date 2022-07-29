#pragma once

#import <React/RCTComponent.h>
#import <Navigine/NCAnimationType.h>

@class RCTBridge;
@class NCLocationPoint;
@class NCIconMapObject;

@interface NavigineIconMapObjectView: UIView<RCTComponent>

// props
- (void) setSource:(NSString *) _source;
- (void) setPosition:(NCLocationPoint *) _points;
- (void) setVisible:(NSNumber*) _visible;
- (void) setSize:(float) width
          height:(float) height;
- (void) setStyling:(NSString *) style;
- (void) setInteractive:(NSNumber *) interactive;

-(NCLocationPoint*) getPosition;
-(NCIconMapObject*) getMapObject;
-(void) setMapObject:(NCIconMapObject*) mapObject;

- (void) setPositionAnimated: (NCLocationPoint*) point duration:(NSNumber *) duration type:(NCAnimationType) type;

@end
