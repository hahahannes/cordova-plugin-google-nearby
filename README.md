# Description
This plugin adds support for the [Google Nearby Messages API](https://developers.google.com/nearby/messages/overview). Support for the other Nearby APIs will follow.

# Installation
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
Default:
```
window.nearby.subscribe()
```

Default:
```
var options = {
    "strategy": "" 
}
window.nearby.subscribe(options)
```

## Ionic 
### Subscribe
```
this.nearby.subscribe(options).then(result => {})
```


# Debug

# Remove
```
cordova plugin rm org.apache.cordova.nearby
```

# Troubleshooting Android
- make sure that the following content is in the AndroidManifest.xml
- make sure that the following content is in the build.gradle file
- make sure you have installed the SDK


