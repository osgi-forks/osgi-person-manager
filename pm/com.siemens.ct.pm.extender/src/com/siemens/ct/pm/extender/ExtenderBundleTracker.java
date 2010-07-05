package com.siemens.ct.pm.extender;

import java.util.HashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.BundleTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtenderBundleTracker extends BundleTracker {

	private final HashMap<String, ServiceRegistration> serviceMap;
	private final Logger logger = LoggerFactory
			.getLogger(ExtenderBundleTracker.class);

	public ExtenderBundleTracker(BundleContext context) {
		super(context, 0xFF, null);
		serviceMap = new HashMap<String, ServiceRegistration>();
	}

	@Override
	public Object addingBundle(Bundle bundle, BundleEvent event) {
		String className = (String) bundle.getHeaders().get(
				"Action-Contribution");
		if (className != null) {
			Class<?> clazz;
			try {
				clazz = bundle.loadClass(className);
				try {
					ServiceRegistration serviceRegistration = bundle
							.getBundleContext()
							.registerService(
									"com.siemens.ct.pm.application.service.IActionContribution",
									clazz.newInstance(), null);
					serviceMap.put(bundle.getSymbolicName(),
							serviceRegistration);
					logger.info("Extender Action Contribution Service registered for: "
							+ clazz.getName());

				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return bundle;
	}
}
