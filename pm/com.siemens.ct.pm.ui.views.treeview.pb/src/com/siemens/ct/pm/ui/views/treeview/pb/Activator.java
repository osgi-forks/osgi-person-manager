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

package com.siemens.ct.pm.ui.views.treeview.pb;

import static com.google.inject.Guice.createInjector;
import static org.ops4j.peaberry.Peaberry.osgiModule;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ops4j.peaberry.Export;
import org.ops4j.peaberry.Import;
import org.ops4j.peaberry.util.AbstractScope;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.siemens.ct.pm.application.service.IViewContribution;
import com.siemens.ct.pm.model.IPersonManager;

public class Activator implements BundleActivator {

	private TreeViewPeaberry treeView;
	private List<IPersonManager> personManagers;

	@Inject
	Export<IViewContribution> handle;

	@Inject
	public void setPersonManager(IPersonManager personManager) {
		// Do nothing, the injection to the treeView is handles by the PMScope
		// But if we don't have an injection, the binding is not done...
	}

	public void start(BundleContext context) throws Exception {
		personManagers = new ArrayList<IPersonManager>();
		treeView = new TreeViewPeaberry();

		Injector inj = createInjector(osgiModule(context),
				new AbstractModule() {
					@Override
					protected void configure() {
						bind(IPersonManager.class).toProvider(
								service(IPersonManager.class)
										.out(new PMScope()).single());
						bind(export(IViewContribution.class)).toProvider(
								service(treeView).export());
					}
				});
		inj.injectMembers(this);
	}

	public void stop(BundleContext context) throws Exception {
		personManagers = null;
		treeView = null;
		handle.unput();
	}

	private class PMScope extends AbstractScope<IPersonManager> {

		@Override
		protected IPersonManager adding(Import<IPersonManager> service) {
			IPersonManager instance = service.get();
			if (personManagers.size() == 0) {
				treeView.setPersonManager(instance);
				handle.put(null);
				handle.put(treeView);
			}
			personManagers.add(instance);
			return instance;
		}

		@Override
		protected void modified(IPersonManager instance,
				Map<String, ?> attributes) {
		}

		@Override
		protected void removed(IPersonManager instance) {
			treeView.removePersonManager(instance);
			personManagers.remove(instance);
			if (personManagers.size() == 0) {
				handle.unput();
			} else {
				treeView.setPersonManager(personManagers.get(0));
			}
		}
	}
}
