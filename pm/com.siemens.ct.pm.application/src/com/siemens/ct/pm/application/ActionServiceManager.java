package com.siemens.ct.pm.application;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.siemens.ct.pm.application.service.IActionContribution;

public class ActionServiceManager extends ServiceTracker {

	private JToolBar toolBar;
	private JMenuBar menuBar;

	public ActionServiceManager(BundleContext context) {
		super(context, IActionContribution.class.getName(), null);
		System.out.println("ActionServiceManager started");
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	@Override
	public Object addingService(ServiceReference reference) {
		System.out.println("ActionServiceManager.addingService(): " + reference);
		Action action;

		IActionContribution actionContribution = (IActionContribution) context
				.getService(reference);
		action = actionContribution.getAction();

		JButton button = new JButton(action);
		// button.setText("");

		button.setToolTipText((String) action.getValue(Action.NAME));

		toolBar.add(button);
		toolBar.revalidate();

		Component[] menus = menuBar.getComponents();
		for (Component component : menus) {
			if (component instanceof JMenu) {
				JMenu menu = (JMenu) component;
				if (menu.getName().equals("actionsMenu")) {
					menu.add(action);
				}
			}
		}
		menuBar.revalidate();

		return button;
	}

	@Override
	public void removedService(ServiceReference reference, Object service) {
		JButton button = (JButton) service;

		toolBar.remove(button);
		toolBar.revalidate();
		toolBar.repaint();

		Component[] menus = menuBar.getComponents();
		for (Component component : menus) {
			if (component instanceof JMenu) {
				JMenu menu = (JMenu) component;
				if (menu.getName().equals("actionsMenu")) {
					for (int i = 0; i < menu.getItemCount(); i++) {
						JMenuItem item = menu.getItem(i);
						if (item.getAction() == button.getAction()) {
							menu.remove(item);
							break;
						}
					}
				}
			}
		}

		context.ungetService(reference);
	}

	public void initialize(JToolBar toolBar, JMenuBar menuBar) {
		this.toolBar = toolBar;
		this.menuBar = menuBar;
		open();
	}
}
