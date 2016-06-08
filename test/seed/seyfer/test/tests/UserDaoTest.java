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
@ContextConfiguration(locations = { 
		"classpath:seed/seyfer/config/dao-context.xml",
		"classpath:seed/seyfer/config/auth-context.xml",
		"classpath:seed/seyfer/config/security-context.xml", 
		"classpath:seed/seyfer/config/service-context.xml", 
		"classpath:seed/seyfer/test/config/datasource.xml", 
		})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from users");
//		jdbc.execute("delete from authorities");
	}

	@Test
	public void testCreateUser() {
		Assert.assertEquals("dummy", 1, 1);

		User user = new User("seyfer", "seyfer", "seedseed", true, "user", "ss@ss.ss");
		user.setId(1);
		boolean userCreationResult = usersDao.create(user);

		Assert.assertTrue("User creation true", userCreationResult);
		
		List<User> users = usersDao.getAllUsers();
		
		Assert.assertEquals("Must be 1", 1, users.size());
		
		Assert.assertTrue("User exists", usersDao.exists(user.getUsername()));
		
		Assert.assertEquals("Identical", user, users.get(0));
		
//		return usersDao.getLastUserId();
	}
}
