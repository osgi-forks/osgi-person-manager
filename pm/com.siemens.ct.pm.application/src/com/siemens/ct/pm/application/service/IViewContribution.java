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
}
