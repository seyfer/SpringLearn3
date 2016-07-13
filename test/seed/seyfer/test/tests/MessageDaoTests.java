package seed.seyfer.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seed.seyfer.dao.Message;
import seed.seyfer.dao.MessagesDao;
import seed.seyfer.dao.Offer;
import seed.seyfer.dao.OffersDao;
import seed.seyfer.dao.User;
import seed.seyfer.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:seed/seyfer/config/dao-context.xml",
		"classpath:seed/seyfer/config/auth-context.xml", "classpath:seed/seyfer/config/security-context.xml",
		"classpath:seed/seyfer/config/service-context.xml", "classpath:seed/seyfer/test/config/datasource.xml", })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageDaoTests {

	@Autowired
	private MessagesDao messagesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	User user1 = new User(1, "seyfer", "Oleg", "seedseed", true, "ROLE_ADMIN", "ss@ss.ss");
	User user2 = new User(2, "seyfer2", "Oleg2", "seedseed2", true, "user", "ss2@ss.ss");
	User user3 = new User(3, "seyfer3", "Oleg3", "seedseed3", false, "user", "ss3@ss.ss");

	private Message message1 = new Message(1, "Your offer", "Your offer is shit", "Bin Mr", "bin@bin.com", user1);
	private Message message2 = new Message("Test Subject 2", "Test content 2", "Isaac Newton",
			"isaac@caveofprogramming.com", user1);
	private Message message3 = new Message("Test Subject 3", "Test content 3", "Isaac Newton",
			"isaac@caveofprogramming.com", user2);

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@After
	public void after() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);

		messagesDao.create(message1);
		messagesDao.create(message2);
		messagesDao.create(message3);

		Message retrieved = messagesDao.getMessage(message1.getId());
		assertEquals("Message1 should match retrieved updated Message", message1, retrieved);

		List<Message> messages1 = messagesDao.getMessages();
		assertEquals("Should be one offer in database.", 3, messages1.size());

		List<Message> messages = messagesDao.getMessages(user1.getUsername());
		assertEquals(2, messages.size());

		messages = messagesDao.getMessages(user2.getUsername());
		assertEquals(1, messages.size());
	}

	@Test
	public void testDelete() {
		usersDao.create(user1);
		usersDao.create(user2);

		messagesDao.saveOrUpdate(message1);
		messagesDao.saveOrUpdate(message2);
		messagesDao.saveOrUpdate(message3);

		List<Message> messages = messagesDao.getMessages(user1.getUsername());

		for (Message message : messages) {
			Message retrieved = messagesDao.getMessage(message.getId());
			assertEquals(message, retrieved);
		}

		Message toDelete = messages.get(1);

		Assert.assertNotNull("This message not deleted yet.", messagesDao.getMessage(toDelete.getId()));

		messagesDao.delete(toDelete.getId());

		Assert.assertNull("This message was deleted.", messagesDao.getMessage(toDelete.getId()));
	}
}
