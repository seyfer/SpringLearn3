package seed.seyfer.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String showHome(HttpSession session) {

		session.setAttribute("name", "lol");

		return "home";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam(value="id", required=false) String id) {
		
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
