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

package com.siemens.ct.pm.application;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonManagerApplication extends SingleFrameApplication {

	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JTabbedPane viewContainer;
	private AboutDialog aboutDialog;
	private final Logger logger = LoggerFactory
			.getLogger(PersonManagerApplication.class);

	@Action
	public void showAboutDialog() {
		if (aboutDialog == null) {
			JFrame mainFrame = getMainFrame();
			aboutDialog = new AboutDialog(mainFrame);
			aboutDialog.setLocationRelativeTo(mainFrame);
		}
		show(aboutDialog);
	}

	private javax.swing.Action getAction(String actionName) {
		return getContext().getActionMap().get(actionName);
	}

	private JMenu createMenu(String menuName, String[] actionNames) {
		JMenu menu = new JMenu();
		menu.setName(menuName);
		if (actionNames != null) {
			for (String actionName : actionNames) {
				if (actionName.equals("---")) {
					menu.add(new JSeparator());
				} else {
					JMenuItem menuItem = new JMenuItem();
					menuItem.setAction(getAction(actionName));
					menu.add(menuItem);
				}
			}
		}
		return menu;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		String[] fileMenuActionNames = { "quit" };
		menuBar.add(createMenu("fileMenu", fileMenuActionNames));
		menuBar.add(createMenu("actionsMenu", null));
		String[] helpMenuActionNames = { "showAboutDialog" };
		menuBar.add(createMenu("helpMenu", helpMenuActionNames));
		return menuBar;
	}

	private JToolBar createToolBar() {
		String[] toolbarActionNames = {};
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		for (String actionName : toolbarActionNames) {
			if (actionName.equals("---")) {
				toolBar.addSeparator();
			} else {
				JButton button = new JButton();
				button.setAction(getAction(actionName));
				button.setText(null);
				button.setFocusable(false);
				toolBar.add(button);
			}
		}
		return toolBar;

	}

	private JComponent createMainPanel() {

		viewContainer = new JTabbedPane();

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(viewContainer, BorderLayout.CENTER);
		panel.add(createToolBar(), BorderLayout.NORTH);
		panel.setBorder(new EmptyBorder(0, 4, 4, 4));

		return panel;

	}

	@Override
	protected void startup() {
		menuBar = createMenuBar();
		getMainFrame().setJMenuBar(menuBar);
		JComponent mainPanel = createMainPanel();
		logger.info("Application UI skeleton initialized");

		PersonManagerApplicationComponent.getActionServiceManager().initialize(
				this, toolBar, menuBar);
		PersonManagerApplicationComponent.getViewServiceManager().initialize(
				viewContainer);

		show(mainPanel);
	}

	/**
	 * Runs after the startup has completed and the GUI is up and ready.
	 */
	@Override
	protected void ready() {
	}
}
