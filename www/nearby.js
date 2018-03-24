window.subscribe = function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "NearbyPlugin", "subscribe");
    },

window.unsubscribe = function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "NearbyPlugin", "unsubscribe");
    },
    
window.publish = function(message, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "NearbyPlugin", "publish", message);
    }

