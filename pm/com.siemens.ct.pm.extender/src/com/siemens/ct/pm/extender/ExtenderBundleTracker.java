package com.siemens.ct.pm.extender;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.util.tracker.BundleTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtenderBundleTracker extends BundleTracker {

	private final Logger logger = LoggerFactory
			.getLogger(ExtenderBundleTracker.class);

	public ExtenderBundleTracker(BundleContext context) {
		super(context, Bundle.ACTIVE, null);
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
					bundle.getBundleContext()
							.registerService(
									"com.siemens.ct.pm.application.service.IActionContribution",
									clazz.newInstance(), null);
					logger.info("Extender Action Contribution Service registered for: "
							+ clazz.getName());

				} catch (InstantiationException e) {
					logger.error("Could not instantiate " + className, e);
				} catch (IllegalAccessException e) {
					logger.error("Illegal access during instatiation of class "
							+ className, e);
				}
			} catch (ClassNotFoundException e) {
				logger.error("Could not find class " + className, e);
			}
		}
		return bundle;
	}
}
