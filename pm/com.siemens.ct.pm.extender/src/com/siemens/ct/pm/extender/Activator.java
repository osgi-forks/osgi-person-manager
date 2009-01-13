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

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private ExtenderBundleTracker extenderBundleTracker;

    public void start(BundleContext context) throws Exception {
        extenderBundleTracker = new ExtenderBundleTracker(context);
        extenderBundleTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
        extenderBundleTracker.close();
    }
}
