package seed.seyfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import seed.seyfer.dao.Offer;
import seed.seyfer.dao.OffersDAO;

@Service("offersService")
public class OffersService {

	private OffersDAO offersDAO;

	public OffersDAO getOffersDAO() {
		return offersDAO;
	}

	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return this.offersDAO.getOffers();
	}
}
