[![](https://img.shields.io/npm/dm/cordova-plugin-google-nearby.svg)](https://www.npmjs.com/package/cordova-plugin-google-nearby)
# Description
This plugin adds support for the [Google Nearby Messages API](https://developers.google.com/nearby/messages/overview). Support for the other Nearby APIs will follow.

# Installation
## Requirements
Please follow the Steps 1, 2 and 3 of the [Getting Started of Google](https://developers.google.com/nearby/messages/android/get-started), which are these one:
- Install Google Play services with Android SDK Manager
- Install Google Repository with Android SDK Manager
- Activate the Google Nearby API in the Google Developer Console

## Automatically
Cordova
```
cordova plugin add cordova-plugin-google-nearby --variable API_KEY="123456789"
```

Ionic v2
```
ionic cordova plugin add cordova-plugin-google-nearby --variable API_KEY="123456789"
```
## From source 
```
cordova plugin add https://github.com/hahahannes/cordova-plugin-google-nearby --variable API_KEY="123456789"
```

# Usage
The plugin provides three functions for subscription, publishing and unsubscribing.

## Cordova
### Subscribe
```
window.nearby.subscribe(function(success) {
    console.log(success)
}, function(error) {
     console.log(error)
})
```

### Publish
```
window.nearby.publish(message, function(success) {
    console.log(success)
}, function(error) {
    console.log(error)
})
```

### Unsubscribe
```
window.nearby.unsubscribe(function(success) {
    console.log(success)
}, function(error) {
    console.log(error)
})
```

## Ionic 
- you can use [Ionic Native](https://ionicframework.com/docs/native/) 

### Installtion

```
ionic cordova plugin add cordova-plugin-google-nearby
npm install --save @ionic-native/google-nearby
```

### Subscribe
```
import { GoogleNearby } from '@ionic-native/google-nearby';
subscribtion: any
constructor(private nearby: GoogleNearby) { 
    this.subscribtion = this.nearby.subscribe().subscribe(result => {
        console.log(result)
    })
}
```

### Unubscribe
```
import { GoogleNearby } from '@ionic-native/google-nearby';
constructor(private nearby: GoogleNearby) { }
this.subscribtion.unsubscribe()
```

### Publish
```
import { GoogleNearby } from '@ionic-native/google-nearby';
constructor(private nearby: GoogleNearby) { }
this.nearby.publish(message).then(result => {
    console.log(result)
})
```

# Debug
```shell
adb logcat 
```

# Remove
Cordova
```
cordova plugin rm org.apache.cordova.nearby
```

Ionic
```
ionic cordova plugin rm org.apache.cordova.nearby
```

# Troubleshooting Android
- make sure that the following content is in the AndroidManifest.xml
- make sure that the following content is in the build.gradle file
- make sure you have the requirements from above
- check in the nearby settings if your app is deactivated 

# Notes
Currently there is a problem with installing this plugin and cordova version 7.


