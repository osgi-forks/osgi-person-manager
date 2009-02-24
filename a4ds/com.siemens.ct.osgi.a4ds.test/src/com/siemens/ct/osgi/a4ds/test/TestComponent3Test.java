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
