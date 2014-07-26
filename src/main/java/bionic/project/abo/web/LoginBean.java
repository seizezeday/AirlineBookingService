package bionic.project.abo.web;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.expression.impl.ThisExpressionResolver;
import org.springframework.context.annotation.Scope;

import bionic.project.abo.dao.UserDAO;
import bionic.project.abo.entity.User;
import bionic.project.abo.enums.Role;
import bionic.project.abo.service.UserService;



@Named
@Scope("session")
public class LoginBean implements Serializable {
	
	@Inject
	private UserService userService; 
	
	private String login;
	private String password;
	private String loggedUser="";
	
	
	FacesMessage.Severity severity;
	FacesContext context = FacesContext.getCurrentInstance();
	ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");

	
	public String getLogin() {
		return login;
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
	public String getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public String doLogin(){
		
		String s = userService.doLogin(this.login, this.password);
		
//		if (user == null){
//			return null;
//		}else if (user.getRole().equals(Role.ADMIN)){
//			loggedUser = "Admin";
//			return "Admin?faces-redirect=true";
//		} else if (user.getRole().equals(Role.SUPERADMIN)){
//			loggedUser = "Superadmin";
//			return "addUser?faces-redirect=true";
//		} else if (user.getRole().equals(Role.OPERATOR)){
//			loggedUser = "Operator";
//			return "accountant?faces-redirect=true";
//	    } else if (user.getRole().equals(Role.ANALYTIC)){
//	    	loggedUser = "Analytic";
//			return "report?faces-redirect=true";
//	    }
		
		if (s == null) noSuchUserInfo();
		loggedUser = s;
		return (s + "?faces-redirect=true");
		
	}
	
	public void noSuchUserInfo() {
		String message1= bundle.getString("nouser");
		String message2= bundle.getString("nouserdet");
		
		addMessage(FacesMessage.SEVERITY_INFO, message1,
				message2);
	}
	
	
	
	public void addMessage(FacesMessage.Severity severity, String summary,
			String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	
	public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedUser = "";
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return "login?faces-redirect=true";
    }

	
	
	

}
