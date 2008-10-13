/*******************************************************************************
 * Copyright (c) 2008 Siemens AG
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kai Toedter - initial API and implementation
 *******************************************************************************/

package com.siemens.ct.pm.extender;

import java.util.HashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.SynchronousBundleListener;

public class Activator implements BundleActivator, SynchronousBundleListener {

	private BundleContext context;
	private HashMap<String, ServiceRegistration> serviceMap;

	public void start(BundleContext context) throws Exception {

		this.context = context;
		serviceMap = new HashMap<String, ServiceRegistration>();
		context.addBundleListener(this);

		// search for existing bundles
		Bundle[] bundles = context.getBundles();
		for (Bundle bundle : bundles) {
			if (Bundle.ACTIVE == bundle.getState()) {
				addService(bundle);
			}
		}
	}

	public void stop(BundleContext context) throws Exception {
		context.removeBundleListener(this);
	}

	@Override
	public void bundleChanged(BundleEvent event) {
		if (BundleEvent.STARTED == event.getType()) {
			addService(event.getBundle());
		} else if (BundleEvent.STOPPED == event.getType()) {
			removeService(event.getBundle());
		}
	}

	private void removeService(Bundle bundle) {
		ServiceRegistration serviceRegistration = serviceMap.get(bundle
				.getSymbolicName());
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

	@SuppressWarnings("unchecked")
	private void addService(Bundle bundle) {
		String className = (String) bundle.getHeaders().get(
				"Action-Contribution");
		if (className != null) {
			Class clazz;
			try {
				clazz = bundle.loadClass(className);
				try {
					ServiceRegistration serviceRegistration = context
							.registerService(
									"com.siemens.ct.pm.application.service.IActionContribution",
									clazz.newInstance(), null);
					serviceMap.put(bundle.getSymbolicName(),
							serviceRegistration);
					System.out
							.println("Extender: Action Contribution Service registered for: "
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

	}
}
