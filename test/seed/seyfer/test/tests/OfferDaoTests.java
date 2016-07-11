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
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	User user1 = new User(1, "seyfer", "Oleg", "seedseed", true, "ROLE_ADMIN", "ss@ss.ss");
	User user2 = new User(2, "seyfer2", "Oleg2", "seedseed2", true, "user", "ss2@ss.ss");
	User user3 = new User(3, "seyfer3", "Oleg3", "seedseed3", false, "user", "ss3@ss.ss");

	Offer offer1 = new Offer(1, user1, "user 1 test offer");
	Offer offer2 = new Offer(user2, "user 2 test offer");
	Offer offer3 = new Offer(user3, "user 3 test offer");
	Offer offer4 = new Offer(user2, "user 2 test offer");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from users");
		jdbc.execute("delete from offers");
	}

	@After
	public void after() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);

		offersDao.create(offer1);

		Offer retrieved = offersDao.getOffer(offer1.getId());
		assertEquals("offer1 should match retrieved updated offer", offer1, retrieved);

		List<Offer> offers1 = offersDao.getOffers();
		assertEquals("Should be one offer in database.", 1, offers1.size());

		offersDao.create(offer2);
		offersDao.create(offer3);

		List<Offer> offers2 = offersDao.getOffers();

		// because 1 user not enabled
		assertEquals("Should be 2 offer in database.", 2, offers2.size());
	}

	@Test
	public void testGetUsername() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);

		offersDao.create(offer1);
		offersDao.create(offer2);
		offersDao.create(offer3);
		offersDao.create(offer4);

		List<Offer> userOffers = offersDao.getOffers(user1.getUsername());
		assertEquals("Should be one offer in database.", 1, userOffers.size());

		List<Offer> userOffers1 = offersDao.getOffers(user2.getUsername());
		assertEquals("Should be one offer in database.", 2, userOffers1.size());

		List<Offer> userOffers2 = offersDao.getOffers("fake test");
		assertEquals("Should be zero offers in database.", 0, userOffers2.size());
	}

	@Test
	public void testUpdate() {
		usersDao.create(user1);

		offersDao.create(offer1);

		Offer retrieved = offersDao.getOffer(offer1.getId());

		// Get the offer with ID filled in.
		retrieved.setText("Updated offer text.");
		Assert.assertTrue("Offer update should return true", offersDao.update(retrieved));

		Offer retrievedAgain = offersDao.getOffer(retrieved.getId());

		Assert.assertNotEquals("Offer text should NOT match", offer1.getText(), retrievedAgain.getText());

		Offer offer1 = new Offer(1, user2, "i'm saved or updated");
		offersDao.saveOrUpdate(offer1);
		Offer retrievedAOU = offersDao.getOffer(offer1.getId());
		Assert.assertEquals("Offer text should NOT match", offer1.getText(), retrievedAOU.getText());
	}

	@Test
	public void testDelete() {
		usersDao.create(user1);

		offersDao.create(offer1);

		Offer retrieved = offersDao.getOffer(offer1.getId());

		offersDao.delete(retrieved.getId());
		List<Offer> empty = offersDao.getOffers();
		Assert.assertEquals("Offers list should be empty.", 0, empty.size());

		Offer retrievedDel = offersDao.getOffer(retrieved.getId());
		Assert.assertNull("Should be null", retrievedDel);
	}

}
