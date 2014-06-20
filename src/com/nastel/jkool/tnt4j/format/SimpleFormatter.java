/*
 * Copyright 2014 Nastel Technologies, Inc.
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
package com.nastel.jkool.tnt4j.format;

import com.nastel.jkool.tnt4j.tracker.TrackingActivity;
import com.nastel.jkool.tnt4j.tracker.TrackingEvent;

/**
 * <p>
 * Simple implementation of <code>Formatter</code> interface provides
 * simple/minimal formatting of <code>TrackingActvity</code> and <code>TrackingEvent</code>
 * as well as any object passed to <code>format()</code> method call.
 * </p>
 * 
 * 
 * @version $Revision: 1 $
 * 
 * @see DefaultFormatter
 * @see TrackingActivity
 * @see TrackingEvent
 */

public class SimpleFormatter extends DefaultFormatter {
	@Override
	public String format(TrackingEvent event) {
		StringBuffer msg = new StringBuffer(1024);
		msg.append("{'").append(event.getSeverity()).append("',");
		msg.append("event='").append(event.getStringMessage()).append("',");
		msg.append("name='").append(event.getOperation().getName()).append("',");
		msg.append("ccode='").append(event.getOperation().getCompCode()).append("',");
		if (event.getOperation().getReasonCode() != 0) {
			msg.append("rcode='").append(event.getOperation().getReasonCode()).append("',");
		}
		if (event.getOperation().getElapsedTime() != 0) {
			msg.append("usec='").append(event.getOperation().getElapsedTime()).append("',");
		}
		if (event.getOperation().getMessageAge() != 0) {
			msg.append("age.usec='").append(event.getOperation().getMessageAge()).append("',");
		}
		if (event.getTag() != null) {
			msg.append("tag='").append(event.getTag()).append("',");
		}
		if (event.getOperation().getCorrelator() != null) {
			msg.append("corr-id='").append(event.getOperation().getCorrelator()).append("',");
		}
		if (event.getOperation().getThrowable() != null) {
			msg.append("error='").append(event.getOperation().getExceptionString()).append("',");
		}
		msg.append("type='").append(event.getOperation().getType()).append("',");
		msg.append("track-id='").append(event.getTrackingId()).append("',");
		if (event.getParentItem() != null) {
			msg.append("parent-id='").append(event.getParentItem().getTrackingId()).append("'");
		}
		msg.append("}");
		return 	msg.toString();
	}
	
	@Override
	public String format(TrackingActivity activity) {
		return activity.getStatus() + "-" + activity.toString();
	}
}
