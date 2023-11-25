package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import util.Constant;
import util.DateUtil;
import util.LsdUtils;
import util.MessageUtil;
import util.Page;
import bean.Apply;
import bean.Express;
import bean.Friend;
import bean.Message;
import bean.User;
import db.BaseDAO;
import db.DAO;

public class Control extends HttpServlet {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private List beans;
	private static final String sessionList = "SessionList";
	private User sessionUser = null;
	private DAO dao = new DAO();
	private BaseDAO hdao = new BaseDAO();

	private static Logger log = Logger.getLogger(Control.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		String act = request.getParameter("act");
		if (act.equalsIgnoreCase("login")) {
			login();
			return;
		}
		if (act.equalsIgnoreCase("logout")) {
			logout();
			return;
		}
		if (act.equalsIgnoreCase("reg")) {
			reg();
			return;
		}
		sessionUser = (User) session.getAttribute("SessionUser");
		if (sessionUser == null) {
			session.setAttribute("loginErrorMessage", "请重新登录");
			response.sendRedirect("exit.jsp");
			return;
		}

		// TODO: 跳转条件
		if ("userList".equalsIgnoreCase(act)) {
			userList();
		} else if ("userAdd".equalsIgnoreCase(act)) {
			userAdd();
		} else if ("userDel".equalsIgnoreCase(act)) {
			userDel();
		} else if ("userGet".equalsIgnoreCase(act)) {
			userGet();
		} else if ("userUpdate".equalsIgnoreCase(act)) {
			userUpdate();
			//////////////////////
		}

		else if ("updatePassword".equals(act)) {
			updatePassword();
		} else if ("passwordSelf".equals(act)) {
			passwordSelf();
		} else if ("expressList".equals(act)) {
			expressList();
		} else if ("expressAdd".equals(act)) {
			expressAdd();
		} else if ("expressDel".equals(act)) {
			expressDel();
		} else if ("expressGet".equals(act)) {
			expressGet();
		} else if ("expressUpdate".equals(act)) {
			expressUpdate();
		} else if ("recMessage".equals(act)) {
			recMessage();
		} else if ("sedMessage".equals(act)) {
			sedMessage();
		} else if ("mesageAdd".equals(act)) {
			mesageAdd();
		} else if ("friendList".equals(act)) {
			friendList();
		} else if ("applyAdd".equals(act)) {
			applyAdd();
		} else if ("applyList".equals(act)) {
			applyList();
		} else if ("checkApply".equals(act)) {
			checkApply();
		} else if ("myExpress".equals(act)) {
			myExpress();
		} else if ("checkExpress".equals(act)) {
			checkExpress();
		} else if ("friendExpress".equals(act)) {
			friendExpress();
		} else if ("messageGet".equals(act)) {
			messageGet();
		} else if ("mesageReply".equals(act)) {
			mesageReply();
		} else if ("friendDel".equals(act)) {
			friendDel();
		}
		return;
	}

