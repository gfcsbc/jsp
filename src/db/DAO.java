package db;

import java.util.List;
import java.util.Map;

import bean.User;

public class DAO {

	public static void main(String[] args) {
		DAO dao = new DAO();
		try {
			User user = new User();
			user.setLoginId("setLoginId");
			user.setName("setName");
			user.setPassword("setPassword");
			dao.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(int id) throws Exception {
		String sql = "delete from User where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void deleteNote(int id) throws Exception {
		String sql = "delete from note where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void updateUser(User user) throws Exception {
		String sql = "update User set name=?, userRole=? where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { user.getName(), user.getUserRole(), user.getId() });
	}

	public void saveUser(User user) throws Exception {
		String sql = "insert into User (loginId,name,password,userRole) values(?,?,?,?,?,?)";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { user.getLoginId(), user.getName(), user.getPassword(), user.getUserRole() });
	}

	public void updateUserPassword(int id, String password) throws Exception {
		String sql = "update User set password=? where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { password, id });
	}

	public void deleteContact(int id) throws Exception {
		String sql = "delete from Contact where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void deleteNews(int id) throws Exception {
		String sql = "delete from News where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void deleteGoods(int id) throws Exception {
		String sql = "delete from goods where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void deleteScore(int id) throws Exception {
		String sql = "delete from score where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void deleteLost(int id) throws Exception {
		String sql = "delete from lost where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUser() throws Exception {
		String sql = "select * from User where userRole != 'π‹¿Ì‘±'";
		DBUtil u = new DBUtil(true);
		return u.queryToBeanList(User.class, sql);
	}

	public User findUser(String username, String password) throws Exception {
		DBUtil u = new DBUtil(true);
		String sql = "select * from User where loginId=? and password=?";
		User user = (User) u.queryToBean(User.class, sql, new Object[] { username, password });
		return user;
	}

	public User findUserByLoginId(String username) throws Exception {
		DBUtil u = new DBUtil(true);
		String sql = "select * from User where loginId=?";
		User user = (User) u.queryToBean(User.class, sql, new Object[] { username });
		return user;
	}

	public User findUserById(int id) throws Exception {
		DBUtil u = new DBUtil(true);
		String sql = "select * from User where id=?";
		User user = (User) u.queryToBean(User.class, sql, new Object[] { id });
		return user;
	}

	public Object get(Class<?> clazz, Object id) throws Exception {
		DBUtil u = new DBUtil(true);
		String sql = "select * from " + clazz.getSimpleName() + " where id=?";
		Object bean = u.queryToBean(clazz, sql, new Object[] { id });
		return bean;
	}

	public List<?> findAll(Class<?> clazz) throws Exception {
		String sql = "select * from " + clazz.getSimpleName() + "";
		DBUtil u = new DBUtil(true);
		return u.queryToBeanList(clazz, sql);
	}

	public void delete(Class<?> clazz, Object id) throws Exception {
		String sql = "delete from " + clazz.getSimpleName() + " where id=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, new Object[] { id });
	}

	public void save(Class<?> clazz, Map<String, Object> ps) throws Exception {

		Object[] objs = new Object[ps.size()];
		String names = "";
		String values = "";
		int index = 0;
		for (String key : ps.keySet()) {
			names += key + ",";
			values += "?,";
			objs[index++] = ps.get(key);
		}
		names = names.substring(0, names.length() - 1);
		values = values.substring(0, values.length() - 1);
		String sql = "insert into " + clazz.getSimpleName() + " (" + names + ") values(" + values + ")";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, objs);
	}

	public void update(Class<?> clazz, Map<String, Object> ps, String idname, Object idvalue) throws Exception {

		Object[] objs = new Object[ps.size() + 1];
		String names = "";
		int index = 0;
		for (String key : ps.keySet()) {
			names += key + "=?, ";
			objs[index++] = ps.get(key);
		}
		objs[index] = idvalue;
		names = names.substring(0, names.length() - 2);
		String sql = "update " + clazz.getSimpleName() + " set " + names + " where " + idname + "=?";
		DBUtil u = new DBUtil(true);
		u.executeUpdate(sql, objs);
	}
}
