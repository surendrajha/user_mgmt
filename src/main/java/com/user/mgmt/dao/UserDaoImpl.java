package com.user.mgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.user.mgmt.domain.Users;

@Repository
public class UserDaoImpl implements UserDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Users> getUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "SELECT * FROM users order by firstName";
		List<Users> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
		return result;
	}

	@Override
	public Users getUser(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		String sql = "SELECT * FROM users WHERE id=:id";
		Users result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		return result;

	}

	@Override
	public void addOrUpdateUser(Users user) {

		String inserSql = "INSERT INTO Users (address, email, firstName, lastName, phone, sex) VALUES (:address, :email, :firstName, :lastName, :phone, :sex)";
		String updateSql = "UPDATE Users SET address=:address, email=:email, firstName=:firstName, lastName=:lastName, phone=:phone, sex=:sex WHERE id = :id";
		Map params = new HashMap();
		String sql = inserSql;
		if (user.getId() != null && user.getId() > 0) {
			params.put("id", user.getId());
			sql = updateSql;
		}
		params.put("address", user.getAddress());
		params.put("email", user.getEmail());
		params.put("firstName", user.getFirstName());
		params.put("lastName", user.getLastName());
		params.put("phone", user.getPhone());
		params.put("sex", user.getSex());

		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public void deleteUser(Long id) {
		String SQL = "DELETE FROM Users WHERE id = :id";
		Map params = new HashMap();
		params.put("id", id);
		namedParameterJdbcTemplate.update(SQL, params);
	}

	private static final class UserMapper implements RowMapper<Users> {

		public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
			Users user = new Users();
			user.setId(rs.getLong("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setSex(rs.getString("sex"));
			user.setEmail(rs.getString("email"));
			user.setPhone(Long.valueOf(rs.getLong("phone")));
			user.setAddress(rs.getString("address"));
			return user;
		}
	}

}