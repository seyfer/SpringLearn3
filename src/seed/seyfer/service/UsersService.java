package seed.seyfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import seed.seyfer.dao.Message;
import seed.seyfer.dao.MessagesDao;
import seed.seyfer.dao.User;
import seed.seyfer.dao.UsersDao;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDAO;

	@Autowired
	private MessagesDao messagesDao;

	public UsersDao getOffersDAO() {
		return usersDAO;
	}

	@Secured("ROLE_ADMIN")
	public void create(User user) {
		this.usersDAO.create(user);
	}

	public boolean exists(String username) {
		return this.usersDAO.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return this.usersDAO.getAllUsers();
	}

	public User loadByUsername(String username) {
		return this.usersDAO.loadByUsername(username);
	}

	public void sendMessage(Message message) {
		System.out.println("sendMessage: " + message);
		
		messagesDao.create(message);
	}

	public List<Message> getMessages(String username) {
		return messagesDao.getMessages(username);
	}
}
