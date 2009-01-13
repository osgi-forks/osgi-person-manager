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

package com.siemens.ct.pm.application.service;

import java.util.List;

import javax.swing.Action;

import org.jdesktop.application.Application;

/**
 * A simple Action UI contribution, providing the action and the locations in
 * the tool bar and the menu.
 */
public interface IActionContribution {
    public List<Action> getActions(Application application);

    public String getMenuLocation();

    public String getToolBarLocation();
}
