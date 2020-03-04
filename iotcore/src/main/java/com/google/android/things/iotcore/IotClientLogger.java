package com.google.android.things.iotcore;

import com.google.android.things.iotcore.ConnectionCallback.DisconnectReason;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.jose4j.lang.JoseException;

/**
 * Interface for a lightweight logger. All function calls should not block on IO.
 */
public interface IotClientLogger {

    /** Error when attempting to forcibly disconnect (e.g. during shutdown) */
    void onErrorDisconnecting(MqttException e);

    /** Attempting to connect */
    void onConnectAttempt();

    /** Background thread is shutting down */
    void onThreadShutdown();

    /** Error signing JWT */
    void onJwtException(JoseException e);

    /** Error publishing message */
    void onPublishError(String topic, int messageLength, MqttException e);

    /** Connection successful */
    void onConnection();

    /** Disconnected (may be called multiple times for a single disconnection event) */
    void onDisconnect(@DisconnectReason final int disconnectReason);

    /** Hit a "retryable" exception (meaning the client will attempt to reconnect */
    void onRetryableException(MqttException e);

    /** Hit an "unretryable" exception (meaning the client will shut down and not attempt to reconnect */
    void onUnretryableException(MqttException e);
}
