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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;

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

	@Transactional
	public int create(User user) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(
				"insert into users (username, password, email, enabled) "
						+ "values (:username, :password, :email, :enabled)",
				parameterSource, keyHolder, new String[] { "id" });

		String userId = keyHolder.getKey().toString();
		MapSqlParameterSource parameterSourceAuthority = new MapSqlParameterSource();
		parameterSourceAuthority.addValue("user_id", userId);
		parameterSourceAuthority.addValue("authority", parameterSource.getValue("authority"));

		return jdbc.update("insert into authorities (user_id, authority) " + "values (:user_id, :authority)",
				parameterSourceAuthority);
	}

	public boolean exists(String username) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource("username", username);
		return jdbc.queryForObject("select count(*) from users where username=:username", paramMap, Integer.class) > 0;
	}

	public List<User> getAllUsers() {
		return jdbc.query("select * from authorities, users where users.id = authorities.user_id",
				BeanPropertyRowMapper.newInstance(User.class));
	}
}
