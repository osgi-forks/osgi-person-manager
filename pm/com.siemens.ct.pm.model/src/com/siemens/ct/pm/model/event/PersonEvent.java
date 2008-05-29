package com.siemens.ct.pm.model.event;

import java.util.EventObject;

import com.siemens.ct.pm.model.IPerson;

public class PersonEvent extends EventObject {

	private static final long serialVersionUID = 5641252708254799778L;

	private final IPerson person;

	public static enum Type {
		DELETE, CREATE, UPDATE
	};

	private final Type type;

	public PersonEvent(Object source, Type type, IPerson person) {
		super(source);
		this.type = type;
		this.person = person;
	}

	public synchronized Type getType() {
		return type;
	}

	public synchronized IPerson getPerson() {
		return person;
	}
}
