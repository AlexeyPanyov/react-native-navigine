#import <React/RCTConvert.h>
#import <Foundation/Foundation.h>
#import <Navigine/Navigine.h>

@interface RCTConvert(RNNavigineView)

@end

@implementation RCTConvert (RNNavigineView)

+ (NCLocationPoint *)NCLocationPoint:(id)json {
  json = [self NSDictionary:json];
  NSDictionary* jsonPoint = json[@"point"];
  NCPoint* point = [NCPoint pointWithX:[self double:jsonPoint[@"x"]] y:[self double:jsonPoint[@"y"]]];
  return [NCLocationPoint locationPointWithPoint:point
                                      locationId:[self double:json[@"locationId"]]
                                   sublocationId:[self double:json[@"sublocationId"]]];
}

+ (NCLocationPolyline *)NCLocationPolyline:(id)json {
  json = [self NSDictionary:json];
  NSArray* jsonPolylineArray = json[@"polyline"];
  NSMutableArray* points = [[NSMutableArray alloc] init];
  for (NSDictionary* jsonPoint in jsonPolylineArray) {
    float x = [jsonPoint[@"x"] floatValue];
    float y = [jsonPoint[@"y"] floatValue];
    [points addObject:[NCPoint pointWithX:x y:y]];
  }
  return [NCLocationPolyline locationPolylineWithPolyline:[NCPolyline polylineWithPoints:points]
                                               locationId:[self double:json[@"locationId"]]
                                            sublocationId:[self double:json[@"sublocationId"]]];
}

@end
