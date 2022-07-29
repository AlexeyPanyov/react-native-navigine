import React from 'react';
import { requireNativeComponent} from 'react-native';
// @ts-ignore

import { LocationPolyline } from '../interfaces';
import { processColorProps } from '../utils';

export interface PolylineMapObjectProps {
  polyline: LocationPolyline;
  lineWidth?: number;
  lineColor?: string;
  styling?: string;
  visible?: boolean;
  interactive?: boolean;
}

const NativePolylineMapObjectComponent = requireNativeComponent<PolylineMapObjectProps>('NaviginePolylineMapObject');

export class PolylineMapObject extends React.Component<PolylineMapObjectProps> {
    render() {
      const props = { ...this.props };
      processColorProps(props, 'lineColor' as keyof PolylineMapObjectProps);
      return <NativePolylineMapObjectComponent {...props} />;
    }
}
