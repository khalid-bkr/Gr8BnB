package bnb.model;


public class Guest extends User {

	public Guest(int id, String name, String userName, String password) {
		super(id, name, userName, password);
	}

	public Guest(int id) {
		super(id);
	}

	public Guest(String name, String userName, String password) {
		super(name, userName, password);
	}
	

}
