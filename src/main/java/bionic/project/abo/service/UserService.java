package bionic.project.abo.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import bionic.project.abo.dao.UserDAO;
import bionic.project.abo.entity.User;
import bionic.project.abo.enums.Role;


@Named
public class UserService {
	
	@Inject
	private UserDAO userDao;
	
	private User user;
	
	@Transactional
	public void createUser(User user){
		userDao.create(user);
	}
	
	@Transactional
	public String doLogin(String login, String password){
		List<User> user = userDao.findUser(login, password);
		if (user.isEmpty()){
			return null;
		}else if (user.get(0).getRole().equals(Role.ADMIN)){
			return "Admin";
		} else if (user.get(0).getRole().equals(Role.SUPERADMIN)){
			return "so";
		} else if (user.get(0).getRole().equals(Role.OPERATOR)){
			return "accountant";
	    } else if (user.get(0).getRole().equals(Role.ANALYTIC)){
	    	return "report";
	    }
		
		return null;
		
		
	}
	
	public List<User> showAllUsers(){
		return userDao.showAllUsers();
		
	}
	
//	public void removeUser(User user){
//		userDao.delete(id);
//	}

}
