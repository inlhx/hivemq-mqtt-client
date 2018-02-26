package org.mqttbee.mqtt.message.disconnect.mqtt3;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.mqttbee.annotations.NotNull;
import org.mqttbee.api.mqtt.mqtt3.message.disconnect.Mqtt3Disconnect;
import org.mqttbee.api.mqtt.mqtt5.message.disconnect.Mqtt5DisconnectReasonCode;
import org.mqttbee.mqtt.codec.encoder.mqtt3.Mqtt3DisconnectEncoder;
import org.mqttbee.mqtt.datatypes.MqttUserPropertiesImpl;
import org.mqttbee.mqtt.message.disconnect.MqttDisconnect;

/**
 * @author Silvio Giebl
 */
@Immutable
public class Mqtt3DisconnectView implements Mqtt3Disconnect {

    private static MqttDisconnect WRAPPED;
    private static Mqtt3DisconnectView INSTANCE;

    @NotNull
    public static MqttDisconnect wrapped() {
        if (WRAPPED != null) {
            return WRAPPED;
        }
        return WRAPPED = new MqttDisconnect(Mqtt5DisconnectReasonCode.NORMAL_DISCONNECTION,
                MqttDisconnect.SESSION_EXPIRY_INTERVAL_FROM_CONNECT, null, null,
                MqttUserPropertiesImpl.NO_USER_PROPERTIES, Mqtt3DisconnectEncoder.PROVIDER);
    }

    @NotNull
    public static Mqtt3DisconnectView create() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        return INSTANCE = new Mqtt3DisconnectView();
    }

    private Mqtt3DisconnectView() {
    }

    @NotNull
    public MqttDisconnect getWrapped() {
        return WRAPPED;
    }

}