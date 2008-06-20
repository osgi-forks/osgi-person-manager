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

package com.siemens.ct.pm.ui.actions.person;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class AddPersonAction extends AbstractAction {

	private static final long serialVersionUID = -8825856224871288191L;

	public AddPersonAction() {
		super("Add Person");
		ImageIcon icon = new ImageIcon(this.getClass().getResource(
				"/icons/user_add.png"));
		putValue(Action.SMALL_ICON, icon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog((Component) e.getSource(),
				"Not implemented yet", "Add Person Action Action",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
