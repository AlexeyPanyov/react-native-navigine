import PropTypes from 'prop-types';
import {requireNativeComponent} from 'react-native';
import {ViewPropTypes} from 'deprecated-react-native-prop-types';
var viewProps = {
  name: 'LocationView',
  propTypes: {
    url: PropTypes.string,
    ...ViewPropTypes,
  },
};
module.exports = requireNativeComponent('LocationView', viewProps);
