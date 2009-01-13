package com.siemens.ct.pm.ui.actions.vcard;

import java.util.ArrayList;
import java.util.List;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;

import com.siemens.ct.pm.application.service.IActionContribution;

public class Actions implements IActionContribution {

    List<javax.swing.Action> actions;

    public @Action
    void addVCard() {
        System.out.println("VCard added...");
    }

    public @Action
    void deleteVCard() {
        System.out.println("VCard deleted...");
    }

    public @Action
    void editVCard() {
        System.out.println("VCard edited...");
    }

    @Override
    public List<javax.swing.Action> getActions(Application application) {
        if (actions == null) {
            actions = new ArrayList<javax.swing.Action>();

            ApplicationContext context = application.getContext();
            ResourceMap resourceMap = context.getResourceMap(this.getClass());
            ApplicationActionMap applicationActionMap = new ApplicationActionMap(context, this
                    .getClass(), this, resourceMap);
            for (Object key : applicationActionMap.allKeys()) {
                javax.swing.Action action = applicationActionMap.get(key.toString());
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
