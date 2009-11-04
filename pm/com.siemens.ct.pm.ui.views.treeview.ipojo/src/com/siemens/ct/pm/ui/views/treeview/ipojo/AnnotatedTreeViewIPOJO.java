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

package com.siemens.ct.pm.ui.views.treeview.ipojo;

import org.apache.felix.ipojo.annotations.Bind;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Unbind;
import org.slf4j.LoggerFactory;

import com.siemens.ct.pm.application.service.ISelectionService;
import com.siemens.ct.pm.application.service.IViewContribution;
import com.siemens.ct.pm.model.IPersonListener;
import com.siemens.ct.pm.model.IPersonManager;
import com.siemens.ct.pm.ui.views.treeview.TreeView;

@Component(name = "AnnotatedTreeViewIPOJO")
@Provides
public class AnnotatedTreeViewIPOJO extends TreeView implements
		IViewContribution, IPersonListener {

	public AnnotatedTreeViewIPOJO() {
		logger = LoggerFactory.getLogger(AnnotatedTreeViewIPOJO.class);
		logger.info("AnnotatedTreeViewIPOJO created");
	}

	@Override
	public String getName() {
		return "Tree View (iPOJO, AN)";
	}

	@Override
	public int getPosition() {
		return 2;
	}

	@Override
	public synchronized void removeSelectionService(
			ISelectionService selectionService) {
		super.removeSelectionService(selectionService);
	}

	@Override
	public synchronized void setSelectionService(
			ISelectionService selectionService) {
		super.setSelectionService(selectionService);
	}

	@Unbind
	public synchronized void unbindPersonManager(
			final IPersonManager personManager) {
		super.removePersonManager(personManager);
	}

	@Bind
	public synchronized void bindPersonManager(
			final IPersonManager personManager) {
		super.setPersonManager(personManager);
	}
}
