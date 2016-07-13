package seed.seyfer.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

	// private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	public MessagesDao() {
		System.out.println("Loaded" + MessagesDao.class.getName());
	}

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

	public Message getMessage(int id) {

		// MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		// parameterSource.addValue("id", id);
		//
		// Offer offer = jdbc.queryForObject(
		// "select * from offers, users where offers.id=:id and offers.user_id =
		// users.id and users.enabled=1",
		// parameterSource, new OfferRowMapper());

		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		Message offer = (Message) crit.uniqueResult();

		return offer;
	}

	public void saveOrUpdate(Message message) {
		session().saveOrUpdate(message);
	}

	public boolean update(Message message) {
		// BeanPropertySqlParameterSource parameterSource = new
		// BeanPropertySqlParameterSource(offer);
		//
		// jdbc.update("update offers set text=:text where id=:id",
		// parameterSource);

		session().update(message);

		return true;
	}

	public boolean create(Message message) {
		// BeanPropertySqlParameterSource parameterSource = new
		// BeanPropertySqlParameterSource(offer);
		//
		// jdbc.update("insert into offers (user_id, text) values (:user_id,
		// :text)", parameterSource);

		session().save(message);

		return true;
	}

	// @Transactional
	// public int[] create(List<Offer> offers) {
	// SqlParameterSource[] parameterSource =
	// SqlParameterSourceUtils.createBatch(offers.toArray());
	//
	// return jdbc.batchUpdate("insert into offers (user_id, text) values
	// (:user_id, :text)", parameterSource);
	// }

	public int delete(int id) {
		// MapSqlParameterSource parameterSource = new
		// MapSqlParameterSource("id", id);
		// return jdbc.update("delete from offers where id=:id",
		// parameterSource);

		// session().delete(object);

		Query query = session().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {

		// List<Offer> offers = jdbc.query(
		// "select *, offers.id as id from offers, users where offers.user_id =
		// users.id and "
		// + "users.enabled=1 order by offers.id DESC",
		// new OfferRowMapper());
		//
		// return offers;

		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));

		return (List<Message>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {

		// List<Offer> offers = jdbc.query(
		// "select * from offers, users where offers.user_id = users.id and
		// users.enabled=1 and users.username=:username "
		// + "order by offers.id DESC",
		// new MapSqlParameterSource("username", username), new
		// OfferRowMapper());
		//
		// return offers;

		Criteria crit = session().createCriteria(Message.class);
		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true))
				.add(Restrictions.eq("u.username", username));

		return (List<Message>) crit.list();
	}
}
