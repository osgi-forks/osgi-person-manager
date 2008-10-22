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

package com.siemens.ct.pm.application;

import java.awt.Component;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import org.jdesktop.application.Application;

import com.siemens.ct.pm.application.service.IActionContribution;

public class ActionServiceManager {

	private JToolBar toolBar;
	private JMenuBar menuBar;
	private Application application;

	private boolean isInitialized = false;

	public ActionServiceManager() {
		System.out.println("ActionServiceManager started");
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public synchronized void setActionContribution(
			IActionContribution actionContribution) {
		waitUntilInitialized();
		System.out.println("ActionServiceManager.addingService(): "
				+ actionContribution);

		List<Action> actions = actionContribution.getActions(application);

		if (toolBar.getComponentCount() > 0) {
			toolBar.addSeparator();
		}

		// Find actions menu
		JMenu actionsMenu = getActionsMenu();
		if (actionsMenu != null && actionsMenu.getItemCount() > 0) {
			actionsMenu.addSeparator();
		}

		for (Action action : actions) {
			JButton button = new JButton(action);
			button.setToolTipText((String) action.getValue(Action.NAME));
			toolBar.add(button);

			if (actionsMenu != null) {
				actionsMenu.add(action);
			}
		}
		toolBar.revalidate();
		menuBar.revalidate();
	}

	private JMenu getActionsMenu() {
		Component[] menus = menuBar.getComponents();
		for (Component component : menus) {
			if (component instanceof JMenu) {
				JMenu menu = (JMenu) component;
				if (menu.getName().equals("actionsMenu")) {
					return menu;
				}
			}
		}
		return null;
	}

	public synchronized void unsetActionContribution(
			IActionContribution actionContribution) {
		List<Action> actions = actionContribution.getActions(application);

		JMenu actionsMenu = getActionsMenu();
		for (Action action : actions) {
			Component[] components = toolBar.getComponents();
			for (Component component : components) {
				if (component instanceof JButton
						&& action == ((JButton) component).getAction()) {
					toolBar.remove(component);
					break;
				}
			}

			components = actionsMenu.getMenuComponents();
			for (Component component : components) {
				if (component instanceof JMenuItem
						&& action == ((JMenuItem) component).getAction()) {
					actionsMenu.remove(component);
					break;
				}
			}
		}

		// Cleanup toolbar separators
		Component[] components = toolBar.getComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof JToolBar.Separator
					&& (i == 0 || i == components.length - 1 || (i < components.length - 1 && components[i + 1] instanceof JToolBar.Separator))) {
				toolBar.remove(components[i]);
			}
		}

		toolBar.revalidate();
		toolBar.repaint();

		// Cleanup menu separators
		components = actionsMenu.getMenuComponents();
		for (int i = 0; i < components.length; i++) {
			if (components[i] instanceof JPopupMenu.Separator
					&& (i == 0 || i == components.length - 1 || (i < components.length - 1 && components[i + 1] instanceof JPopupMenu.Separator))) {
				actionsMenu.remove(components[i]);
			}
		}

		toolBar.revalidate();
		toolBar.repaint();
	}

	public synchronized void initialize(Application application,
			JToolBar toolBar, JMenuBar menuBar) {
		this.application = application;
		this.toolBar = toolBar;
		this.menuBar = menuBar;
		this.isInitialized = true;
		this.notifyAll();
	}

	public synchronized void waitUntilInitialized() {
		while (!this.isInitialized) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
