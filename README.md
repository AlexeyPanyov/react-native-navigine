

# React-Native Navigine
Library for using our SDK (android, IOS) in react-native
# Installation
```npm i naviginereactnativetest18```
# Example of using for android
### Using ###
1. Import 'LocationView'.

```import LocationView from 'naviginereactnativetest18';```

2. Import NavigineModule

```const {NavigineModule} = NativeModules;```

3. Add LocationView with styles.

``` <LocationView style={{width: '100%', height: 400}} />```

4. Use function Navigine.Init("user-hash", "server")

```NavigineModule.init('user-hash', 'server');```

5. Get permissions
 
``` await PermissionsAndroid.request()```

6.  Import IconMapObject;

```import IconMapObject from 'naviginereactnativetest18/components/IconMapObjectView';```

7. Add LocationView with properties x, y, locationId, siblocationId.

```<IconMapObject objectPosition={{x: number, y: number, locationId: number, sublsocationId: number, }} />```
 
8. Import NativeEventEmitter and use with NavigineModule.
 
 ```const eventEmitter = new NativeEventEmitter(NavigineModule);```
 
9. Use method addListner.
 
 ```const eventEmitter = new NativeEventEmitter(NavigineModule)
    eventEmitter.addListener('EventReminder', event => {console.log(event);});```
    
  
```import React from 'react';
import {
  NativeModules,
  Button,
  PermissionsAndroid,
  NativeEventEmitter,
  View,
} from 'react-native';

import LocationView from 'naviginereactnativetest18';
import IconMapObject from 'naviginereactnativetest18/components/IconMapObjectView';

const App = () => {
  const {NavigineModule} = NativeModules;
  const [coords, setCoords] = React.useState({x: 10, y: 10});
  const initView = () => {
    NavigineModule.init('user-hash', 'server');
  };
  React.useEffect(() => {
    const eventEmitter = new NativeEventEmitter(NavigineModule);
    eventEmitter.addListener('EventReminder', event => {
      setCoords(event);
    });
  });
  const requestPermission = async () => {
    try {
      const accessFineLocation = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        {
          title: 'Get access',
          message:
            'These permissions are necessary for' + 'the navigation to work.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      if (accessFineLocation === PermissionsAndroid.RESULTS.GRANTED) {
        console.log('You can use the navigition');
      } else {
        console.log('navigition permission denied');
      }
    } catch (err) {
      console.warn(err);
    }
  };

  return (

        <View>
          <LocationView style={{width: '100%', height: 400}}>
            <IconMapObject
              objectPosition={{
                x: coords.x,
                y: coords.y,
                locationId: 0000,
                sublsocationId: 0000,
              }}
            />
          </LocationView>
          <Button onPress={initView} title="initialization View" />
          <Button onPress={requestPermission} title="Get Permissions" />
        </View>

  );
};

export default App;
