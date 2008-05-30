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

package com.siemens.ct.pm.application;

import java.util.HashMap;

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
	private final HashMap<JComponent, Integer> positionMap;

	public ViewServiceManager(BundleContext context) {
		super(context, IViewContribution.class.getName(), null);
		label = new JLabel();
		serviceCount = 0;
		positionMap = new HashMap<JComponent, Integer>();
		System.out.println("ViewServiceManager started");
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("ViewServiceManager.addingService(): " + reference);
		IViewContribution service = (IViewContribution) context.getService(reference);
		JComponent view = service.getView();
		positionMap.put(view, service.getPosition());
		if (serviceCount == 0) {
			viewContainer.remove(label);
		}
		int position = service.getPosition();
		boolean isInserted = false;
		for (int pos = 0; pos < viewContainer.getTabCount(); pos++) {
			if (position <= positionMap.get(viewContainer.getComponentAt(pos))) {
				viewContainer.insertTab(service.getName() + " ", service.getIcon(), view, service
						.getName(), pos);
				isInserted = true;
				break;
			}
		}
		if (!isInserted) {
			viewContainer.insertTab(service.getName() + " ", service.getIcon(), view, service
					.getName(), viewContainer.getTabCount());
		}

		serviceCount += 1;
		return service;
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		JComponent view = ((IViewContribution) service).getView();
		viewContainer.remove(view);
		positionMap.remove(view);
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
