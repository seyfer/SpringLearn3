package seed.seyfer.test.tests;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seed.seyfer.dao.User;
import seed.seyfer.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:seed/seyfer/config/dao-context.xml",
		"classpath:seed/seyfer/config/auth-context.xml", "classpath:seed/seyfer/config/security-context.xml",
		"classpath:seed/seyfer/config/service-context.xml", "classpath:seed/seyfer/test/config/datasource.xml", })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DataSource dataSource;

	User user1 = new User(1, "seyfer", "Oleg", "seedseed", true, "ROLE_ADMIN", "ss@ss.ss");
	User user2 = new User(2, "seyfer2", "Oleg2", "seedseed2", true, "user", "ss2@ss.ss");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);

		List<User> users = usersDao.getAllUsers();

		Assert.assertEquals("Must be 1", 1, users.size());
		Assert.assertEquals("Identical", user1, users.get(0));
		
		usersDao.create(user2);
		
		List<User> users2 = usersDao.getAllUsers();
		
		Assert.assertEquals("Must be 2", 2, users2.size());
	}

	@Test
	public void testUsers() {
		// Assert.assertEquals("dummy", 1, 1);

		usersDao.create(user1);

		List<User> users = usersDao.getAllUsers();

		Assert.assertEquals("Must be 1", 1, users.size());
		Assert.assertTrue("User exists", usersDao.exists(user1.getUsername()));
		Assert.assertEquals("Identical", user1, users.get(0));

		// return usersDao.getLastUserId();
	}
}
