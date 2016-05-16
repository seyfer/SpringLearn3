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
	public int create(Offer offer) {
		return this.offersDAO.create(offer);
	}

	public void throwTestException() {
		// TODO Auto-generated method stub
		offersDAO.getOffer(9999);
	}
}
