package com.siemens.ct.osgi.a4ds.test.data;

import com.siemens.ct.osgi.a4ds.Component;

@Component(provides = "XXX")
// Should raise an error during annotation processing
public class TestComponent3 implements ITestService {
	public void doSomething() {
	}
}
