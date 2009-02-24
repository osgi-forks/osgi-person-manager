/*******************************************************************************
 * Copyright (c) 2009 Siemens AG
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kai Toedter - initial API and implementation
 *******************************************************************************/

package com.siemens.ct.osgi.a4ds.test.data;

import com.siemens.ct.osgi.a4ds.Component;

@Component(provides = "XXX")
// Should raise an error during annotation processing
public class TestComponent3 implements ITestService {
	public void doSomething() {
	}
}
