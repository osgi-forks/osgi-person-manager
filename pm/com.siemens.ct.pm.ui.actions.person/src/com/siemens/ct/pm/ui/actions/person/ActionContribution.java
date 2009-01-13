package com.siemens.ct.pm.ui.actions.person;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import org.jdesktop.application.Application;

import com.siemens.ct.pm.application.service.IActionContribution;
import com.siemens.ct.pm.application.service.ISelectionListener;
import com.siemens.ct.pm.model.IPerson;
import com.siemens.ct.pm.model.IPersonManager;

public class ActionContribution implements IActionContribution, ISelectionListener {

    private final List<Action> actions;
    private final DeletePersonAction deletePersonAction;

    public ActionContribution() {
        super();
        actions = new ArrayList<Action>();
        actions.add(new AddPersonAction());

        deletePersonAction = new DeletePersonAction();
        actions.add(deletePersonAction);
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
        if (selectedObject instanceof IPerson) {
            deletePersonAction.setSelectedPerson((IPerson) selectedObject);
            deletePersonAction.setEnabled(true);
        } else {
            deletePersonAction.setSelectedPerson(null);
            deletePersonAction.setEnabled(false);
        }
    }

    public synchronized void removePersonManager() {
        deletePersonAction.setPersonManager(null);
    }

    public synchronized void setPersonManager(IPersonManager personManager) {
        deletePersonAction.setPersonManager(personManager);
    }
}
