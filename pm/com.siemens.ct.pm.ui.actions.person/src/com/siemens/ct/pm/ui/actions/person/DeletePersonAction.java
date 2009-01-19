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

package com.siemens.ct.pm.ui.actions.person;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.siemens.ct.pm.model.IPerson;
import com.siemens.ct.pm.model.IPersonManager;

@SuppressWarnings("serial")
public class DeletePersonAction extends AbstractAction {

	private IPersonManager personManager;
	private IPerson selectedPerson;

	public DeletePersonAction() {
		super("Delete Person");
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				"/icons/user_delete.png"));
		putValue(Action.SMALL_ICON, icon);
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (personManager != null && selectedPerson != null) {
			personManager.deletePerson(selectedPerson);
		}
	}

	public synchronized void setPersonManager(IPersonManager personManager) {
		this.personManager = personManager;
	}

	public void setSelectedPerson(IPerson selectedPerson) {
		this.selectedPerson = selectedPerson;
	}
}
