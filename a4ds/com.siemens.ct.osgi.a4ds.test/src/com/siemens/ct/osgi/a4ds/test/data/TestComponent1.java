package com.siemens.ct.osgi.a4ds.test.data;

import com.siemens.ct.osgi.a4ds.Bind;
import com.siemens.ct.osgi.a4ds.Component;
import com.siemens.ct.osgi.a4ds.Unbind;

@Component
public class TestComponent1 implements ITestService, ITestService2 {

	public void doSomething() {
	}

	public void doSomethingElse() {
	}

	@Bind
	public void bindReference(ITestReference ref) {
	}

	@Unbind
	public void unbindReference(ITestReference ref) {
	}
}
