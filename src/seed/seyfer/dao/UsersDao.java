package seed.seyfer.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passworEncoder;

	private int userId;

	public UsersDao() {
		System.out.println("Loaded" + UsersDao.class.getName());
	}

	public NamedParameterJdbcTemplate getJdbc() {
		return jdbc;
	}

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public int getLastUserId() {
		return userId;
	}

	@Transactional
	public boolean create(User user) {
		// BeanPropertySqlParameterSource parameterSource = new
		// BeanPropertySqlParameterSource(user);

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();

		parameterSource.addValue("username", user.getUsername());
		parameterSource.addValue("name", user.getName());
		parameterSource.addValue("password", passworEncoder.encode(user.getPassword()));
		parameterSource.addValue("email", user.getEmail());
		parameterSource.addValue("enabled", user.isEnabled());
		parameterSource.addValue("authority", user.getAuthority());

		if (user.getId() == 0) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbc.update(
					"insert into users (username, name, password, email, enabled, authority) "
							+ "values (:username, :name, :password, :email, :enabled, :authority)",
					parameterSource, keyHolder, new String[] { "id" });

			userId = Integer.parseInt(keyHolder.getKey().toString());
		} else {
			parameterSource.addValue("id", user.getId());
			
			jdbc.update(
					"insert into users (id, username, name, password, email, enabled, authority) "
							+ "values (:id, :username, :name, :password, :email, :enabled, :authority)",
					parameterSource);

			userId = user.getId();
		}

		return true;
	}

	public boolean exists(String username) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource("username", username);
		return jdbc.queryForObject("select count(*) from users where username=:username", paramMap, Integer.class) > 0;
	}

	public List<User> getAllUsers() {
		// return jdbc.query("select * from authorities, users where users.id =
		// authorities.user_id",
		// BeanPropertyRowMapper.newInstance(User.class));

		return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}
}
