package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.Apply;
import bean.Express;
import bean.Friend;
import bean.Message;
import bean.User;

public class BaseDAO {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();

		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	//	public void saveStock(Stock bean) {
	//		Session session = null;
	//		Transaction trans = null;
	//		try {
	//			session = HibernateSessionFactory.getSession();
	//			trans = session.beginTransaction();
	//
	//			int id = (Integer) session.save(bean);
	//			 
	//
	//			trans.commit();
	//
	//		} catch (RuntimeException e) {
	//			e.printStackTrace();
	//			trans.rollback();
	//			throw e;
	//		} finally {
	//			if (session != null) {
	//				session.close();
	//			}
	//		}
	//	}

	//
	//	public int getSellBackAmount(int sellid) {
	//		int total = 0;
	//
	//		Session session = null;
	//		try {
	//			session = HibernateSessionFactory.getSession();
	//			List<GoodsSellBack> list = session.createQuery("from GoodsSellBack where goodsSell.id=" + sellid).list();
	//			for (GoodsSellBack gs : list) {
	//				total += gs.getBackAmount();
	//			}
	//		} catch (RuntimeException e) {
	//			session.beginTransaction().rollback();
	//			throw e;
	//		} finally {
	//			if (session != null) {
	//				session.close();
	//			}
	//		}
	//
	//		return total;
	//	}
	//
	//	public int getSellAmount(String goodsid) {
	//		int total = 0;
	//
	//		Session session = null;
	//		try {
	//			session = HibernateSessionFactory.getSession();
	//			List<GoodsSell> list = session.createQuery("from GoodsSell where type='卖出' and goods.sid='" + goodsid + "'").list();
	//			for (GoodsSell gs : list) {
	//				total += gs.getAmount();
	//			}
	//		} catch (RuntimeException e) {
	//			session.beginTransaction().rollback();
	//			throw e;
	//		} finally {
	//			if (session != null) {
	//				session.close();
	//			}
	//		}
	//
	//		return total;
	//	}

	public void add(Object obj) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.save(obj);
			session.beginTransaction().commit();

		} catch (RuntimeException e) {
			session.beginTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void delete(Object obj) throws Exception {
		Session session = null;
		try {
			//取得session对象
			session = HibernateSessionFactory.getSession();
			//删除实体
			session.delete(obj);
			//提交事务
			session.beginTransaction().commit();

		} catch (Exception e) {
			session.beginTransaction().rollback();//事务回滚
			if (session != null) {
				session.close();
			}
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void update(Object obj) throws Exception {
		Session session = null;
		try {
			//取得session对象
			session = HibernateSessionFactory.getSession();
			//删除实体
			session.merge(obj);
			//提交事务
			session.beginTransaction().commit();

		} catch (Exception e) {
			session.beginTransaction().rollback();//事务回滚
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<?> findByHQL(String hql) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query queryObject = session.createQuery(hql);
			return queryObject.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<?> findByHQL(String hql, Object... params) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query queryObject = session.createQuery(hql);
			if (params != null) {
				int index = 0;
				for (Object p : params) {
					queryObject.setParameter(index++, p);
				}
			}
			return queryObject.list();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Object unique(String hql, Object... params) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query queryObject = session.createQuery(hql);
			if (params != null) {
				int index = 0;
				for (Object p : params) {
					queryObject.setParameter(index++, p);
				}
			}
			return queryObject.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateByHQL(String hql, Object... params) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Transaction trans = null;
		try {
			Query queryObject = session.createQuery(hql);
			trans = session.beginTransaction();
			if (params != null) {
				int index = 0;
				for (Object p : params) {
					queryObject.setParameter(index++, p);
				}
			}
			queryObject.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void delete(Class clazz, Serializable id) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		try {
			Object obj = session.load(clazz, id);
			session.delete(obj);
			session.beginTransaction().commit();
		} catch (Exception e) {
			session.beginTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Object findById(String cls, Serializable key) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Object instance = (Object) session.get(cls, key);
			return instance;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Object get(Class<?> clazz, Serializable key) throws Exception {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Object instance = (Object) session.get(clazz, key);
			return instance;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<Message> findMessageRec(User user) {
		try {
			String hql = "from Message where (toUser.id=?) or (fromUser.id !=? and allUser=true) order by id desc";
			return (List<Message>) findByHQL(hql, user.getId(), user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Message> findMessageSed(User user) {
		try {
			String hql = "from Message where fromUser.id=? order by id desc";
			return (List<Message>) findByHQL(hql, user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByPhone(String phone) {
		try {
			return (User) unique("from User where loginId=?", phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void checkApply(Integer applyid) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		try {
			Apply apply = (Apply) session.get(Apply.class, applyid);
			apply.setChecked(true);
			session.createQuery("update Apply set checked=true where fromUser.id=? and toUser.id=?").setParameter(0, apply.getFromUser().getId())
					.setParameter(1, apply.getToUser().getId()).executeUpdate();

			Friend f = new Friend();
			f.setChecked(true);
			f.setUser1(apply.getFromUser());
			f.setUser2(apply.getToUser());

			session.save(f);

			session.beginTransaction().commit();
		} catch (Exception e) {
			session.beginTransaction().rollback();
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Express> findFriendExpress(int id) {
		List<Express> list = new ArrayList<Express>();

		try {
			String hql = "from Friend where user1.id=? or user2.id=?";
			List<Friend> flist = (List<Friend>) findByHQL(hql, id, id);
			String ids = "";

			for (Friend f : flist) {
				if (f.getUser1().getId() == id) {
					ids += "'" + f.getUser2().getLoginId() + "',";
				} else {
					ids += "'" + f.getUser1().getLoginId() + "',";
				}
			}
			if (StringUtils.isNotBlank(ids)) {
				ids = ids.substring(0, ids.length() - 1);
				list = (List<Express>) findByHQL("from Express where status='δ???' and phone in(" + ids + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}