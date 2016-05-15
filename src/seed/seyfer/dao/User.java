package seed.seyfer.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {
	
	@NotBlank(message="username can't be blank")
	@Size(min=3, max=16)
	@Pattern(regexp="^\\w+$", message="must contain only letters, numbers, underscore")
	private String username;
	
	@NotBlank(message="must not be blank")
	@Pattern(regexp="^\\S+$", message="must not containt spaces")
	@Size(min=8, max=20, message="password must be between 8 and 20")
	private String password;
	private boolean enabled = false;
	private String authority;
	
	@NotBlank
	@Email
	private String email;

	public User() {

	}

	public User(String username, String password, boolean enabled, String authority, String email) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authority="
				+ authority + ", email=" + email + "]";
	}
}
