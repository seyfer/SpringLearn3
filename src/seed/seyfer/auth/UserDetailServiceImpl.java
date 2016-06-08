package seed.seyfer.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import seed.seyfer.dao.User;
import seed.seyfer.service.UsersService;

@Component("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UsersService logInService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		AuthUser mediUser = null;
		List authorities = new ArrayList();

		if (!"".equals(username)) {
			System.out.println(username);
			
			User userProfile = logInService.loadByUsername(username);

			if (null != userProfile) {
				List<String> userRoles = new ArrayList<String>();
				userRoles.add(userProfile.getAuthority());

				if (null != userRoles && userRoles.size() > 0) {
					for (String userRole : userRoles) {
						authorities.add(new SimpleGrantedAuthority(userRole));
					}
				}

				mediUser = new AuthUser(username, userProfile.getPassword(), true, true, true, true, authorities,
						userProfile.getName(), userProfile.getId());

			} else {
				mediUser = new AuthUser(username, "NA", true, true, true, true, authorities, "", 0);
			}
		} else {
			mediUser = new AuthUser(username, "NA", true, true, true, true, authorities, "", 0);
		}

		return mediUser;
	}

	public UsersService getLogInService() {
		return logInService;
	}

	public void setLogInService(UsersService logInService) {
		this.logInService = logInService;
	}
}