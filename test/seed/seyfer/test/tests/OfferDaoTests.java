package seed.seyfer.test.tests;

import static org.junit.Assert.assertEquals;

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

import seed.seyfer.dao.Offer;
import seed.seyfer.dao.OffersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:seed/seyfer/config/dao-context.xml",
		"classpath:seed/seyfer/config/security-context.xml", "classpath:seed/seyfer/test/config/datasource.xml", })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
	}

	@Test
	public void testCreateOffer() {

		Offer offer = new Offer("johnwpurcell", "john@caveofprogramming.com", "This is a test offer.");

		Assert.assertTrue("Offer creation should return true", offersDao.create(offer));

		List<Offer> offers = offersDao.getOffers();

		assertEquals("Should be one offer in database.", 1, offers.size());

		assertEquals("Retrieved offer should match created offer.", offer, offers.get(0));

		// Get the offer with ID filled in.
		offer = offers.get(0);

		offer.setText("Updated offer text.");
		Assert.assertTrue("Offer update should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());

		assertEquals("Updated offer should match retrieved updated offer", offer, updated);

		offersDao.delete(offer.getId());

		List<Offer> empty = offersDao.getOffers();

		assertEquals("Offers lists should be empty.", 0, empty.size());
	}

}
