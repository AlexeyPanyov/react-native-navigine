package com.navigine.rn.view;

import android.content.Context;
import android.graphics.Color;

import com.navigine.rn.models.ReactMapObject;
import com.facebook.react.views.view.ReactViewGroup;
import com.navigine.idl.java.LocationPolyline;
import com.navigine.idl.java.MapObject;
import com.navigine.idl.java.PolylineMapObject;

public class NaviginePolylineMapObject extends ReactViewGroup implements ReactMapObject {

    private LocationPolyline locationPolyline = null;
    private Boolean visible = true;
    private Boolean interactive = true;
    private float width = 20.f;
    private String styling;
    private PolylineMapObject mapObject = null;
    private int lineColor = Color.BLACK;

    public NaviginePolylineMapObject(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void setMapObject(MapObject obj) {
        mapObject = (PolylineMapObject) obj;
        updateMapObject();
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    private void updateMapObject() {
        if(mapObject != null) {
            mapObject.setPolyLine(locationPolyline);
            mapObject.setVisible(visible);
            mapObject.setInteractive(interactive);
            mapObject.setWidth(width);
            float red = Color.red(lineColor) / 255.f;
            float green = Color.green(lineColor) / 255.f;
            float blue = Color.blue(lineColor) / 255.f;
            float alpha = Color.alpha(lineColor) / 255.f;
            mapObject.setColor(red, green, blue, alpha);
            if (styling != null) {
                mapObject.setStyle(styling);
            }

        }
    }

    public void setLocationPolyline(LocationPolyline _locationPolyline) {
        locationPolyline = _locationPolyline;
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

    public void setLineWidth(float _width) {
        width = _width;
        updateMapObject();
    }

    public void setStyling(String _styling) {
        styling = _styling;
        updateMapObject();
    }

    public void setLineColor(int _color) {
        lineColor = _color;
        updateMapObject();
    }
}
