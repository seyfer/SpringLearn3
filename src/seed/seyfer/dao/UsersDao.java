package seed.seyfer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passworEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	private int userId;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	// public NamedParameterJdbcTemplate getJdbc() {
	// return jdbc;
	// }

	// @Autowired
	// public void setJdbc(DataSource jdbc) {
	// this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	// }

	public int getLastUserId() {
		return userId;
	}
	
	public UsersDao() {
		System.out.println("Loaded" + UsersDao.class.getName());
	}

	@Transactional
	public void create(User user) {
		// MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		//
		// parameterSource.addValue("username", user.getUsername());
		// parameterSource.addValue("name", user.getName());
		// parameterSource.addValue("password",
		// passworEncoder.encode(user.getPassword()));
		// parameterSource.addValue("email", user.getEmail());
		// parameterSource.addValue("enabled", user.isEnabled());
		// parameterSource.addValue("authority", user.getAuthority());
		//
		// if (user.getId() == 0) {
		// KeyHolder keyHolder = new GeneratedKeyHolder();
		// jdbc.update(
		// "insert into users (username, name, password, email, enabled,
		// authority) "
		// + "values (:username, :name, :password, :email, :enabled,
		// :authority)",
		// parameterSource, keyHolder, new String[] { "id" });
		//
		// userId = Integer.parseInt(keyHolder.getKey().toString());
		// } else {
		// parameterSource.addValue("id", user.getId());
		//
		// jdbc.update(
		// "insert into users (id, username, name, password, email, enabled,
		// authority) "
		// + "values (:id, :username, :name, :password, :email, :enabled,
		// :authority)",
		// parameterSource);
		//
		// userId = user.getId();
		// }

		user.setPassword(passworEncoder.encode(user.getPassword()));

		session().save(user);

		userId = user.getId();
	}

	public boolean exists(String username) {
		// MapSqlParameterSource paramMap = new
		// MapSqlParameterSource("username", username);
		// return jdbc.queryForObject("select count(*) from users where
		// username=:username", paramMap, Integer.class) > 0;

		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));

		return (User) crit.uniqueResult() != null;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// with separated authorities
		// return jdbc.query("select * from authorities, users where users.id =
		// authorities.user_id",
		// BeanPropertyRowMapper.newInstance(User.class));

		// last working pure sql
		// return jdbc.query("select * from users",
		// BeanPropertyRowMapper.newInstance(User.class));

		return session().createQuery("from User").list();
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username) {
		// MapSqlParameterSource paramMap = new
		// MapSqlParameterSource("username", username);
		// User user = jdbc.queryForObject("select * from users where
		// username=:username", paramMap,
		// new RowMapper<User>() {
		//
		// public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		//
		// User user = new User();
		// user.setAuthority(rs.getString("authority"));
		// user.setEmail(rs.getString("email"));
		// user.setEnabled(true);
		// user.setName(rs.getString("name"));
		// user.setUsername(rs.getString("username"));
		// user.setId(rs.getInt("id"));
		// user.setPassword(rs.getString("password"));
		//
		// return user;
		// }
		//
		// });

		// System.out.println(user); System.exit(0);

		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));

		User user = (User) crit.uniqueResult();

		return user;
	}
}
