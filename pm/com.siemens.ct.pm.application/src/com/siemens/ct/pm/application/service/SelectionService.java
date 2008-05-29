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
