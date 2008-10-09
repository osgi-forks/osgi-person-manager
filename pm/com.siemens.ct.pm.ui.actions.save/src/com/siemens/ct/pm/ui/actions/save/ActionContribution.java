package com.siemens.ct.pm.ui.actions.save;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import org.jdesktop.application.Application;

import com.siemens.ct.pm.application.service.IActionContribution;
import com.siemens.ct.pm.application.service.ISelectionListener;

public class ActionContribution implements IActionContribution,
		ISelectionListener {

	private final List<Action> actions;

	public ActionContribution() {
		super();
		actions = new ArrayList<Action>();
		actions.add(new SavePersonAction());
	}

	@Override
	public List<Action> getActions(Application application) {
		return actions;
	}

	@Override
	public String getMenuLocation() {
		return "actions";
	}

	@Override
	public String getToolBarLocation() {
		return "actions";
	}

	@Override
	public void selectionChanged(Object selectedObject) {
		// TODO Auto-generated method stub

	}
}
