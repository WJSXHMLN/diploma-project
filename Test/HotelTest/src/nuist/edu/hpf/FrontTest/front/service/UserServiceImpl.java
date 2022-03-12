package nuist.edu.hpf.FrontTest.front.service;

import nuist.edu.hpf.FrontTest.front.dao.UserDao;
import nuist.edu.hpf.FrontTest.front.dao.UserDaoImpl;
import nuist.edu.hpf.Test.bean.User;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByLoginNameAndPass(String loginname, String password) {
		return userDao.findByLoginNameAndPass(loginname,password);
	}
	@Override
	public User findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}
	@Override
	public void save(User user) {
		userDao.save(user);
	}

}
