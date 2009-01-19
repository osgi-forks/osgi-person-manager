/*******************************************************************************
 * Copyright (c) 2009 Siemens AG
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siemens.ct.pm.application.service.IViewContribution;

public class ViewServiceManager {

	private JTabbedPane viewContainer;
	private final JLabel label;
	private int serviceCount;
	private final HashMap<JComponent, Integer> positionMap;
	private boolean isInitialized = false;
	private final Logger logger = LoggerFactory
			.getLogger(ViewServiceManager.class);

	public ViewServiceManager() {
		label = new JLabel();
		serviceCount = 0;
		positionMap = new HashMap<JComponent, Integer>();
		logger.info("start...");
	}

	public synchronized void setViewContribution(
			IViewContribution viewContribution) {
		waitUntilInitialized();
		logger.info("adding service: " + viewContribution);
		JComponent view = viewContribution.getView();
		positionMap.put(view, viewContribution.getPosition());
		if (serviceCount == 0) {
			viewContainer.remove(label);
		}
		int position = viewContribution.getPosition();
		boolean isInserted = false;
		for (int pos = 0; pos < viewContainer.getTabCount(); pos++) {
			if (position <= positionMap.get(viewContainer.getComponentAt(pos))) {
				viewContainer.insertTab(viewContribution.getName() + " ",
						viewContribution.getIcon(), view, viewContribution
								.getName(), pos);
				isInserted = true;
				break;
			}
		}
		if (!isInserted) {
			viewContainer.insertTab(viewContribution.getName() + " ",
					viewContribution.getIcon(), view, viewContribution
							.getName(), viewContainer.getTabCount());
		}

		serviceCount += 1;
	}

	public synchronized void unsetViewContribution(
			IViewContribution viewContribution) {
		JComponent view = (viewContribution).getView();
		viewContainer.remove(view);
		positionMap.remove(view);
		serviceCount -= 1;
		checkServices();
	}

	private void checkServices() {
		if (serviceCount == 0) {
			viewContainer.addTab("No views available", null, label);
		}
	}

	public synchronized void initialize(JTabbedPane viewContainer) {
		this.viewContainer = viewContainer;
		checkServices();
		this.notifyAll();
		this.isInitialized = true;
	}

	public synchronized void waitUntilInitialized() {
		while (!this.isInitialized) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
