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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestComponent1Test {

	static boolean compiled;

	@BeforeClass
	static public void setUp() {
		compiled = CompilerUtils
				.compileAndProcessAnnotations("TestComponent1.java");
	}

	@Test
	public void testCompiler() {
		assertTrue("Compilation of \"TestComponent1.java\" failed", compiled);
	}

	@Test
	public void testComponent() throws JDOMException, IOException {
		Element component = getComponent();
		assertEquals("\"component\" tag not found", "component", component
				.getName());
	}

	private Element getComponent() throws JDOMException, IOException {
		Document doc = new SAXBuilder().build("OSGI-INF/TestComponent1.xml");
		Element component = doc.getRootElement();
		assertNotNull("document has no root element", component);
		return component;
	}

	@Test
	public void testImplementation() throws JDOMException, IOException {
		Element component = getComponent();
		Element implementation = component.getChild("implementation");
		assertNotNull("document root has no tag \"implementation\"",
				implementation);
		Attribute clazz = implementation.getAttribute("class");
		assertNotNull(
				"\"class\" attribute not found in tag \"implementation\"",
				clazz);
		assertEquals("\"class\" value",
				"com.siemens.ct.osgi.a4ds.test.data.TestComponent1", clazz
						.getValue());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testService() throws JDOMException, IOException {
		Element component = getComponent();
		Element service = component.getChild("service");
		assertNotNull("document root has no tag \"service\"", service);
		List<Element> provideList = service.getChildren("provide");
		assertNotNull("no \"provide\" tags found in tag \"service\"",
				provideList);
		assertEquals("2 \"provide\" tags", 2, provideList.size());
		Element provide1 = provideList.get(0);
		Attribute interface1 = provide1.getAttribute("interface");
		assertEquals("\"interface\" value",
				"com.siemens.ct.osgi.a4ds.test.data.ITestService", interface1
						.getValue());
		Element provide2 = provideList.get(1);
		Attribute interface2 = provide2.getAttribute("interface");
		assertEquals("\"interface\" value",
				"com.siemens.ct.osgi.a4ds.test.data.ITestService2", interface2
						.getValue());

	}

	@Test
	public void testReference() throws JDOMException, IOException {
		Element component = getComponent();
		Element reference = component.getChild("reference");
		assertNotNull("document root has no tag \"reference\"", reference);

		Attribute name = reference.getAttribute("name");
		assertEquals("\"name\" value",
				"com.siemens.ct.osgi.a4ds.test.data.ITestReference", name
						.getValue());
		Attribute interfaze = reference.getAttribute("interface");
		assertEquals("\"interface\" value",
				"com.siemens.ct.osgi.a4ds.test.data.ITestReference", interfaze
						.getValue());
		Attribute cardinality = reference.getAttribute("cardinality");
		assertEquals("\"cardinality\" value", "1..1", cardinality.getValue());
		Attribute bind = reference.getAttribute("bind");
		assertEquals("\"bind\" value", "bindReference", bind.getValue());
		Attribute unbind = reference.getAttribute("unbind");
		assertEquals("\"unbind\" value", "unbindReference", unbind.getValue());
	}
}
