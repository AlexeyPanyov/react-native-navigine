# React-Bative Navigine
Library for using our SDK (android, IOS) in react-native
# Installation
npm i naviginereactnativetest18

# Example of using for android
### Using View ###
// js
```
import React from 'react';
import LocationView from 'naviginereactnativetest18';
import {
  NativeModules,
  Button,
  PermissionsAndroid,
  NativeEventEmitter,
  View,
} from "react-native";

const Map () => {
const { NavigineModule } = NativeModules;
 
  const initView = () => {
    NavigineModule.init("F90F-0202-58ED-7F06", "https://api.navigine.com");
  }

    return (
    <View>
      <LocationView
        style={{ width: "100%", height: 400 }}
      />
      <Button
        title="initialization view"
        color="#231234"
        onPress={initView}
       />
           <Button
        title="initialization view"
        color="#231234"
        onPress={initView}
       />

    </View>
    );
}
