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

package com.siemens.ct.pm.model.minimal;

import java.util.ArrayList;
import java.util.List;

import com.siemens.ct.pm.model.IPerson;
import com.siemens.ct.pm.model.IPersonListener;
import com.siemens.ct.pm.model.IPersonManager;
import com.siemens.ct.pm.model.event.PersonEvent;

public class PersonManager implements IPersonManager {

    private final List<IPerson> persons;

    private final List<IPersonListener> personListeners;

    public PersonManager() {
        personListeners = new ArrayList<IPersonListener>();

        persons = new ArrayList<IPerson>();
        persons.add(new Person("Kai", "Tödter", "Siemens AG"));
    }

    @Override
    public List<IPerson> getPersons() {
        return persons;
    }

    @Override
    public void deletePerson(IPerson selectedPerson) {
        persons.remove(selectedPerson);
        PersonEvent personEvent = new PersonEvent(this, PersonEvent.Type.DELETE, selectedPerson);
        for (IPersonListener personlistener : personListeners) {
            personlistener.handleEvent(personEvent);
        }
    }

    public synchronized void addPersonListener(IPersonListener personListener) {
        System.out.println("Minimal PersonManager.addPersonListener(): " + personListener);
        personListeners.add(personListener);
    }

    public synchronized void removePersonListener(IPersonListener personListener) {
        System.out.println("Minimal PersonManager.removePersonListener(): " + personListener);
        personListeners.remove(personListener);
    }
}
