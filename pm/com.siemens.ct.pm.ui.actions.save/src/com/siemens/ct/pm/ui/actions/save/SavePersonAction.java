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

package com.siemens.ct.pm.ui.actions.save;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SavePersonAction extends AbstractAction {

    private static final long serialVersionUID = 4872237049739003596L;

    public SavePersonAction() {
        super("Save Person");
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/icons/drive_user.png"));
        putValue(Action.SMALL_ICON, icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog((Component) e.getSource(), "Not implemented yet",
                "Add Person Action Action", JOptionPane.INFORMATION_MESSAGE);
    }
}
