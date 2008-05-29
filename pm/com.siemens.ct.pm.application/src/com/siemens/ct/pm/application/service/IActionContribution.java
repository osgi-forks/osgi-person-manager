package com.siemens.ct.pm.application.service;

import javax.swing.Action;

/**
 * A simple Action UI contribution, providing the action and the locations in
 * the tool bar and the menu.
 */
public interface IActionContribution {
	public Action getAction();

	public String getMenuLocation();

	public String getToolBarLocation();
}
