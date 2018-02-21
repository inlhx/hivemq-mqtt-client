package org.mqttbee.mqtt.codec.encoder.mqtt5;

import org.mqttbee.api.mqtt5.message.publish.pubcomp.Mqtt5PubCompReasonCode;
import org.mqttbee.mqtt.codec.encoder.mqtt5.Mqtt5MessageWithPropertiesEncoder.Mqtt5MessageWithIdAndOmissibleReasonCodeEncoder;
import org.mqttbee.mqtt.codec.encoder.provider.MqttMessageEncoderProvider;
import org.mqttbee.mqtt.message.MqttMessageType;
import org.mqttbee.mqtt.message.publish.pubcomp.MqttPubCompImpl;

import static org.mqttbee.mqtt.message.publish.pubcomp.MqttPubCompImpl.DEFAULT_REASON_CODE;

/**
 * @author Silvio Giebl
 */
public class Mqtt5PubCompEncoder extends
        Mqtt5MessageWithIdAndOmissibleReasonCodeEncoder<MqttPubCompImpl, Mqtt5PubCompReasonCode, MqttMessageEncoderProvider<MqttPubCompImpl>> {

    public static final MqttMessageEncoderProvider<MqttPubCompImpl> PROVIDER = Mqtt5PubCompEncoder::new;

    private static final int FIXED_HEADER = MqttMessageType.PUBCOMP.getCode() << 4;

    @Override
    protected int getFixedHeader() {
        return FIXED_HEADER;
    }

    @Override
    protected Mqtt5PubCompReasonCode getDefaultReasonCode() {
        return DEFAULT_REASON_CODE;
    }

}