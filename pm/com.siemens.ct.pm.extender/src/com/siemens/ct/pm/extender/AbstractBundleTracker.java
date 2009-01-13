/*******************************************************************************
 * Copyright (c) 2009 Heiko Seeberger and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *     Heiko Seeberger - initial implementation
 ******************************************************************************/

package com.siemens.ct.pm.extender;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

import com.siemens.ct.pm.extender.AbstractBundleTracker.Configurer.Action;

/**
 * @author Heiko Seeberger
 */
public abstract class AbstractBundleTracker {

    public interface Configurer {

        public enum Action {
            TRACK, UNTRACK
        };

        boolean addExistingBundle(Bundle bundle);

        Action getBundleChangedAction(BundleEvent event);
    }

    protected final BundleContext context;

    private final Configurer configurer;

    private boolean isOpened = false;

    // Do not use symbolic name as key, because it is not unique without
    // version!
    private final Map<Long, Bundle> tracked = new HashMap<Long, Bundle>();

    private SynchronousBundleListener trackingListener;

    public AbstractBundleTracker(final BundleContext context, final Configurer configurer) {
        // TODO Check preconditions!
        this.context = context;
        this.configurer = configurer;
    }

    public void close() {

        // Set open/close state
        synchronized (this) {
            if (!isOpened) {
                return;
            } else {
                isOpened = false;
            }
        }

        // First remove tracking listener
        context.removeBundleListener(trackingListener);

        // Then clear tracked bundles
        tracked.clear();
    }

    public Collection<Bundle> getBundles() {
        synchronized (tracked) {
            return new HashSet<Bundle>(tracked.values());
        }
    }

    public void open() {

        // Set open/close state
        synchronized (this) {
            if (isOpened) {
                return;
            } else {
                isOpened = true;
            }
        }

        // First register tracking listener
        trackingListener = new SynchronousBundleListener() {

            public void bundleChanged(final BundleEvent event) {
                final Action bundleChangedAction = configurer.getBundleChangedAction(event);
                if (bundleChangedAction == Action.TRACK) {
                    track(event.getBundle());
                }
                if (bundleChangedAction == Action.UNTRACK) {
                    untrack(event.getBundle());
                }
            }
        };
        context.addBundleListener(trackingListener);

        // Then track all bundles already there
        for (final Bundle bundle : context.getBundles()) {
            if (configurer.addExistingBundle(bundle)) {
                track(bundle);
            }
        }
    }

    protected abstract void tracked(Bundle bundle);

    protected abstract void untracked(Bundle bundle);

    private void track(final Bundle bundle) {
        boolean isNotTracked = false;
        synchronized (tracked) {
            isNotTracked = (tracked.put(bundle.getBundleId(), bundle) == null);
        }
        if (isNotTracked) {
            tracked(bundle);
        }
    }

    private void untrack(final Bundle bundle) {
        boolean wasTracked = false;
        synchronized (tracked) {
            wasTracked = (tracked.remove(bundle.getBundleId()) != null);
        }
        if (wasTracked) {
            untracked(bundle);
        }
    }
}
