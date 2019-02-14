/*
 * Copyright 2018 The MQTT Bee project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.mqttbee.internal.mqtt.message.connect.connack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mqttbee.annotations.Immutable;
import org.mqttbee.internal.mqtt.datatypes.MqttClientIdentifierImpl;
import org.mqttbee.internal.mqtt.datatypes.MqttUserPropertiesImpl;
import org.mqttbee.internal.mqtt.datatypes.MqttUtf8StringImpl;
import org.mqttbee.internal.mqtt.message.MqttMessageWithUserProperties;
import org.mqttbee.internal.util.StringUtil;
import org.mqttbee.mqtt.datatypes.MqttClientIdentifier;
import org.mqttbee.mqtt.datatypes.MqttUtf8String;
import org.mqttbee.mqtt.mqtt5.message.auth.Mqtt5EnhancedAuth;
import org.mqttbee.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;
import org.mqttbee.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAckReasonCode;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * @author Silvio Giebl
 */
@Immutable
public class MqttConnAck extends MqttMessageWithUserProperties.WithReason.WithCode<Mqtt5ConnAckReasonCode>
        implements Mqtt5ConnAck {

    public static final long SESSION_EXPIRY_INTERVAL_FROM_CONNECT = -1;
    public static final int KEEP_ALIVE_FROM_CONNECT = -1;

    private final boolean sessionPresent;
    private final long sessionExpiryInterval;
    private final int serverKeepAlive;
    private final @Nullable MqttClientIdentifierImpl assignedClientIdentifier;
    private final @Nullable Mqtt5EnhancedAuth enhancedAuth;
    private final @NotNull MqttConnAckRestrictions restrictions;
    private final @Nullable MqttUtf8StringImpl responseInformation;
    private final @Nullable MqttUtf8StringImpl serverReference;

    public MqttConnAck(
            final @NotNull Mqtt5ConnAckReasonCode reasonCode, final boolean sessionPresent,
            final long sessionExpiryInterval, final int serverKeepAlive,
            final @Nullable MqttClientIdentifierImpl assignedClientIdentifier,
            final @Nullable Mqtt5EnhancedAuth enhancedAuth, final @NotNull MqttConnAckRestrictions restrictions,
            final @Nullable MqttUtf8StringImpl responseInformation, final @Nullable MqttUtf8StringImpl serverReference,
            final @Nullable MqttUtf8StringImpl reasonString, final @NotNull MqttUserPropertiesImpl userProperties) {

        super(reasonCode, reasonString, userProperties);
        this.sessionPresent = sessionPresent;
        this.sessionExpiryInterval = sessionExpiryInterval;
        this.serverKeepAlive = serverKeepAlive;
        this.assignedClientIdentifier = assignedClientIdentifier;
        this.enhancedAuth = enhancedAuth;
        this.restrictions = restrictions;
        this.responseInformation = responseInformation;
        this.serverReference = serverReference;
    }

    @Override
    public boolean isSessionPresent() {
        return sessionPresent;
    }

    @Override
    public @NotNull OptionalLong getSessionExpiryInterval() {
        return (sessionExpiryInterval == SESSION_EXPIRY_INTERVAL_FROM_CONNECT) ? OptionalLong.empty() :
                OptionalLong.of(sessionExpiryInterval);
    }

    public long getRawSessionExpiryInterval() {
        return sessionExpiryInterval;
    }

    @Override
    public @NotNull OptionalInt getServerKeepAlive() {
        return (serverKeepAlive == KEEP_ALIVE_FROM_CONNECT) ? OptionalInt.empty() : OptionalInt.of(serverKeepAlive);
    }

    public int getRawServerKeepAlive() {
        return serverKeepAlive;
    }

    @Override
    public @NotNull Optional<MqttClientIdentifier> getAssignedClientIdentifier() {
        return Optional.ofNullable(assignedClientIdentifier);
    }

    public @Nullable MqttClientIdentifierImpl getRawAssignedClientIdentifier() {
        return assignedClientIdentifier;
    }

    @Override
    public @NotNull Optional<Mqtt5EnhancedAuth> getEnhancedAuth() {
        return Optional.ofNullable(enhancedAuth);
    }

    public @Nullable Mqtt5EnhancedAuth getRawEnhancedAuth() {
        return enhancedAuth;
    }

    @Override
    public @NotNull MqttConnAckRestrictions getRestrictions() {
        return restrictions;
    }

    @Override
    public @NotNull Optional<MqttUtf8String> getResponseInformation() {
        return Optional.ofNullable(responseInformation);
    }

    @Override
    public @NotNull Optional<MqttUtf8String> getServerReference() {
        return Optional.ofNullable(serverReference);
    }

    @Override
    protected @NotNull String toAttributeString() {
        return "reasonCode=" + getReasonCode() + ", sessionPresent=" + sessionPresent +
                ((sessionExpiryInterval == SESSION_EXPIRY_INTERVAL_FROM_CONNECT) ? "" :
                        ", sessionExpiryInterval=" + sessionExpiryInterval) +
                ((serverKeepAlive == SESSION_EXPIRY_INTERVAL_FROM_CONNECT) ? "" :
                        ", serverKeepAlive=" + serverKeepAlive) +
                ((assignedClientIdentifier == null) ? "" : ", assignedClientIdentifier=" + assignedClientIdentifier) +
                ((enhancedAuth == null) ? "" : ", enhancedAuth=" + enhancedAuth) +
                ((restrictions == MqttConnAckRestrictions.DEFAULT) ? "" : ", restrictions=" + restrictions) +
                ((responseInformation == null) ? "" : ", responseInformation=" + responseInformation) +
                ((serverReference == null) ? "" : ", serverReference=" + serverReference) +
                StringUtil.prepend(", ", super.toAttributeString());
    }

    @Override
    public @NotNull String toString() {
        return "MqttConnAck{" + toAttributeString() + '}';
    }
}