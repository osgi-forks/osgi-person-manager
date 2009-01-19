package com.siemens.ct.pm.ui.actions.vcard;

import java.util.ArrayList;
import java.util.List;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siemens.ct.pm.application.service.IActionContribution;

public class Actions implements IActionContribution {

	private List<javax.swing.Action> actions;
	private final Logger logger = LoggerFactory.getLogger(Actions.class);

	public @Action
	void addVCard() {
		logger.info("VCard added...");
	}

	public @Action
	void deleteVCard() {
		logger.info("VCard deleted...");
	}

	public @Action
	void editVCard() {
		logger.info("VCard edited...");
	}

	@Override
	public List<javax.swing.Action> getActions(Application application) {
		if (actions == null) {
			actions = new ArrayList<javax.swing.Action>();

			ApplicationContext context = application.getContext();
			ResourceMap resourceMap = context.getResourceMap(this.getClass());
			ApplicationActionMap applicationActionMap = new ApplicationActionMap(
					context, this.getClass(), this, resourceMap);
			for (Object key : applicationActionMap.allKeys()) {
				javax.swing.Action action = applicationActionMap.get(key
						.toString());
				actions.add(action);
			}
		}
		return actions;
	}

	@Override
	public String getMenuLocation() {
		return "action";
	}

	@Override
	public String getToolBarLocation() {
		return "action";
	}
}
