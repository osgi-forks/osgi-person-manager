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
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonManagerApplication {

	private JFrame mainFrame;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JTabbedPane viewContainer;
	private AboutDialog aboutDialog;
	private final Logger logger = LoggerFactory
			.getLogger(PersonManagerApplication.class);

	public void showAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new AboutDialog(mainFrame);
			aboutDialog.setLocationRelativeTo(mainFrame);
		}
		aboutDialog.setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		JMenuItem exitItem = fileMenu.add(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitItem.setText("Exit");
		menuBar.add(fileMenu);

		JMenu actionsMenu = new JMenu("Action");
		actionsMenu.setName("actionsMenu");
		actionsMenu.setMnemonic('A');
		menuBar.add(actionsMenu);

		// Menu for the look and feels (lnfs).
		UIManager.LookAndFeelInfo[] lnfs = UIManager.getInstalledLookAndFeels();

		ButtonGroup lnfGroup = new ButtonGroup();
		JMenu lnfMenu = new JMenu("Look&Feel");
		lnfMenu.setMnemonic('L');

		menuBar.add(lnfMenu);

		for (int i = 0; i < lnfs.length; i++) {
			if (!lnfs[i].getName().equals("CDE/Motif")) {
				JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(lnfs[i]
						.getName());
				lnfMenu.add(rbmi);

				// preselect the current Look & feel
				rbmi.setSelected(UIManager.getLookAndFeel().getName().equals(
						lnfs[i].getName()));

				// store lool & feel info as client property
				rbmi.putClientProperty("lnf name", lnfs[i]);

				// create and add the item listener
				rbmi.addItemListener(
				// inlining
						new ItemListener() {
							public void itemStateChanged(ItemEvent ie) {
								JRadioButtonMenuItem rbmi2 = (JRadioButtonMenuItem) ie
										.getSource();

								if (rbmi2.isSelected()) {
									// get the stored look & feel info
									UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo) rbmi2
											.getClientProperty("lnf name");

									try {
										UIManager.setLookAndFeel(info
												.getClassName());

										// update the complete application's
										// look & feel
										SwingUtilities
												.updateComponentTreeUI(mainFrame);
									} catch (Exception e) {
										e.printStackTrace();

										System.err.println("Unable to set UI "
												+ e.getMessage());
									}
								}
							}
						});
				lnfGroup.add(rbmi);
			}
		}

		// the help menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		JMenuItem aboutItem = helpMenu.add(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showAboutDialog();
			}
		});
		aboutItem.setText("About");
		aboutItem.setMnemonic('A');
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A',
				java.awt.Event.CTRL_MASK));

		menuBar.add(helpMenu);

		return menuBar;
	}

	private JToolBar createToolBar() {
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
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

	protected void startup() {
		menuBar = createMenuBar();
		mainFrame.setJMenuBar(menuBar);

		JComponent mainPanel = createMainPanel();
		mainPanel.setVisible(true);
		logger.info("Application UI skeleton initialized");

		PersonManagerApplicationComponent.getActionServiceManager().initialize(
				toolBar, menuBar);
		PersonManagerApplicationComponent.getViewServiceManager().initialize(
				viewContainer);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}

	public void launch() {
		Runnable appStarter = new Runnable() {
			public void run() {
				mainFrame = new JFrame("PM Dynamic OSGi Demo");
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setSize(800, 600);
				startup();
			}
		};
		SwingUtilities.invokeLater(appStarter);
	}
}
