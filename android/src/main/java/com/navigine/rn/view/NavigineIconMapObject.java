package com.navigine.rn.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.navigine.rn.models.ReactMapObject;
import com.navigine.rn.utils.ImageLoader;
import com.facebook.react.views.view.ReactViewGroup;
import com.navigine.idl.java.AnimationType;
import com.navigine.idl.java.IconMapObject;
import com.navigine.idl.java.LocationPoint;
import com.navigine.idl.java.MapObject;

import java.util.ArrayList;

public class NavigineIconMapObject extends ReactViewGroup implements ReactMapObject {

    private LocationPoint locationPoint = null;
    private Float duration = null;
    private AnimationType animationType = AnimationType.NONE;
    private Boolean visible = true;
    private Boolean interactive = true;
    private float width = 20.f;
    private float height = 20.f;
    private String styling;
    private IconMapObject mapObject = null;
    private String iconSource;
    private View _childView;
    private final ArrayList<View> childs = new ArrayList<>();

    private final OnLayoutChangeListener childLayoutListener = (v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> updateMapObject();

    public NavigineIconMapObject(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    public void setMapObject(MapObject obj) {
        mapObject = (IconMapObject) obj;
        updateMapObject();
    }

    public MapObject getMapObject() {
        return mapObject;
    }
    public void setChildView(View view) {
        if (view == null) {
            _childView.removeOnLayoutChangeListener(childLayoutListener);
            _childView = null;
            updateMapObject();
            return;
        }
        _childView = view;
        _childView.addOnLayoutChangeListener(childLayoutListener);
    }

    private void updateMapObject() {
        if(mapObject != null && locationPoint != null) {
            if (duration == null) {
                mapObject.setPosition(locationPoint);
            } else {
                mapObject.setPositionAnimated(locationPoint, duration, animationType);
            }
            mapObject.setVisible(visible);
            mapObject.setInteractive(interactive);
            mapObject.setSize(width, height);
            if (styling != null) {
                mapObject.setStyle(styling);
            }

            if (_childView != null) {
                try {
                    Bitmap b = Bitmap.createBitmap(_childView.getWidth(), _childView.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas c = new Canvas(b);
                    _childView.draw(c);
                    mapObject.setBitmap(b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (childs.size() == 0) {
                if (!iconSource.equals("")) {
                    ImageLoader.DownloadImageBitmap(getContext(), iconSource, bitmap -> {
                        try {
                            if (mapObject != null) {
                                mapObject.setBitmap(bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    }

    public void addChildView(View view, int index) {
        childs.add(index, view);
        setChildView(childs.get(0));
    }

    public void removeChildView(int index) {
        childs.remove(index);
        setChildView(childs.size() > 0 ? childs.get(0) : null);
    }

    public void setLocationPoint(LocationPoint _locationPoint) {
        locationPoint = _locationPoint;
        duration = null;
        animationType = AnimationType.NONE;
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

    public void setIconSource(String source) {
        iconSource = source;
        updateMapObject();
    }

    public void setSize(float _width, float _height) {
        width = _width;
        height = _height;
        updateMapObject();
    }

    public void setStyling(String _styling) {
        styling = _styling;
        updateMapObject();
    }

    public void setPositionAnimated(LocationPoint _locationPoint, float _duration, AnimationType _type) {
        locationPoint = _locationPoint;
        duration = _duration;
        animationType = _type;
        updateMapObject();
    }

}
