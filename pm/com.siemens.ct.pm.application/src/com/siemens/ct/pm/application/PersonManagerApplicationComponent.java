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

import com.siemens.ct.pm.application.service.IActionContribution;
import com.siemens.ct.pm.application.service.IViewContribution;

public class PersonManagerApplicationComponent {

	private static ActionServiceManager actionServiceManager;
	private static ViewServiceManager viewServiceManager;

	public PersonManagerApplicationComponent() {
		// this must happen before the 'setActionContribution' or
		// 'setViewContribution'
		// methods are called...
		actionServiceManager = new ActionServiceManager();
		viewServiceManager = new ViewServiceManager();
		PersonManagerApplication.launch(PersonManagerApplication.class, null);
		System.out.println("Application launched asynchronously");
	}

	public static ActionServiceManager getActionServiceManager() {
		return actionServiceManager;
	}

	public static ViewServiceManager getViewServiceManager() {
		return viewServiceManager;
	}

	public void setActionContribution(IActionContribution actionContribution) {
		actionServiceManager.setActionContribution(actionContribution);
	}

	public void unsetActionContribution(IActionContribution actionContribution) {
		actionServiceManager.unsetActionContribution(actionContribution);
	}

	public void setViewContribution(IViewContribution viewContribution) {
		viewServiceManager.setViewContribution(viewContribution);
	}

	public void unsetViewContribution(IViewContribution viewContribution) {
		viewServiceManager.unsetViewContribution(viewContribution);
	}
}
