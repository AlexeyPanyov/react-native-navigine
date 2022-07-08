package com.awesomeproject;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.navigine.idl.java.NavigationManager;

import android.widget.VideoView;
import android.net.Uri;
import android.util.Log;
import com.navigine.view.LocationView;
import com.navigine.sdk.Navigine;
import com.navigine.idl.java.NavigineSdk;
import com.navigine.idl.java.LocationManager;
import com.navigine.idl.java.PositionListener;
import com.navigine.idl.java.Position;

public class NavigineViewManager extends SimpleViewManager<LocationView> {

    public static final String REACT_CLASS = "LocationView";
   

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override   
    protected LocationView createViewInstance(ThemedReactContext reactContext) {
        Navigine.initialize(reactContext);
        NavigineSdk.setUserHash("A9BD-ECC4-F85B-2804");
        LocationView locationView = new LocationView(reactContext);
        locationView.getLocationViewController().setSublocationId(7183);
        Log.d("navigine", "" + locationView.getLocationViewController().getZoomFactor());
        locationView.getLocationViewController().setZoomFactor(3);
        Log.d("navigine", "" + locationView.getLocationViewController().getZoomFactor());
        
        return locationView;
    }
}
