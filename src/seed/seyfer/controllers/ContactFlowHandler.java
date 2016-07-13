package seed.seyfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import seed.seyfer.dao.Message;
import seed.seyfer.dao.User;
import seed.seyfer.service.UsersService;

@Controller
public class ContactFlowHandler {
	
	@Autowired
	private UsersService usersService;
	
	public Message prepareFlowForm() {
		Message message = new Message();
		return message;
	}
	
	public Message prepareUserInMessage(Message message) {
		System.out.println("prepareUserInMessage: " + message);
		
		User user = usersService.loadByUsername(message.getUsername());
		
		message.setUser(user);
		
		return message;
	}
}
