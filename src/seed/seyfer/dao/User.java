package seed.seyfer.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 4097239028750268202L;

	@Id
	@Column(name = "id")
	private int id = 0;

	@NotBlank(groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Size(min = 3, max = 16, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Pattern(regexp = "^\\w+$", groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String username;

	@NotBlank(groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Pattern(regexp = "^\\S+$", groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Size(min = 8, max = 20, message = "password must be between 8 and 20", groups = { FormValidationGroup.class })
	private String password;

	private boolean enabled = false;
	private String authority;

	@NotBlank(groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Size(min = 1, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String name;

	@NotBlank(groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Email(groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String email;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Offer> offers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Message> messages;

	public User() {

	}

	public User(String username, String name, String password, boolean enabled, String authority, String email) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public User(int id, String username, String name, String password, boolean enabled, String authority,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", enabled=" + enabled + ", authority=" + authority + ", name=" + name
				+ ", email=" + email + "]";
	}

}
