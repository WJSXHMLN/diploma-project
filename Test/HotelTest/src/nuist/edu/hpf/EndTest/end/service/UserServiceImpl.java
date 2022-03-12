package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.EndTest.end.dao.UserDao;
import nuist.edu.hpf.EndTest.end.dao.UserDaoImpl;
import nuist.edu.hpf.Test.bean.User;

public class UserServiceImpl implements UserService{
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User findByLoginNameAndPass(String loginname, String password) {
		return userDao.findByLoginNameAndPass(loginname,password);
	}
	@Override
	public void save(User user) {
		userDao.save(user);
	}
	@Override
	public List<User> find(String searchType, String keyword, String disabled) {
		StringBuffer  sql = new StringBuffer("SELECT user1.*,user2.*,user2.LOGIN_NAME user2Name FROM tb_user user1 left join tb_user  user2 "
				+ " on user1.create_userId = user2.id"
				+ " WHERE 1=1 ");
		
		if(keyword != null && !keyword.equals("")) {
			//说明用户是点击搜索按钮进行搜索
			if("name".equals(searchType)) {
				sql.append(" and user1.LOGIN_NAME like '%"+keyword.trim()+"%'");
			}else if("phone".equals(searchType)) {
				sql.append(" and user1.phone = '"+keyword.trim()+"'");
			}else if("email".equals(searchType)) {
				sql.append(" and user1.email ='"+keyword.trim()+"'");
			}
		}
		
		if(disabled != null && !disabled.equals("")) {
			sql.append(" and user1.disabled ="+disabled);
		}
		
		System.out.println("sql:"+sql.toString());
		
		return userDao.find(sql.toString());
	}
	@Override
	public User findById(int id) {
		return userDao.findById(id);
	}
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	@Override
	public User findByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

}
