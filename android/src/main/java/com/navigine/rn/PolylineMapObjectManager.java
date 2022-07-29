package com.navigine.rn;

import android.view.View;

import androidx.annotation.NonNull;

import com.navigine.rn.view.NaviginePolylineMapObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.navigine.idl.java.LocationPolyline;
import com.navigine.idl.java.Point;
import com.navigine.idl.java.Polyline;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nonnull;

public class PolylineMapObjectManager extends ViewGroupManager<NaviginePolylineMapObject> {
    public static final String REACT_CLASS = "NaviginePolylineMapObject";
    PolylineMapObjectManager() {}

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Nonnull
    @Override
    public NaviginePolylineMapObject createViewInstance(@Nonnull ThemedReactContext context) {
        return new NaviginePolylineMapObject(context);
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder().build();
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().build();
    }

    private NaviginePolylineMapObject castToPolylineView(View view) {
        return (NaviginePolylineMapObject) view;
    }

        // props
    @ReactProp(name = "polyline")
    public void setLocationPolyline(View view, ReadableMap mapObjectPoint) {
        if (mapObjectPoint != null) {
            ReadableArray polylineArray = mapObjectPoint.getArray("polyline");

            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < polylineArray.size(); ++i) {
                ReadableMap polylineMap = polylineArray.getMap(i);
                float x = (float)polylineMap.getDouble("x");
                float y = (float)polylineMap.getDouble("y");
                points.add(new Point(x, y));
            }

            Polyline polyline = new Polyline(points);
            int locationId = mapObjectPoint.getInt("locationId");
            int sublocationId = mapObjectPoint.getInt("sublocationId");
            LocationPolyline locationPolyline = new LocationPolyline(polyline, locationId, sublocationId);
            castToPolylineView(view).setLocationPolyline(locationPolyline);
        }
    }

    @ReactProp(name = "lineWidth")
    public void setLineWidth(View view, float width) {
        castToPolylineView(view).setLineWidth(width);
    }

    @ReactProp(name = "visible")
    public void setVisible(View view, Boolean visible) {
        castToPolylineView(view).setVisibility(visible != null ? visible : true);
    }

    @ReactProp(name = "interactive")
    public void setInteractive(View view, Boolean interactive) {
        castToPolylineView(view).setInteractive(interactive != null ? interactive : true);
    }

    @ReactProp(name = "styling")
    public void setStyling(View view, String styling) {
        castToPolylineView(view).setStyling(styling);
    }

    @ReactProp(name = "lineColor")
    public void setLineColor(View view, int color) {
        castToPolylineView(view).setLineColor(color);
    }
}
