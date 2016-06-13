package seed.seyfer.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import seed.seyfer.dao.Offer;
import seed.seyfer.dao.User;
import seed.seyfer.service.OffersService;
import seed.seyfer.service.UsersService;

@Controller
public class IndexController {

	@Autowired
	private OffersService offersService;
	private UsersService usersService;
	private static Logger logger = LogManager.getLogger(IndexController.class);

	public UsersService getUsersService() {
		return usersService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {

		logger.info("show home page");

		List<Offer> offers = offersService.getCurrent();

		boolean hasOffer = false;
		if (principal != null) {
//			System.out.println(principal.getName());

			hasOffer = offersService.hasOffer(principal.getName());
		}

		model.addAttribute("hasOffer", hasOffer);
		model.addAttribute("offers", offers);

		// debug
		this.checkRole();

		return "home";
	}

	private boolean checkRole() {
		// get security context from thread local
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null)
			return false;

		Authentication authentication = context.getAuthentication();
		if (authentication == null)
			return false;

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			System.out.println(auth.toString());
		}

		return true;
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		// throw new AccessDeniedException("test");

		List<User> users = usersService.getAllUsers();

		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam(value = "id", required = false) String id) {

		System.out.println("id " + id);

		return "home";
	}

	@RequestMapping("/homemv")
	public ModelAndView showHomeMv() {
		ModelAndView mv = new ModelAndView("home");

		Map<String, Object> model = mv.getModel();

		model.put("name", "lol mv");

		return mv;
	}

	@RequestMapping("/homem")
	public String showHomeM(Model model) {
		model.addAttribute("name", "lol m");

		return "home";
	}
}
