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

package com.siemens.ct.pm.ui.views.bundleview;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

public class Activator implements BundleActivator, SynchronousBundleListener {

    private static Bundle[] bundles;

    public static synchronized Bundle[] getBundles() {
        return bundles;
    }

    public void start(BundleContext context) throws Exception {
        Bundle[] allBundles = context.getBundles();
        ArrayList<Bundle> bundleList = new ArrayList<Bundle>();

        // Hack for adding only our relevant bundles to the list
        for (Bundle bundle : allBundles) {
            String symbolicName = bundle.getSymbolicName();
            if ((symbolicName.startsWith("com.siemens.ct.pm.model") || symbolicName
                    .startsWith("com.siemens.ct.pm.ui"))
                    && !symbolicName.equals("com.siemens.ct.pm.ui.views.bundleview")
                    && !symbolicName.equals("com.siemens.ct.pm.model")) {
                bundleList.add(bundle);
            }
        }
        bundles = bundleList.toArray(new Bundle[] {});
    }

    public void stop(BundleContext context) throws Exception {
    }

    @Override
    public void bundleChanged(BundleEvent event) {
        if (BundleEvent.STARTED == event.getType()) {

        } else if (BundleEvent.STOPPED == event.getType()) {

        }
    }

}
