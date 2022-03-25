package bnb.model;

public class User {
	
	protected int id;
	protected String name;
	protected String userName;
	protected String password;
	
	
	public User(int id, String name, String userName, String password) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
	}
	
	public User(String name, String userName, String password) {
		this.name = name;
		this.userName = userName;
		this.password = password;
	
	}
	
	public User(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