	private void messageGet() throws ServletException, IOException {
		try {
			Message m = (Message) hdao.get(Message.class, Integer.valueOf(request.getParameter("uid")));
			request.setAttribute("modifybean", m);
			f("/jsp/message/reply.jsp");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private void friendExpress() throws ServletException, IOException {
		User user = getSessionUser();
		List<Express> list = null;
		try {
			list = hdao.findFriendExpress(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		f("/jsp/express/friendExpress.jsp");
		return;
	}

	private void myExpress() throws ServletException, IOException {
		User user = getSessionUser();
		List<Express> list = null;
		try {
			list = (List<Express>) hdao.findByHQL("from Express where phone=? order by id desc", user.getLoginId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		f("/jsp/express/myExpress.jsp");
		return;
	}

	private void applyList() throws ServletException, IOException {
		User user = getSessionUser();
		List<Apply> list = null;
		try {
			list = (List<Apply>) hdao.findByHQL("from Apply where toUser.id=? and checked=false", user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		f("/jsp/friend/listApply.jsp");
		return;
	}

	private void friendList() throws ServletException, IOException {
		User user = getSessionUser();
		List<Friend> list = null;
		try {
			list = (List<Friend>) hdao.findByHQL("from Friend where user1.id=? or user2.id=?", user.getId(), user.getId());
			List<User> userlist = new ArrayList<User>();
			if (list != null) {
				for (Friend f : list) {
					if (f.getUser1().getId() == user.getId()) {
						userlist.add(f.getUser2());
					} else {
						userlist.add(f.getUser1());
					}
				}
			}
			request.setAttribute("list", userlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		f("/jsp/friend/list.jsp");
		return;
	}

	private void checkExpress() throws ServletException, IOException {
		try {

			Express e = (Express) hdao.get(Express.class, Integer.valueOf(request.getParameter("uid")));
			if (e.getStatus().equals("已签收")) {
				MessageUtil.addMessage(request, "快递已经签收过了");
				f("/error.jsp");
				return;
			}
			User user = getSessionUser();
			e.setAddressee(user);
			e.setFinDate(DateUtil.getCurrentTime());
			e.setStatus("已签收");
			hdao.update(e);

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void friendDel() throws ServletException, IOException {
		try {

			int fid = Integer.valueOf(request.getParameter("uid"));
			int uid = getSessionUser().getId();
			hdao.updateByHQL("delete Friend where (user1.id=? and user2.id=?) or (user2.id=? and user1.id=?)", fid, uid, fid, uid);

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void mesageReply() throws ServletException, IOException {
		try {

			Message m = (Message) hdao.get(Message.class, Integer.valueOf(request.getParameter("id")));
			m.setRecontent(request.getParameter("recontent"));
			hdao.update(m);

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void checkApply() throws ServletException, IOException {
		try {

			hdao.checkApply(Integer.valueOf(request.getParameter("uid")));

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void applyAdd() throws ServletException, IOException {
		try {
			String phone = request.getParameter("phone");
			String content = request.getParameter("content");

			Apply a = new Apply();
			User user = hdao.getUserByPhone(phone);
			if (user == null) {
				MessageUtil.addMessage(request, "电话号码不存在");
				f("/error.jsp");
				return;
			}
			a.setToUser(user);
			a.setFromUser(getSessionUser());
			a.setAddDate(DateUtil.getCurrentTime());
			a.setContent(content);

			hdao.add(a);

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void mesageAdd() throws ServletException, IOException {
		try {
			String phone = request.getParameter("phone");
			String content = request.getParameter("content");
			String toall = request.getParameter("toAll");
			String toAdmin = request.getParameter("toAdmin");

			Message msg = new Message();
			if (StringUtils.isNotBlank(toAdmin)) {
				User user = hdao.getUserByPhone("admin");
				if (user == null) {
					MessageUtil.addMessage(request, "管理员[admin]不存在");
					f("/error.jsp");
					return;
				}
				msg.setToUser(user);
			} else {
				if (StringUtils.isBlank(toall)) {
					User user = hdao.getUserByPhone(phone);
					if (user == null) {
						MessageUtil.addMessage(request, "电话号码不存在");
						f("/error.jsp");
						return;
					}
					msg.setToUser(user);
				} else {
					msg.setAllUser(true);
				}
			}
			msg.setAddDate(DateUtil.getCurrentTime());
			msg.setContent(content);
			msg.setFromUser(getSessionUser());

			hdao.add(msg);

			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void sedMessage() throws ServletException, IOException {
		User user = getSessionUser();
		List<Message> list = hdao.findMessageSed(user);
		request.setAttribute("list", list);
		f("/jsp/message/sedMessage.jsp");
		return;
	}

	private void recMessage() throws ServletException, IOException {
		User user = getSessionUser();
		List<Message> list = hdao.findMessageRec(user);
		request.setAttribute("list", list);
		f("/jsp/message/recMessage.jsp");
		return;
	}

	private void userList() throws ServletException, IOException {
		String type = request.getParameter("name");
		request.setAttribute("type", type);
		try {
			int pageNum = 0;
			try {
				pageNum = Integer.valueOf(request.getParameter("pageNum"));
			} catch (Exception e) {
			}
			Page p = (Page) session.getAttribute(Constant.SESSION_PAGE);
			beans = dao.findAll(User.class);
			if (pageNum == 0 || p == null) {
				p = new Page();
				p.setCurrentPageNumber(1);
				p.setTotalNumber(0l);
				p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);
				p.setTotalNumber(beans.size());
				p.setList(subList(0, Constant.SESSION_PAGE_NUMBER));
			} else {
				p.setCurrentPageNumber(pageNum);
				p.setList(subList(Constant.SESSION_PAGE_NUMBER * (pageNum - 1), Constant.SESSION_PAGE_NUMBER * pageNum));
			}
			session.setAttribute(Constant.SESSION_PAGE, p);
			f("/jsp/admin/list.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void expressList() throws ServletException, IOException {
		String type = request.getParameter("status");
		request.setAttribute("type", type);
		try {
			int pageNum = 0;
			try {
				pageNum = Integer.valueOf(request.getParameter("pageNum"));
			} catch (Exception e) {
			}
			Page p = (Page) session.getAttribute(Constant.SESSION_PAGE);
			if (StringUtils.isNotBlank(type)) {
				beans = hdao.findByHQL("from Express where status=? order by id desc", type);
			} else {
				beans = hdao.findByHQL("from Express order by id desc");
			}
			if (pageNum == 0 || p == null) {
				p = new Page();
				p.setCurrentPageNumber(1);
				p.setTotalNumber(0l);
				p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);
				p.setTotalNumber(beans.size());
				p.setList(subList(0, Constant.SESSION_PAGE_NUMBER));
			} else {
				p.setCurrentPageNumber(pageNum);
				log.info(pageNum);
				p.setList(subList(Constant.SESSION_PAGE_NUMBER * (pageNum - 1), Constant.SESSION_PAGE_NUMBER * pageNum));
			}
			session.setAttribute(Constant.SESSION_PAGE, p);
			f("/jsp/express/list.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void userAdd() throws ServletException, IOException {
		Map map = new HashMap();
		try {
			map.put("loginId", request.getParameter("loginId"));
			map.put("password", LsdUtils.getMd5String(request.getParameter("password")));
			map.put("name", request.getParameter("name"));
			map.put("userRole", request.getParameter("userRole"));
			dao.save(User.class, map);
			MessageUtil.addMessage(request, "成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "操作失败,账号重复");
			f("/error.jsp");
			return;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void reg() throws ServletException, IOException {
		Map map = new HashMap();
		try {
			map.put("loginId", request.getParameter("loginId"));
			map.put("password", LsdUtils.getMd5String(request.getParameter("password")));
			map.put("name", request.getParameter("name"));
			map.put("userRole", "用户");
			dao.save(User.class, map);
			session.setAttribute("regErrorMessage", "注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("regErrorMessage", "注册失败");
		}
		response.sendRedirect("reg.jsp");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void expressAdd() throws ServletException, IOException {
		Map map = new HashMap();
		try {
			map.put("phone", request.getParameter("phone"));
			map.put("name", request.getParameter("name"));
			map.put("area", request.getParameter("area"));
			map.put("sid", request.getParameter("sid"));
			map.put("status", "未签收");

			Express e = (Express) hdao.unique("from Express where sid=?", map.get("sid"));
			if (e != null) {
				MessageUtil.addMessage(request, "操作失败,快递单号重复");
				f("/error.jsp");
				return;
			}

			dao.save(Express.class, map);
			MessageUtil.addMessage(request, "成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "操作失败");
			f("/error.jsp");
			return;
		}
	}

	private void userDel() throws ServletException, IOException {
		try {
			dao.delete(User.class, Integer.valueOf(request.getParameter("uid")));
			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void expressDel() throws ServletException, IOException {
		try {
			dao.delete(Express.class, Integer.valueOf(request.getParameter("uid")));
			MessageUtil.addRelMessage(request, "成功", "dlg_page");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void userGet() throws ServletException, IOException {
		try {
			User temp = (User) dao.get(User.class, Integer.valueOf(request.getParameter("uid")));
			request.setAttribute("bean", temp);
			f("/jsp/admin/modify.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void expressGet() throws ServletException, IOException {
		try {
			Express temp = (Express) hdao.get(Express.class, Integer.valueOf(request.getParameter("uid")));
			request.setAttribute("bean", temp);
			f("/jsp/express/modify.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void userUpdate() throws ServletException, IOException {
		Map map = new HashMap();
		try {
			map.put("loginId", request.getParameter("loginId"));
			map.put("name", request.getParameter("name"));
			map.put("userRole", request.getParameter("userRole"));
			dao.update(User.class, map, "id", Integer.valueOf(request.getParameter("id")));
			MessageUtil.addMessage(request, "成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void expressUpdate() throws ServletException, IOException {
		try {

			Express temp = (Express) hdao.get(Express.class, Integer.valueOf(request.getParameter("id")));
			if (temp.getStatus().equals("已签收")) {
				MessageUtil.addMessage(request, "快递已经签收,不能修改");
				f("/error.jsp");
				return;
			}

			temp.setPhone(request.getParameter("phone"));
			temp.setArea(request.getParameter("area"));
			temp.setName(request.getParameter("name"));

			hdao.update(temp);

			MessageUtil.addMessage(request, "成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "失败");
			f("/error.jsp");
			return;
		}
	}

	private void updatePassword() {
		try {
			String uid = request.getParameter("uid");
			String userPassword = request.getParameter("userPassword");

			dao.updateUserPassword(Integer.valueOf(uid), LsdUtils.getMd5String(userPassword));
			MessageUtil.addMessage(request, "修改密码成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "修改密码失败");
			f("/error.jsp");
			return;
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	private List subList(int start, int end) {
		if (beans == null) {
			return null;
		}
		if (start >= beans.size()) {
			return null;
		}
		if (end >= beans.size()) {
			end = beans.size();
		}
		return beans.subList(start, end);
	}

	private void logout() throws ServletException, IOException {
		session.invalidate();
		response.sendRedirect("index.jsp");
		return;
	}

	private void login() throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String reqCode = request.getParameter("checkcode");
		if (reqCode == null || !reqCode.equalsIgnoreCase(code)) {
			session.setAttribute("loginErrorMessage", "验证码错误");
			response.sendRedirect("index.jsp");
			return;
		}

		User user = null;
		try {
			password = LsdUtils.getMd5String(password);
			user = dao.findUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user != null) {
			session.setAttribute("SessionUser", user);
			response.sendRedirect("main.jsp");
			return;
		} else {
			session.setAttribute("loginErrorMessage", "用户名或者密码错误");
			response.sendRedirect("index.jsp");
			return;
		}
	}

	private User getSessionUser() {
		return (User) session.getAttribute("SessionUser");
	}

	private void f(String url) {
		try {
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void passwordSelf() throws ServletException, IOException {
		try {
			String password = request.getParameter("userPassword");
			String oldPassword = LsdUtils.getMd5String(request.getParameter("oldPassword"));

			User sessionUser = getSessionUser();
			User user = dao.findUserByLoginId(sessionUser.getLoginId());
			if (!user.getPassword().equals(oldPassword)) {
				MessageUtil.addMessage(request, "操作失败,旧密码错误");
				f("/error.jsp");
				return;
			}

			Map map = new HashMap();
			map.put("password", LsdUtils.getMd5String(password));

			dao.update(User.class, map, "id", sessionUser.getId());
			MessageUtil.addMessage(request, "成功");
			f("/success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(request, "操作失败");
			f("/error.jsp");
			return;
		}
	}

}
