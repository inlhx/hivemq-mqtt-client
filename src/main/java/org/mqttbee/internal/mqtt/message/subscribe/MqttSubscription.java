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

package org.mqttbee.internal.mqtt.message.subscribe;

import org.jetbrains.annotations.NotNull;
import org.mqttbee.annotations.Immutable;
import org.mqttbee.internal.mqtt.datatypes.MqttTopicFilterImpl;
import org.mqttbee.mqtt.datatypes.MqttQos;
import org.mqttbee.mqtt.mqtt5.message.subscribe.Mqtt5RetainHandling;
import org.mqttbee.mqtt.mqtt5.message.subscribe.Mqtt5Subscription;

/**
 * @author Silvio Giebl
 */
@Immutable
public class MqttSubscription implements Mqtt5Subscription {

    private final @NotNull MqttTopicFilterImpl topicFilter;
    private final @NotNull MqttQos qos;
    private final boolean noLocal;
    private final @NotNull Mqtt5RetainHandling retainHandling;
    private final boolean retainAsPublished;

    public MqttSubscription(
            final @NotNull MqttTopicFilterImpl topicFilter, final @NotNull MqttQos qos, final boolean noLocal,
            final @NotNull Mqtt5RetainHandling retainHandling, final boolean retainAsPublished) {

        this.topicFilter = topicFilter;
        this.qos = qos;
        this.noLocal = noLocal;
        this.retainHandling = retainHandling;
        this.retainAsPublished = retainAsPublished;
    }

    @Override
    public @NotNull MqttTopicFilterImpl getTopicFilter() {
        return topicFilter;
    }

    @Override
    public @NotNull MqttQos getQos() {
        return qos;
    }

    @Override
    public boolean isNoLocal() {
        return noLocal;
    }

    @Override
    public @NotNull Mqtt5RetainHandling getRetainHandling() {
        return retainHandling;
    }

    @Override
    public boolean isRetainAsPublished() {
        return retainAsPublished;
    }

    public @NotNull String toAttributeString() {
        return "topicFilter=" + topicFilter + ", qos=" + qos + ", noLocal=" + noLocal + ", retainHandling=" +
                retainHandling + ", retainAsPublished=" + retainAsPublished;
    }

    @Override
    public @NotNull String toString() {
        return "MqttSubscription{" + toAttributeString() + '}';
    }
}