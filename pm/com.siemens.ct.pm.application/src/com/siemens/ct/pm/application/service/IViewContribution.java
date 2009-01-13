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

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 * A simple view UI contribution
 */
public interface IViewContribution {
    public JComponent getView();

    public Icon getIcon();

    public String getName();

    public int getPosition();
}
