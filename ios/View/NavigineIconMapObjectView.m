#import <React/RCTComponent.h>
#import <React/UIView+React.h>

#import <Navigine/Navigine.h>

#ifndef MAX
#import <NSObjCRuntime.h>
#endif

#import "NavigineIconMapObjectView.h"

#define ANDROID_COLOR(c) [UIColor colorWithRed:((c>>16)&0xFF)/255.0 green:((c>>8)&0xFF)/255.0 blue:((c)&0xFF)/255.0  alpha:((c>>24)&0xFF)/255.0]

#define UIColorFromRGB(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]

@implementation NavigineIconMapObjectView {
  NCLocationPoint* _point;
  NCIconMapObject* mapObject;
  float _width;
  float _height;
  NSString* source;
  NSString* lastSource;
  NSString* style;
  BOOL visible;
  BOOL interactive;
  NSMutableArray<UIView*>* _reactSubviews;
  UIView* _childView;
  NSNumber * _duration;
  NCAnimationType _type;
}

- (instancetype)init {
  self = [super init];
  visible = [[NSNumber alloc] initWithInt:1];
  _width = 0;
  _height = 0;
  _reactSubviews = [[NSMutableArray alloc] init];
  source = @"";
  lastSource = @"";
  return self;
}

- (void)updateMarker {
  if (mapObject != nil) {
    if (_duration == NULL) {
      [mapObject setPosition:_point];
    } else {
      [mapObject setPositionAnimated:_point duration:_duration.doubleValue type:_type];
    }
    [mapObject setVisible:visible];
    [mapObject setInteractive:interactive];
    [mapObject setSize:_width height:_height];
    if (style) {
      [mapObject setStyle:style];
    }
    if ([_reactSubviews count] == 0) {
      if (![source isEqual:@""]) {
        if (![source isEqual:lastSource]) {
          [mapObject setBitmap:[self resolveUIImage:source]];
          lastSource = source;
        }
      }
    }
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

- (void)setPosition:(NCLocationPoint *)point {
  _point = point;
  _duration = NULL;
  _type = NCAnimationTypeNone;
  [self updateMarker];
}

- (void)setSize:(float) width
         height:(float) height {
  _width = width;
  _height = height;
  [self updateMarker];
}

- (void) setStyling:(NSString *) _style {
  style = _style;
  [self updateMarker];
}

- (UIImage*)resolveUIImage:(NSString*) uri {
  UIImage *icon;
  if ([uri rangeOfString:@"http://"].location == NSNotFound && [uri rangeOfString:@"https://"].location == NSNotFound) {
    if ([uri rangeOfString:@"file://"].location != NSNotFound){
      NSString *file = [uri substringFromIndex:8];
      icon = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL fileURLWithPath:file]]];
    } else {
      icon = [UIImage imageNamed:uri];
    }
  } else {
    icon = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:uri]]];
  }
  return icon;
}

- (void) setSource:(NSString*) _source {
  source = _source;
  [self updateMarker];
}

- (void) setMapObject:(NCIconMapObject *)_mapObject {
  mapObject = _mapObject;
  [self updateMarker];
}

- (NCLocationPoint *)getPosition {
  return _point;
}

- (NCIconMapObject*)getMapObject {
  return mapObject;
}

- (void)insertReactSubview:(UIView*) subview atIndex:(NSInteger)atIndex {
  [_reactSubviews insertObject:subview atIndex: atIndex];
  [super insertReactSubview:subview atIndex:atIndex];
}

- (void)removeReactSubview:(UIView*) subview {
  [_reactSubviews removeObject:subview];
  [super removeReactSubview: subview];
}

- (void) setPositionAnimated: (NCLocationPoint*) point duration:(NSNumber *) duration type:(NCAnimationType) type {
  _point = point;
  _duration = duration;
  _type = type;
  [self updateMarker];
}

@synthesize reactTag;

@end
