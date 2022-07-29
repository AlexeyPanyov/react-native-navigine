#import <React/RCTComponent.h>
#import <React/UIView+React.h>

#import <Navigine/Navigine.h>

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

#import "NaviginePolylineMapObjectView.h"

#define ANDROID_COLOR(c) [UIColor colorWithRed:((c>>16)&0xFF)/255.0 green:((c>>8)&0xFF)/255.0 blue:((c)&0xFF)/255.0  alpha:((c>>24)&0xFF)/255.0]

#define UIColorFromRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]

@implementation NaviginePolylineMapObjectView {
  NCLocationPolyline* _polyline;
  NCPolylineMapObject* mapObject;
  float _width;
  NSString* style;
  BOOL visible;
  BOOL interactive;
  UIColor* lineColor;
}

- (instancetype)init {
  self = [super init];
  visible = [[NSNumber alloc] initWithInt:1];
  lineColor = UIColor.blackColor;
  _width = 5;
  return self;
}

- (void)updateMarker {
  if (mapObject != nil) {
    [mapObject setPolyLine:_polyline];
    [mapObject setVisible:visible];
    [mapObject setInteractive:interactive];
    [mapObject setWidth:_width];
    if (style) {
      [mapObject setStyle:style];
    }

    const CGFloat* components = CGColorGetComponents(lineColor.CGColor);
    float red = components[0];
    float green = components[1];
    float blue = components[2];
    float alpha = CGColorGetAlpha(lineColor.CGColor);
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

- (void)setPolyline:(NCLocationPolyline *)polyline {
  _polyline = polyline;
  [self updateMarker];
}

- (void)setLineWidth:(float) width {
  _width = width;
  [self updateMarker];
}

- (void) setStyling:(NSString *) _style {
  style = _style;
  [self updateMarker];
}

- (void) setMapObject:(NCPolylineMapObject *)_mapObject {
  mapObject = _mapObject;
  [self updateMarker];
}

- (void) setLineColor:(UIColor*) color {
  lineColor = color;
  [self updateMarker];
}

- (NCLocationPolyline *)getPosition {
  return _polyline;
}

- (NCPolylineMapObject*)getMapObject {
  return mapObject;
}

@synthesize reactTag;

@end
