package seed.seyfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import seed.seyfer.dao.User;
import seed.seyfer.dao.UsersDao;

@Service("usersService")
public class UsersService {

	private UsersDao usersDAO;

	public UsersDao getOffersDAO() {
		return usersDAO;
	}

	@Autowired
	public void setOffersDAO(UsersDao usersDAO) {
		this.usersDAO = usersDAO;
	}

	@Secured("ROLE_ADMIN")
	public boolean create(User user) {
		return this.usersDAO.create(user);
	}

	public boolean exists(String username) {
		return this.usersDAO.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return this.usersDAO.getAllUsers();
	}
}
