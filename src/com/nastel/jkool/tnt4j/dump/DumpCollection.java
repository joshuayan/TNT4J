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
package com.nastel.jkool.tnt4j.dump;

import com.nastel.jkool.tnt4j.core.Snapshot;


/**
 * <p>
 * Classes that implement this interface provide implementation for 
 * the <code>DumpCollection</code> interface, which defines a container for 
 * holding application dump information as a snapshot. 
 * </p>
 * 
 * 
 * @version $Revision: 5 $
 * 
 * @see DumpProvider
 */

public interface DumpCollection extends Snapshot {

	/**
	 * Obtain <code>DumpProvider</code> instance associated with the dump.
	 * Dump provider generates instances of the <code>DumpCollection</code> 
	 * 
	 * @return a dump provider associated with this dump.
	 */
	public DumpProvider getDumpProvider();
	

	/**
	 * Obtain reason why the dump was triggered or generated
	 * 
	 * @return reason for dump being generated
	 */
	public Throwable getReason();
	
	/**
	 * Set reason why the dump was triggered or generated
	 * 
	 * @param reason of what caused dump generation
	 */
	public void setReason(Throwable reason);
	
}
