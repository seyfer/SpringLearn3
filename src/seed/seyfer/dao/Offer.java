package seed.seyfer.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import seed.seyfer.validation.ValidEmail;

@Entity
@Table(name = "offers")
public class Offer {

	@Id
	@Column(name = "id")
	private int id = 0;

	@Size(min = 5, max = 255, groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="text")
	private String text;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

	public Offer() {
		super();

		this.user = new User();
	}

	public Offer(User user, String text) {
		super();
		this.text = text;
		this.user = user;
	}

	public Offer(int id, User user, String text) {
		super();
		this.id = id;
		this.user = user;
		this.text = text;
	}

	public int getUser_id() {
		return user.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Offer other = (Offer) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", text=" + text + ", user=" + user + "]";
	}

}
