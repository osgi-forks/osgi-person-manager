package com.siemens.ct.pm.model;

import java.util.List;

public interface IPersonManager {
	public List<IPerson> getPersons();

	public void deletePerson(IPerson selectedPerson);
}
