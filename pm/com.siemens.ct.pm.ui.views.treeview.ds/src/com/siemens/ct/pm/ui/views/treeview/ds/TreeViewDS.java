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

package com.siemens.ct.pm.ui.views.treeview.ds;

import org.slf4j.LoggerFactory;

import com.siemens.ct.pm.application.service.ISelectionService;
import com.siemens.ct.pm.application.service.IViewContribution;
import com.siemens.ct.pm.model.IPersonListener;
import com.siemens.ct.pm.model.IPersonManager;
import com.siemens.ct.pm.ui.views.treeview.TreeView;

public class TreeViewDS extends TreeView implements IViewContribution,
		IPersonListener {

	public TreeViewDS() {
		super();
		logger = LoggerFactory.getLogger(TreeViewDS.class);
		logger.info("TreeViewDS created");
	}

	@Override
	public String getName() {
		return "Tree View (DS)";
	}

	@Override
	public int getPosition() {
		return 1;
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

	@Override
	public synchronized void removePersonManager(
			final IPersonManager personManager) {
		super.removePersonManager(personManager);
	}

	@Override
	public synchronized void setPersonManager(final IPersonManager personManager) {
		super.setPersonManager(personManager);
	}
}
