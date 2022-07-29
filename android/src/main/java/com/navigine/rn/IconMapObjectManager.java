package com.navigine.rn;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.navigine.rn.view.NavigineIconMapObject;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.navigine.idl.java.AnimationType;
import com.navigine.idl.java.LocationPoint;
import com.navigine.idl.java.Point;

import javax.annotation.Nonnull;

public class IconMapObjectManager extends ViewGroupManager<NavigineIconMapObject> {
    public static final String REACT_CLASS = "NavigineIconMapObject";
    IconMapObjectManager() {}

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    private NavigineIconMapObject castToMapObjectView(View view) {
        return (NavigineIconMapObject) view;
    }

    @Nonnull
    @Override
    public NavigineIconMapObject createViewInstance(@Nonnull ThemedReactContext context) {
        return new NavigineIconMapObject(context);
    }

    // props
    @ReactProp(name = "locationPoint")
    public void setLocationPoint(View view, ReadableMap mapObjectPoint) {
        if (mapObjectPoint != null) {
            ReadableMap pointMap = mapObjectPoint.getMap("point");
            float x = (float)pointMap.getDouble("x");
            float y = (float)pointMap.getDouble("y");
            Point point = new Point(x, y);
            int locationId = mapObjectPoint.getInt("locationId");
            int sublocationId = mapObjectPoint.getInt("sublocationId");
            LocationPoint locationPoint = new LocationPoint(point, locationId, sublocationId);
            castToMapObjectView(view).setLocationPoint(locationPoint);
        }
    }

    // props
    @ReactProp(name = "size")
    public void setSize(View view, ReadableMap mapObjectPoint) {
        if (mapObjectPoint != null) {
            float width = (float)mapObjectPoint.getDouble("width");
            float height = (float)mapObjectPoint.getDouble("height");
            castToMapObjectView(view).setSize(width, height);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(View view, Boolean visible) {
        castToMapObjectView(view).setVisibility(visible != null ? visible : true);
    }

    @ReactProp(name = "interactive")
    public void setInteractive(View view, Boolean interactive) {
        castToMapObjectView(view).setInteractive(interactive != null ? interactive : true);
    }

    @ReactProp(name = "styling")
    public void setStyling(View view, String styling) {
        castToMapObjectView(view).setStyling(styling);
    }


    @Override
    public void addView(NavigineIconMapObject parent, View child, int index) {
        parent.addChildView(child, index);
        super.addView(parent, child, index);
    }

    @Override
    public void removeViewAt(NavigineIconMapObject parent, int index) {
        parent.removeChildView(index);
        super.removeViewAt(parent, index);
    }
    private NavigineIconMapObject castToIconMapObjectView(View view) {
        return (NavigineIconMapObject) view;
    }

    @ReactProp(name = "source")
    public void setSource(View view, String source) {
        if (source != null) {
            castToIconMapObjectView(view).setIconSource(source);
        }
    }

    @Override
    public void receiveCommand(
            @NonNull NavigineIconMapObject view,
            String commandType,
            @Nullable ReadableArray args) {
        switch (commandType) {
            case "setPositionAnimated":
                ReadableMap mapObjectPoint = args.getMap(0);
                ReadableMap pointMap = mapObjectPoint.getMap("point");
                float x = (float) pointMap.getDouble("x");
                float y = (float) pointMap.getDouble("y");
                Point point = new Point(x, y);
                int locationId = mapObjectPoint.getInt("locationId");
                int sublocationId = mapObjectPoint.getInt("sublocationId");
                LocationPoint locationPoint = new LocationPoint(point, locationId, sublocationId);
                int moveDuration = args.getInt(1);
                AnimationType animationType = AnimationType.values()[args.getInt(2)];
                castToIconMapObjectView(view).setPositionAnimated(locationPoint, moveDuration, animationType);
                return;
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }
}
