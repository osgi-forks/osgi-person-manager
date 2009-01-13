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

import java.util.ArrayList;
import java.util.List;

public class SelectionService implements ISelectionService {

    private final List<ISelectionListener> listeners;

    public SelectionService() {
        listeners = new ArrayList<ISelectionListener>();
    }

    public synchronized void registerSelectionListener(ISelectionListener listener) {
        System.out.println("SelectionService.registerSelectionListener(): " + listener);
        listeners.add(listener);
    }

    public synchronized void unregisterSelectionListener(ISelectionListener listener) {
        listeners.remove(listener);
    }

    @Override
    public synchronized void objectSelected(Object selectedObject) {
        System.out.println("SelectionService.objectSelected(): " + selectedObject);
        for (ISelectionListener listener : listeners) {
            listener.selectionChanged(selectedObject);
        }
    }

}
