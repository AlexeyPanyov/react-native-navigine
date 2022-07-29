import React from 'react';
import { requireNativeComponent} from 'react-native';
// @ts-ignore

import { LocationPoint } from '../interfaces';
import { processColorProps } from '../utils';

export interface CircleMapObjectProps {
  center: LocationPoint;
  radius?: number;
  circleColor?: string;
  styling?: string;
  visible?: boolean;
  interactive?: boolean;
}

const NativeCircleMapObjectComponent = requireNativeComponent<CircleMapObjectProps>('NavigineCircleMapObject');

export class CircleMapObject extends React.Component<CircleMapObjectProps> {
    render() {
      const props = { ...this.props };
      processColorProps(props, 'circleColor' as keyof CircleMapObjectProps);
      return <NativeCircleMapObjectComponent {...props} />;
    }
}
