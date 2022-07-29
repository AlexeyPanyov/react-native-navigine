package com.navigine.rn;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.List;

public class RNLocationViewPackage implements ReactPackage {
    public RNLocationViewPackage() {
    }

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        return Arrays.asList(new RNNavigineModule(reactContext));
    }

    @NonNull
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        return Arrays.asList(
                new NavigineViewManager(),
                new IconMapObjectManager(),
                new PolylineMapObjectManager(),
                new CircleMapObjectManager()
        );
    }
}