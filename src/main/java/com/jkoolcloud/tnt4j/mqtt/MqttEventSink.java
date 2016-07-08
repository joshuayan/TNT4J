/*
 * Copyright 2014-2015 JKOOL, LLC.
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
 */
package com.jkoolcloud.tnt4j.mqtt;

import java.io.IOException;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.jkoolcloud.tnt4j.core.OpLevel;
import com.jkoolcloud.tnt4j.core.Snapshot;
import com.jkoolcloud.tnt4j.format.EventFormatter;
import com.jkoolcloud.tnt4j.sink.AbstractEventSink;
import com.jkoolcloud.tnt4j.sink.EventSink;
import com.jkoolcloud.tnt4j.source.Source;
import com.jkoolcloud.tnt4j.tracker.TrackingActivity;
import com.jkoolcloud.tnt4j.tracker.TrackingEvent;

/**
 * <p>
 * This class implements {@link EventSink} with MQTT as the underlying sink
 * implementation.
 * </p>
 * 
 * 
 * @version $Revision: 1 $
 * 
 * @see OpLevel
 * @see EventFormatter
 * @see AbstractEventSink
 * @see MqttEventSinkFactory
 */
public class MqttEventSink extends AbstractEventSink {

	MqttClient mqttClient;
	MqttEventSinkFactory factory;
	
	public MqttEventSink(MqttEventSinkFactory f, String nm) {
	    super(nm);
	    factory = f;
    }

	public MqttEventSink(MqttEventSinkFactory f, String name, Properties props) {
	    super(name);
	    factory = f;
   }

	public MqttEventSink(MqttEventSinkFactory f, String name, Properties props, EventFormatter frmt) {
	    super(name, frmt);
	    factory = f;
    }

	@Override
    public boolean isSet(OpLevel sev) {
	    return true;
    }

	@Override
    public Object getSinkHandle() {
	    return mqttClient;
    }

	@Override
    public boolean isOpen() {
	    return mqttClient.isConnected();
    }

	@Override
    public void open() throws IOException {
		try {
	        mqttClient = factory.newMqttClient();
        } catch (MqttException e) {
	        throw new IOException(e);
        }
	}

	@Override
    public void close() throws IOException {
		if (mqttClient != null) {
			try {
	            mqttClient.close();
            } catch (MqttException e) {
    	        throw new IOException(e);
            }
		}
	}

	@Override
    protected void _log(TrackingEvent event) throws Exception {
		MqttMessage message = factory.newMqttMessage(getEventFormatter().format(event).getBytes());
		factory.publish(mqttClient, message);
	}

	@Override
    protected void _log(TrackingActivity activity) throws Exception {
		MqttMessage message = factory.newMqttMessage(getEventFormatter().format(activity).getBytes());
		factory.publish(mqttClient, message);
    }

	@Override
    protected void _log(Snapshot snapshot) throws Exception {
		MqttMessage message = factory.newMqttMessage(getEventFormatter().format(snapshot).getBytes());
		factory.publish(mqttClient, message);
    }

	@Override
    protected void _log(long ttl, Source src, OpLevel sev, String msg, Object... args) throws Exception {
		MqttMessage message = factory.newMqttMessage(getEventFormatter().format(ttl, src, sev, msg, args).getBytes());
		factory.publish(mqttClient, message);
    }

	@Override
    protected void _write(Object msg, Object... args) throws IOException, InterruptedException {
		try {
			MqttMessage message = factory.newMqttMessage(getEventFormatter().format(msg, args).getBytes());
	        factory.publish(mqttClient, message);
        } catch (MqttPersistenceException e) {
	        throw new IOException(e);
        } catch (MqttException e) {
	        throw new IOException(e);
        }
    }

}