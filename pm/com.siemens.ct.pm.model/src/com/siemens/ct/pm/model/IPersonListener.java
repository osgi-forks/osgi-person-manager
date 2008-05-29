package com.siemens.ct.pm.model;

import com.siemens.ct.pm.model.event.PersonEvent;

public interface IPersonListener {
	public void handleEvent(PersonEvent event);
}
