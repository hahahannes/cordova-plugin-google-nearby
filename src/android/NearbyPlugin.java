package org.apache.cordova;
import android.util.Log;
import org.apache.cordova.*;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.nearby.Nearby;

import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.tasks.OnFailureListener;

public class NearbyPlugin extends CordovaPlugin {
    private static final String TAG = "NearbyPlugin";
    private static CallbackContext publish_callback;
    private static CallbackContext subscribe_callback;
    private static CallbackContext unsubscribe_callback;
    private static CallbackContext unpublish_callback;
    private static final int REQUEST_RESOLVE_ERROR = 1001;

    private static Message mActiveMessage = null;

    MessageListener mMessageListener = new MessageListener() {
        @Override
        public void onFound(Message message) {
            String found_message = new String(message.getContent());
            Log.d(TAG, "found messsage: " + found_message);
            PluginResult result = new PluginResult(PluginResult.Status.OK, found_message);
            result.setKeepCallback(true);
            NearbyPlugin.this.subscribe_callback.sendPluginResult(result);
        }

        @Override
        public void onLost(Message message) {
            Log.d(TAG, "message lost: " + message);
        }
    };
    OnFailureListener failListener = new OnFailureListener() {
        @Override
        public void onFailure(Exception e) {
            Log.e(TAG, e.getMessage());
        }
    };

    @Override
    public boolean execute(String action, final String message, final CallbackContext callbackContext) throws JSONException {
        if (action.equals("subscribe")) {
            this.subscribe_callback = callbackContext;
            this.subscribe();
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            this.subscribe_callback.sendPluginResult(pluginResult);
            return true;
        } else if (action.equals("unsubscribe")) {
            this.unsubscribe_callback = callbackContext;
            this.unsubscribe();
            return true;
        } else if (action.equals("publish")) {
            this.publish_callback = callbackContext;
            this.publish(message);
            return true;
        } else if (action.equals("unpublish")) {
            this.unpublish_callback = callbackContext;
            this.unpublish();
            return true;
        } else {
            return false;
        }
    }

    // Subscribe to receive messages.
    private void subscribe() {
        SubscribeCallback callback = new SubscribeCallback() {
            @Override
            public void onExpired() {
                Log.d(TAG, "subscribtion expired");
            }
        };
        SubscribeOptions options = new SubscribeOptions.Builder().setCallback(callback).build();
        Nearby.getMessagesClient(cordova.getActivity()).subscribe(NearbyPlugin.this.mMessageListener, options).addOnFailureListener(this.failListener);
        Log.d(TAG, "subscribed successfully");

    }

    private void publish(String message) {
        PublishCallback callback = new PublishCallback() {
            @Override
            public void onExpired() {
                Log.d(TAG, "publish expired");
            }
        };        
        PublishOptions options = new PublishOptions.Builder().setCallback(callback).build();
        this.mActiveMessage = new Message(message.getBytes());
        Nearby.getMessagesClient(cordova.getActivity()).publish(this.mActiveMessage, options).addOnFailureListener(this.failListener);
        Log.d(TAG, "published message: " + message);
        this.publish_callback.success("published message");
    }

    private void unsubscribe() {
        Nearby.getMessagesClient(cordova.getActivity()).unsubscribe(this.mMessageListener);
        Log.d(TAG, "unsubscribed");
        this.unsubscribe_callback.success("unsubscribed");
    }
    private void unpublish() {
        if (this.mActiveMessage != null) {
            Nearby.getMessagesClient(cordova.getActivity()).unpublish(this.mActiveMessage);
            Log.d(TAG, "unpublished");
            this.unpublish_callback.success("unpublished message");
            this.mActiveMessage = null;
        } else {
            this.unpublish_callback.success("nothing to unpublish");
        }
    }
}