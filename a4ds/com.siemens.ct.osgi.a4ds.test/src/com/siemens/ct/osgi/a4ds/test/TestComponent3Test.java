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

package com.siemens.ct.osgi.a4ds.test;

import static org.junit.Assert.assertFalse;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestComponent3Test {

	static boolean compiled;

	@BeforeClass
	static public void setUp() {
		compiled = CompilerUtils
				.compileAndProcessAnnotations("TestComponent3.java");
	}

	@Test
	public void testCompiler() {
		assertFalse("Compilation of \"TestComponent3.java\" should fail!",
				compiled);
	}

}
