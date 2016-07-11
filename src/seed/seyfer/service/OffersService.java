package seed.seyfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import seed.seyfer.dao.Offer;
import seed.seyfer.dao.OffersDao;

@Service("offersService")
public class OffersService {

	private OffersDao offersDAO;

	public OffersDao getOffersDAO() {
		return offersDAO;
	}

	@Autowired
	public void setOffersDAO(OffersDao offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return this.offersDAO.getOffers();
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public boolean create(Offer offer) {
		return this.offersDAO.create(offer);
	}

	public void throwTestException() {
		// TODO Auto-generated method stub
		offersDAO.getOffer(9999);
	}

	public boolean hasOffer(String username) {
		if (username == null) {
			return false;
		}

		List<Offer> offers = offersDAO.getOffers(username);

		if (offers.size() == 0) {
			return false;
		}

		return true;
	}

	public Offer getOffer(String username) {

		if (username == null) {
			return null;
		}

		List<Offer> offers = offersDAO.getOffers(username);

		if (offers.size() == 0) {
			return null;
		}

		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {
//		System.out.println(offer.getId());
//
//		if (offer.getId() != 0) {
//			offersDAO.update(offer);
//		} else {
//			offersDAO.create(offer);
//		}
		
		offersDAO.saveOrUpdate(offer);
	}

	public int delete(Offer offer) {
		return offersDAO.delete(offer.getId());
	}
}
