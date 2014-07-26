package bionic.project.abo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;






import org.springframework.context.annotation.Scope;

import bionic.project.abo.enums.Role;
import bionic.project.abo.enums.Status;
import bionic.project.abo.entity.Flight;
import bionic.project.abo.entity.User;
import bionic.project.abo.service.UserService;



@Named
@Scope("session")
public class UserBean implements Serializable {
	
	@Inject
	private UserService userService;
	
	public UserBean(){}
	
	private String login;
	private String password;
	private String name;
	private Role[] roles =  Role.values();
	private Role role;
	private Status[] statuses = Status.values();
	private Status status;
	private Date registrationDate;
	
	private List<User> users;
	private int usersQuantity;
	
	private List<User> filteredUsers;
	
	
	
	
	
	
	
	
	
	
	
	
	public List<User> getFilteredUsers() {
		return filteredUsers;
	}


	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public int getUsersQuantity() {
		return usersQuantity;
	}


	public void setUsersQuantity(int usersQuantity) {
		this.usersQuantity = usersQuantity;
	}


	public Role[] getRoles() {
		return roles;
	}


	public void setRoles(Role[] roles) {
		this.roles = roles;
	}


	public Status[] getStatuses() {
		return statuses;
	}


	public void setStatuses(Status[] statuses) {
		this.statuses = statuses;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	

	


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public String getLogin() {
		return login;
	}
	
	
	


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public String doLogin(){
//		return loginService.doLogin(this.login, this.password);
//		//return "Admin";
//		
//	}
	
	public String createUser(){
		User user = new User();
		user.setLogin(this.login);
		user.setName(this.name);
		user.setPassword(this.password);
		user.setRole(this.role);
		user.setStatus(this.status);
		userService.createUser(user);
		updateUsersOnAdd(user);
		return "so?faces-redirect=true";
		
	}
	
	public void updateUsersOnAdd(User user){
		if (users == null){
			users = new ArrayList<User>();
			users.add(user);
			
		} else {
			users.add(user);
		}
		
	}
	
	public List<User> showAllUsers(){
		List<User> users = userService.showAllUsers();
		this.users = users;

		usersQuantity = users.size();
		//System.out.println(amount);

		return null;
	}
	
	public void removeUser(User user){
		
	}
	

}
