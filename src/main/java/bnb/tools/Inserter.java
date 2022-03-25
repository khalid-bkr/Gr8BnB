package bnb.tools;


import java.sql.SQLException;

import bnb.dal.UserDao;
import bnb.model.User;



/**
 * main() runner, used for the app demo.
 * 
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UserDao userDao = UserDao.getInstance();
		
		User u1 = userDao.getUserByUserName("Kat15");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u1.getId(), u1.getName(), u1.getUserName());
		
//		User user = new User(436188584,"Bruce","Bruce127","password");
//		user = userDao.create(user);
		User u2 = userDao.getUserByUserName("Bruce127");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u2.getId(), u2.getName(), u2.getUserName());
		
		userDao.delete(u2);
		User u3 = userDao.getUserByUserName("Bruce127");
		System.out.format("Reading USER: id:%s n:%s un:%s \n",
		u3.getId(), u3.getName(), u3.getUserName());
	}
}
