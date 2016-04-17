package seed.seyfer.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import seed.seyfer.dao.Offer;
import seed.seyfer.service.OffersService;

@Controller
public class OffersController {

	private OffersService offersService;

	public OffersService getOffersService() {
		return offersService;
	}

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping("/offers")
	public String offers(Model model) {

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping("/createOffer")
	public String createOffer(Model model) {
		return "createOffer";
	}

}
