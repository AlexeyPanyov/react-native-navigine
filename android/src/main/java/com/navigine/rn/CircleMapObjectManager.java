package com.navigine.rn;

import android.view.View;

import androidx.annotation.NonNull;

import com.navigine.rn.view.NavigineCircleMapObject;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.navigine.idl.java.LocationPoint;
import com.navigine.idl.java.Point;

import java.util.Map;

import javax.annotation.Nonnull;

public class CircleMapObjectManager extends ViewGroupManager<NavigineCircleMapObject> {
    public static final String REACT_CLASS = "NavigineCircleMapObject";
    CircleMapObjectManager() {}

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Nonnull
    @Override
    public NavigineCircleMapObject createViewInstance(@Nonnull ThemedReactContext context) {
        return new NavigineCircleMapObject(context);
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder().build();
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().build();
    }

    private NavigineCircleMapObject castToCircleView(View view) {
        return (NavigineCircleMapObject) view;
    }

        // props
    @ReactProp(name = "center")
    public void setCenter(View view, ReadableMap mapObjectPoint) {
        if (mapObjectPoint != null) {
            ReadableMap pointMap = mapObjectPoint.getMap("point");
            float x = (float)pointMap.getDouble("x");
            float y = (float)pointMap.getDouble("y");
            Point point = new Point(x, y);
            int locationId = mapObjectPoint.getInt("locationId");
            int sublocationId = mapObjectPoint.getInt("sublocationId");
            LocationPoint center = new LocationPoint(point, locationId, sublocationId);
            castToCircleView(view).setCenter(center);
        }
    }

    @ReactProp(name = "radius")
    public void setRadius(View view, float radius) {
        castToCircleView(view).setRadius(radius);
    }

    @ReactProp(name = "visible")
    public void setVisible(View view, Boolean visible) {
        castToCircleView(view).setVisibility(visible != null ? visible : true);
    }

    @ReactProp(name = "interactive")
    public void setInteractive(View view, Boolean interactive) {
        castToCircleView(view).setInteractive(interactive != null ? interactive : true);
    }

    @ReactProp(name = "styling")
    public void setStyling(View view, String styling) {
        castToCircleView(view).setStyling(styling);
    }

    @ReactProp(name = "circleColor")
    public void setCircleColor(View view, int color) {
        castToCircleView(view).setCircleColor(color);
    }
}
