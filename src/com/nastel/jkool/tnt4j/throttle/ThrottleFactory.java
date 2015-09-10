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
package com.nastel.jkool.tnt4j.throttle;

public interface ThrottleFactory {
	/**
	 * Create a new throttle (0 means unlimited)
	 * 
	 * @param maxMps maximum message/second rate
	 * @param maxBps maximum bytes/second rate
	 * @return new throttled instance
	 */
	Throttle newThrottle(int maxMps, int maxBps, boolean enabled);
}
