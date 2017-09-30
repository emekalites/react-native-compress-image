/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  CameraRoll,
  StyleSheet,
  Text,
  View,
  Image,
  Alert,
  TouchableOpacity
} from 'react-native';
import Spinner from 'react-native-gifted-spinner';
import CompressImage from 'react-native-compress-image';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  image: {
    width: 250,
    height: 250,
  },
  compressButton: {
    color: '#333333',
    fontWeight: 'bold',
    marginBottom: 5,
  }
});

export default class compress extends Component {
  constructor() {
    super();

    this.state = {
      compressedImageUri: '',
      loading: true,
    };
  }

  componentDidMount() {
    CameraRoll.getPhotos({first: 1}).then((photos) => {
      if (!photos.edges || photos.edges.length === 0) {
        return Alert.alert('Unable to load camera roll',
          'Check that you authorized the access to the camera roll photos and that there is at least one photo in it');
      }

      this.setState({
        image: photos.edges[0].node.image,
      })
    }).catch(() => {
      return Alert.alert('Unable to load camera roll',
        'Check that you authorized the access to the camera roll photos');
    });
  }

  resize() {
    CompressImage.createCompressedImage(this.state.image.uri, 400, 400, 'Compress/Images')
    .then(({uri}) => {
      console.log(uri);
      this.setState({
        compressedImageUri: uri,
      });
    }).catch((err) => {
      console.log(err);
      return Alert.alert('Unable to compress photo, Check the console for full the error message');
    });
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Image Compression example
        </Text>
        <Text style={styles.instructions}>
          This is the original image:
        </Text>
        {
          this.state.image ?
            <Image style={styles.image} source={{uri: this.state.image.uri}} /> :
            <Spinner />
        }
        <Text style={styles.instructions}>
          Compressed image:
        </Text>
        <TouchableOpacity onPress={() => this.resize()}>
          <Text style={styles.compressButton}>
            Click me to compress the image
          </Text>
        </TouchableOpacity>
        {
          this.state.compressedImageUri ?
          <Image style={styles.image} source={{uri: this.state.compressedImageUri}} /> : null
        }
      </View>
    );
  }
}


AppRegistry.registerComponent('compress', () => compress);
