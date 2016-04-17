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
public class OffersDAO {

	private NamedParameterJdbcTemplate jdbc;

	public OffersDAO() {
		System.out.println("Loaded" + OffersDAO.class.getName());
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

		Offer offer = jdbc.queryForObject("select * from offers where id=:id", parameterSource, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Offer offer = new Offer(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("text"));

				return offer;
			}

		});

		return offer;
	}

	public int update(Offer offer) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("update offers set " + "name=:name, text=:text, email=:email where id=:id", parameterSource);
	}

	public int create(Offer offer) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(offer);

		return jdbc.update("insert into offers (name, text, email) " + "values (:name, :text, :email)",
				parameterSource);
	}

	@Transactional
	public int[] create(List<Offer> offers) {
		SqlParameterSource[] parameterSource = SqlParameterSourceUtils.createBatch(offers.toArray());

		return jdbc.batchUpdate("insert into offers (name, text, email) " + "values (:name, :text, :email)",
				parameterSource);
	}

	public int delete(int id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id=:id", parameterSource);
	}

	public List<Offer> getOffers() {

		List<Offer> offers = jdbc.query("select * from offers order by id DESC", new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {

				Offer offer = new Offer(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("text"));

				return offer;
			}

		});

		return offers;
	}
}
