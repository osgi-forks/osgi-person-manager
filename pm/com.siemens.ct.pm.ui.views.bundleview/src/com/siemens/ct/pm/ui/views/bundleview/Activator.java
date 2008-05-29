package com.siemens.ct.pm.ui.views.bundleview;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static Bundle[] bundles;

	public static synchronized Bundle[] getBundles() {
		return bundles;
	}

	public void start(BundleContext context) throws Exception {
		bundles = context.getBundles();
	}

	public void stop(BundleContext context) throws Exception {
	}

}
