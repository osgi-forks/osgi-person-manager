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

public class TestComponent2Test {

	static boolean compiled;

	@BeforeClass
	static public void setUp() {
		compiled = CompilerUtils
				.compileAndProcessAnnotations("TestComponent2.java");
	}

	@Test
	public void testCompiler() {
		assertTrue("Compilation of \"TestComponent2.java\" failed", compiled);
	}

	@Test
	public void TestComponent2() throws JDOMException, IOException {
		Element component = getComponent();
		assertEquals("\"component\" tag not found", "component", component
				.getName());
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
				"com.siemens.ct.osgi.a4ds.test.data.TestComponent2", clazz
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
		assertEquals("2 \"provide\" tags", 1, provideList.size());
		Element provide1 = provideList.get(0);
		Attribute interface1 = provide1.getAttribute("interface");
		assertEquals("\"interface\" value",
				"com.siemens.ct.osgi.a4ds.test.data.ITestService", interface1
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

	private Element getComponent() throws JDOMException, IOException {
		Document doc = new SAXBuilder().build("OSGI-INF/TestComponent2.xml");
		Element component = doc.getRootElement();
		assertNotNull("document has no root element", component);
		return component;
	}
}