package com.siemens.ct.pm.ui.actions.vcard;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Action;

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
	public List<javax.swing.Action> getActions() {
		if (actions == null) {
			actions = new ArrayList<javax.swing.Action>();

			actions.add(new AddVCardAction());
			actions.add(new EditVCardAction());
			actions.add(new RemoveVCardAction());

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
