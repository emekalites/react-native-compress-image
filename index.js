import { NativeModules } from 'react-native';
const ImageCompressAndroid = NativeModules.ImageCompressAndroid;

export default {
  createCompressedImage: (imagePath, directoryPath) => {
    return new Promise((resolve, reject) => {
      ImageCompressAndroid.createCompressedImage(imagePath, directoryPath, resolve, reject);
    });
  },
  createCustomCompressedImage: (imagePath, directoryPath, maxWidth, maxHeight, quality) => {
    return new Promise((resolve, reject) => {
      ImageCompressAndroid.createCustomCompressedImage(imagePath, directoryPath, maxWidth, maxHeight, quality, resolve, reject);
    });
  },
};