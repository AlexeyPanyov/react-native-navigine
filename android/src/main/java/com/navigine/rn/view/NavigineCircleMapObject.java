package com.navigine.rn.view;

import android.content.Context;
import android.graphics.Color;

import com.navigine.rn.models.ReactMapObject;
import com.facebook.react.views.view.ReactViewGroup;
import com.navigine.idl.java.CircleMapObject;
import com.navigine.idl.java.LocationPoint;
import com.navigine.idl.java.MapObject;

public class NavigineCircleMapObject extends ReactViewGroup implements ReactMapObject {

    private LocationPoint center = null;
    private Boolean visible = true;
    private Boolean interactive = true;
    private float radius = 20.f;
    private String styling;
    private CircleMapObject mapObject = null;
    private int circleColor = Color.BLACK;

    public NavigineCircleMapObject(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void setMapObject(MapObject obj) {
        mapObject = (CircleMapObject) obj;
        updateMapObject();
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    private void updateMapObject() {
        if(mapObject != null) {
            mapObject.setPosition(center);
            mapObject.setVisible(visible);
            mapObject.setInteractive(interactive);
            mapObject.setRadius(radius);
            float red = Color.red(circleColor) / 255.f;
            float green = Color.green(circleColor) / 255.f;
            float blue = Color.blue(circleColor) / 255.f;
            float alpha = Color.alpha(circleColor) / 255.f;
            mapObject.setColor(red, green, blue, alpha);
            if (styling != null) {
                mapObject.setStyle(styling);
            }

        }
    }

    public void setCenter(LocationPoint _center) {
        center = _center;
        updateMapObject();
    }

    public void setVisibility(Boolean _visible) {
        visible = _visible;
        updateMapObject();
    }

    public void setInteractive(Boolean _interactive) {
        interactive = _interactive;
        updateMapObject();
    }

    public void setRadius(float _radius) {
        radius = _radius;
        updateMapObject();
    }

    public void setStyling(String _styling) {
        styling = _styling;
        updateMapObject();
    }

    public void setCircleColor(int _color) {
        circleColor = _color;
        updateMapObject();
    }
}
