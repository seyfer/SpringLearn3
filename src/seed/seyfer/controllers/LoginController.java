package seed.seyfer.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import seed.seyfer.dao.Offer;
import seed.seyfer.dao.User;
import seed.seyfer.service.OffersService;
import seed.seyfer.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;

	public UsersService getUsersService() {
		return usersService;
	}

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/newAccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());

		return "newAccount";
	}

	@RequestMapping(value = "/doCreateAccount", method = RequestMethod.POST)
	public String doCreateAccount(@Valid User user, BindingResult result) {
		// debug
		System.out.println(new Object() {
		}.getClass().getEnclosingMethod().getName());
		System.out.println(user);

		if (result.hasErrors()) {
			System.out.println("not validates");

			// debug
			// List<ObjectError> errors = result.getAllErrors();
			// for (ObjectError error : errors) {
			// System.out.println(error.getDefaultMessage());
			// }

			return "newAccount";
		} else {
			System.out.println("valid");
		}

		user.setAuthority("user");
		user.setEnabled(true);
		
		if (usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newAccount";
		}
		
		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			System.out.println(e.getClass());
			
			result.rejectValue("username", "DuplicateKey.user.username", e.getMostSpecificCause().getMessage());
			return "newAccount";
		}

		return "accountCreated";
	}
}
