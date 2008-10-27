package com.siemens.ct.pm.extender;

import java.util.HashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceRegistration;

public class ExtenderBundleTracker extends AbstractBundleTracker {

	private final HashMap<String, ServiceRegistration> serviceMap;

	public ExtenderBundleTracker(BundleContext context) {
		super(context, new Configurer() {

			@Override
			public boolean addExistingBundle(Bundle bundle) {
				return true;
			}

			@Override
			public Action getBundleChangedAction(BundleEvent event) {
				if (event.getType() == BundleEvent.STARTED) {
					return Action.TRACK;
				} else if (event.getType() == BundleEvent.STOPPED) {
					return Action.UNTRACK;
				}
				return null;
			}
		});
		serviceMap = new HashMap<String, ServiceRegistration>();
	}

	@Override
	protected void tracked(Bundle bundle) {
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

	@Override
	protected void untracked(Bundle bundle) {
		ServiceRegistration serviceRegistration = serviceMap.get(bundle
				.getSymbolicName());
		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
	}

}
