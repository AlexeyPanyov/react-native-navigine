#import <React/RCTComponent.h>
#import <React/UIView+React.h>

#import <Navigine/Navigine.h>

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

#import "NavigineCircleMapObjectView.h"

#define ANDROID_COLOR(c) [UIColor colorWithRed:((c>>16)&0xFF)/255.0 green:((c>>8)&0xFF)/255.0 blue:((c)&0xFF)/255.0  alpha:((c>>24)&0xFF)/255.0]

#define UIColorFromRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]

@implementation NavigineCircleMapObjectView {
  NCLocationPoint* _center;
  NCCircleMapObject* mapObject;
  float _radius;
  NSString* style;
  BOOL visible;
  BOOL interactive;
  UIColor* circleColor;
}

- (instancetype)init {
  self = [super init];
  visible = [[NSNumber alloc] initWithInt:1];
  circleColor = UIColor.blackColor;
  _radius = 5;
  return self;
}

- (void)updateMarker {
  if (mapObject != nil) {
    [mapObject setPosition:_center];
    [mapObject setVisible:visible];
    [mapObject setInteractive:interactive];
    [mapObject setRadius:_radius];
    if (style) {
      [mapObject setStyle:style];
    }

    const CGFloat* components = CGColorGetComponents(circleColor.CGColor);
    float red = components[0];
    float green = components[1];
    float blue = components[2];
    float alpha = CGColorGetAlpha(circleColor.CGColor);
    [mapObject setColor:red green:green blue:blue alpha:alpha];
  }
}

- (void)setVisible:(NSNumber *) _visible {
  visible = _visible.boolValue;
  [self updateMarker];
}

- (void)setInteractive:(NSNumber *) _interactive {
  interactive = _interactive.boolValue;
  [self updateMarker];
}

- (void)setCenter:(NCLocationPoint *)center {
  _center = center;
  [self updateMarker];
}

- (void)setRadius:(float) radius {
  _radius = radius;
  [self updateMarker];
}

- (void) setStyling:(NSString *) _style {
  style = _style;
  [self updateMarker];
}

- (void) setMapObject:(NCCircleMapObject *)_mapObject {
  mapObject = _mapObject;
  [self updateMarker];
}

- (void) setCircleColor:(UIColor*) color {
  circleColor = color;
  [self updateMarker];
}

- (NCLocationPoint *)getCenter {
  return _center;
}

- (NCCircleMapObject*)getMapObject {
  return mapObject;
}

@synthesize reactTag;

@end
