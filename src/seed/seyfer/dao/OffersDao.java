package seed.seyfer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;

	public OffersDao() {
		System.out.println("Loaded" + OffersDao.class.getName());
	}

	public NamedParameterJdbcTemplate getJdbc() {
		return jdbc;
	}

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Offer getOffer(int id) {

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);

		Offer offer = jdbc.queryForObject(
				"select * from offers, users where offers.id=:id and offers.user_id = users.id", parameterSource,
				new RowMapper<Offer>() {

					public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

						User user = new User();
						user.setAuthority(rs.getString("authority"));
						user.setEmail(rs.getString("email"));
						user.setEnabled(true);
						user.setName(rs.getString("name"));
						user.setUsername(rs.getString("username"));

						Offer offer = new Offer(rs.getInt("id"), user, rs.getString("text"));

						return offer;
					}

				});

		return offer;
	}

	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);

		jdbc.update("update offers set text=:text where id=:id", parameterSource);

		return true;
	}

	public boolean create(Offer offer) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);

		jdbc.update("insert into offers (user_id, text) values (:user_id, :text)", parameterSource);

		return true;
	}

	@Transactional
	public int[] create(List<Offer> offers) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(offers.toArray());

		return jdbc.batchUpdate("insert into offers (user_id, text) values (:user_id, :text)", parameterSource);
	}

	public int delete(int id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id=:id", parameterSource);
	}

	public List<Offer> getOffers() {

		List<Offer> offers = jdbc.query(
				"select * from offers, users where offers.user_id = users.id and users.enabled=1 order by offers.id DESC",
				new RowMapper<Offer>() {

					public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

						User user = new User();
						user.setAuthority(rs.getString("authority"));
						user.setEmail(rs.getString("email"));
						user.setEnabled(true);
						user.setName(rs.getString("name"));
						user.setUsername(rs.getString("username"));

						Offer offer = new Offer(rs.getInt("id"), user, rs.getString("text"));

						return offer;
					}

				});

		return offers;
	}
}
