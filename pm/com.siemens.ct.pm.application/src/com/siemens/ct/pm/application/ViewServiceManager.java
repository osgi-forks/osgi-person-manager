package com.siemens.ct.pm.application;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.siemens.ct.pm.application.service.IViewContribution;

public class ViewServiceManager extends ServiceTracker {

	private JTabbedPane viewContainer;
	private final JLabel label;
	private int serviceCount;

	public ViewServiceManager(BundleContext context) {
		super(context, IViewContribution.class.getName(), null);
		label = new JLabel();
		serviceCount = 0;
		System.out.println("ViewServiceManager started");
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("ViewServiveManager.addingService(): " + reference);
		IViewContribution service = (IViewContribution) context.getService(reference);
		JComponent view = service.getView();
		if (serviceCount == 0) {
			viewContainer.remove(label);
		}
		viewContainer.addTab(service.getName() + " ", service.getIcon(), view);
		serviceCount += 1;
		return view;
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		viewContainer.remove((JComponent) service);
		context.ungetService(reference);
		serviceCount -= 1;
		checkServices();
	}

	private void checkServices() {
		if (serviceCount == 0) {
			viewContainer.addTab("No views available", null, label);
		}
	}

	public void initialize(JTabbedPane viewContainer) {
		this.viewContainer = viewContainer;
		checkServices();
		open();
	}
}
