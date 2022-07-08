package com.awesomeproject; // replace com.your-app-name with your app’s name

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import com.navigine.sdk.Navigine;
import com.navigine.idl.java.NavigineSdk;
import com.navigine.idl.java.LocationManager;
import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReactMethod;
import com.navigine.idl.java.NavigationManager;
import androidx.annotation.Nullable;

import android.net.Uri;
import android.util.Log;
import com.navigine.sdk.Navigine;
import com.navigine.idl.java.NavigineSdk;
import com.navigine.idl.java.LocationManager;
import com.navigine.idl.java.PositionListener;
import com.navigine.idl.java.Position;
import com.navigine.idl.java.Point;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class NavigineModule extends ReactContextBaseJavaModule {
  private LocationManager mLocationManager;
  private NavigationManager mNavigationManager;
  private boolean DEBUG_LOG = true;
  private String mPositionEventName = null;
  private static final String TAG = "NAVIGINE.Demo";

  @Override
  public String getName() {
    return "NavigineModule";
  }

  private ReactApplicationContext getContext() {
    return reactContext;
  }

  private static ReactApplicationContext reactContext = null;

  NavigineModule(ReactApplicationContext context) {
    super(context);
    reactContext = context;
  }

  @ReactMethod
  public void init(final String userHash, final String server) {
    runOnUiThread(new Thread(new Runnable() {
      @Override
      public void run() {
        Navigine.initialize(reactContext);
        NavigineSdk.setUserHash(userHash);
        NavigineSdk.setServer(server);
        NavigineSdk navigineSdk = NavigineSdk.getInstance();
        mLocationManager = navigineSdk.getLocationManager();
        mLocationManager.setLocationId(3046);
        mNavigationManager = navigineSdk.getNavigationManager(mLocationManager);
        mNavigationManager.addPositionListener(new PositionListener() {
          @Override
          public void onPositionUpdated(Position position) {
            if(mPositionEventName == null){
              return;
            }
            WritableMap params = Arguments.createMap();
            params.putDouble("eventProperty", position.getPoint().getX());
            sendEvent(reactContext, mPositionEventName, params);
          }

          @Override
          public void onPositionError(Error error) {
            Log.d("NavigineApp", "onPositionError(). Message: " + error.getMessage());
          }
        });
      }
    }));
  }

  @ReactMethod
  public void setLocationId(final int locationId) {
    runOnUiThread(new Thread(new Runnable() {
      @Override
      public void run() {
        NavigineSdk navigineSdk = NavigineSdk.getInstance();
        LocationManager locationManager = navigineSdk.getLocationManager();
        locationManager.setLocationId(locationId);
      }
    }));
  }

  @ReactMethod
  public void createCalendarEvent(String name, String location) {
    Log.d("NavigineModule", "Create event called with name: " + name
        + " and location: " + location);
  }


  @ReactMethod
  public void setPositionCallback(Callback callback) {
  }

  private void sendEvent(ReactContext reactContext,
      String eventName,
      @Nullable WritableMap params) {
    reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, params);
  }

  @ReactMethod
  public void addListener(String eventName) {
    mPositionEventName = eventName;
  }

  @ReactMethod
  public void removeListeners(Integer count) {
    // Remove upstream listeners, stop unnecessary background tasks
  }

}
