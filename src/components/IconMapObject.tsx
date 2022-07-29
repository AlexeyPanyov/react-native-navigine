import React from 'react';
import { requireNativeComponent, Platform, ImageSourcePropType, UIManager, findNodeHandle } from 'react-native';
// @ts-ignore
import resolveAssetSource from 'react-native/Libraries/Image/resolveAssetSource';
import { LocationPoint } from '../interfaces';

export interface IconMapObjectProps {
  children?: React.ReactElement;
  locationPoint: LocationPoint;
  source?: ImageSourcePropType;
  size?: { width: number, height: number };
  styling?: string;
  visible?: boolean;
  interactive?: boolean;
}

const NativeIconMapObjectComponent = requireNativeComponent<IconMapObjectProps & { pointerEvents: 'none' }>('NavigineIconMapObject');

interface State {
  recreateKey: boolean;
  children: any;
}

export class IconMapObject extends React.Component<IconMapObjectProps, State> {
  state = {
    recreateKey: false,
    children: this.props.children,
  };

  private getCommand(cmd: string): any {
    if (Platform.OS === 'ios') {
      return UIManager.getViewManagerConfig('NavigineIconMapObject').Commands[cmd];
    } else {
      return cmd;
    }
  }

  static getDerivedStateFromProps(nextProps: IconMapObjectProps, prevState: State): Partial<State> {
    if (Platform.OS === 'ios') {
      return {
        children: nextProps.children,
        recreateKey:
          nextProps.children === prevState.children
            ? prevState.recreateKey
            : !prevState.recreateKey,
      };
    }
    return {
      children: nextProps.children,
      recreateKey: Boolean(nextProps.children),
    };
  }

  private resolveImageUri(img?: ImageSourcePropType) {
    return img ? resolveAssetSource(img).uri : '';
  }

  private getProps() {
    return {
      ...this.props,
      source: this.resolveImageUri(this.props.source),
    };
  }

  public setPositionAnimated(locationPoint: LocationPoint, duration: number, animation: Animation) {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this),
      this.getCommand('setPositionAnimated'),
      [locationPoint, duration, animation],
    );
  }

  render() {
    return (
      <NativeIconMapObjectComponent
        {...this.getProps()}
        key={String(this.state.recreateKey)}
        pointerEvents='none'
      />
    );
  }
}
