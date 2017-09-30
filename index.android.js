import { NativeModules } from 'react-native';
const ImageCompressAndroid = NativeModules.ImageCompressAndroid;

export default {
  createCompressedImage: (imagePath, newWidth, newHeight, directoryPath) => {
    return new Promise((resolve, reject) => {
      ImageCompressAndroid.createCompressedImage(imagePath, newWidth, newHeight, directoryPath, resolve, reject);
    });
  },
};