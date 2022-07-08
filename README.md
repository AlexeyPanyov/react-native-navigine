import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
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
    NavigineModule.init('F90F-0202-58ED-7F06', 'https://api.navigine.com');
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
    <SafeAreaView>
      <StatusBar />
      <ScrollView contentInsetAdjustmentBehavior="automatic">
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
          <Button onPress={initView} title="Get Permissions" />
          <Button onPress={requestPermission} title="Get Permissions" />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

export default App;
