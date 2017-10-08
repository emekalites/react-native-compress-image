# React Native Compress Image
[![npm version](https://badge.fury.io/js/react-native-compress-image.svg)](https://badge.fury.io/js/react-native-compress-image)
[![npm downloads](https://img.shields.io/npm/dt/react-native-compress-image.svg)](https://badge.fury.io/js/react-native-compress-image)

React Native Compress Image for Android

**NOTE: The iOS side of this module will be included when i have figured it out.**

## Installation

`npm install --save react-native-compress-image`

`react-native link react-native-compress-image`

**NOTE: For Android, you will still have to manually update the AndroidManifest.xml (as below) in order to use Scheduled Notifications.**

## Android manual Installation

In your `AndroidManifest.xml`
```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

In `android/settings.gradle`
```gradle
include ':react-native-compress-image'
project(':react-native-compress-image').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-compress-image/android')
```

in `android/app/build.gradle`
```java
dependencies {
    compile project(':react-native-compress-image')  // <--- Add package to dependencies
    compile fileTree(dir: "libs", include: ["*.jar"])
    compile "com.android.support:appcompat-v7:23.0.1"
    compile "com.facebook.react:react-native:+"
    ....
}
```

in `MainApplication.java`:

```java
import com.emekalites.react.compress.image.ImageCompressPackage;  // <--- Import Package

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
      @Override
      protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
      }

      @Override
      protected List<ReactPackage> getPackages() {
      	return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new ImageCompressPackage() 		// <---- Add the Package
        );
      }
  };
}
```

## Usage

```javascript
import CompressImage from 'react-native-compress-image';

CompressImage.createCompressedImage(imageUri, appDirectory).then((response) => {
  // response.uri is the URI of the new image that can now be displayed, uploaded...
  // response.path is the path of the new image
  // response.name is the name of the new image with the extension
  // response.size is the size of the new image
}).catch((err) => {
  // Oops, something went wrong. Check that the filename is correct and
  // inspect err to get more details.
});

CompressImage.createCustomCompressedImage(imageUri, appDirectory, maxWidth, maxHeight, quality).then((response) => {
  // response.uri is the URI of the new image that can now be displayed, uploaded...
  // response.path is the path of the new image
  // response.name is the name of the new image with the extension
  // response.size is the size of the new image
}).catch((err) => {
  // Oops, something went wrong. Check that the filename is correct and
  // inspect err to get more details.
});
```
## Sample App

A basic, sample app is available in [the `example` folder](https://github.com/emekalites/react-native-compress-image/tree/master/example). It uses the module to compress a photo from the Camera Roll.

## API

### `promise createCompressedImage(imageUri, appDirectory)`

The promise resolves with an object containing: `path`, `uri`, `name` and `size` of the new file. The URI can be used directly as the `source` of an [`<Image>`](https://facebook.github.io/react-native/docs/image.html) component.

Option | Description
------ | -----------
imageUri | Path of image file whether jpeg or png
appDirectory | The folder or path to save the compressed image

### `promise createCustomCompressedImage(imageUri, appDirectory, maxWidth, maxHeight, quality)`

The promise resolves with an object containing: `path`, `uri`, `name` and `size` of the new file. The URI can be used directly as the `source` of an [`<Image>`](https://facebook.github.io/react-native/docs/image.html) component.

Option | Description
------ | -----------
imageUri | Path of image file whether jpeg or png
appDirectory | The folder or path to save the compressed image
maxWidth | Image maximum width
maxHeight | Image maximum height
quality | Image quality

## Credits

[https://github.com/zetbaitsu/Compressor](https://github.com/zetbaitsu/Compressor)
