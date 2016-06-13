package seed.seyfer.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import seed.seyfer.auth.AuthUser;
import seed.seyfer.dao.Offer;
import seed.seyfer.service.OffersService;

@Controller
public class OffersController {

	private OffersService offersService;

	// private UsersService

	public OffersService getOffersService() {
		return offersService;
	}

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	// @ExceptionHandler(DataAccessException.class)
	// public String handleDatabaseExc(DataAccessException ex) {
	// return "error";
	// }

	@RequestMapping("/offers")
	public String offers(Model model) {

		// offersService.throwTestException();

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping("/createOffer")
	public String createOffer(Model model, Principal principal) {

		Offer offer = null;
		if (principal != null) {
			String username = principal.getName();
			offer = offersService.getOffer(username);
		}

		if (offer == null) {
			offer = new Offer();
		}

		model.addAttribute("offer", offer);

		return "createOffer";
	}

	@RequestMapping(value = "/doCreateOffer", method = RequestMethod.POST)
	public String doCreateOffer(Model model, @Valid Offer offer, BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		System.out.println(new Object() {
		}.getClass().getEnclosingMethod().getName());
		System.out.println(offer);

		if (result.hasErrors()) {
			System.out.println("not validates");

			// debug
			// List<ObjectError> errors = result.getAllErrors();
			// for (ObjectError error : errors) {
			// System.out.println(error.getDefaultMessage());
			// }

			return "createOffer";
		} else {
			System.out.println("valid");
		}

		// String username = principal.getName();
		// offer.getUser().setUsername(username);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AuthUser currentUser = (AuthUser) auth.getPrincipal();

		System.out.println((AuthUser) currentUser);

		offer.getUser().setId(currentUser.getId());
		// offersService.create(offer);

		if (delete != null) {
			offersService.delete(offer);
		} else {
			offersService.saveOrUpdate(offer);
		}

		return "redirect:/offers";
	}

}
