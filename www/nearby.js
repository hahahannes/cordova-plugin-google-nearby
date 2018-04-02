var exec = require('cordova/exec');
var nearby = {
subscribe: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, "NearbyPlugin", "subscribe");
    },

unsubscribe: function (successCallback, errorCallback) {
        exec(successCallback, errorCallback, "NearbyPlugin", "unsubscribe");
    },
    
publish: function(message, successCallback, errorCallback) {
        exec(successCallback, errorCallback, "NearbyPlugin", "publish", message);
    }
}


module.exports = nearby;


