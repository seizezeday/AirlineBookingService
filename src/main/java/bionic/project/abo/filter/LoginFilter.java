package bionic.project.abo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bionic.project.abo.web.LoginBean;

public class LoginFilter implements Filter {
	/**
	 * Checks if user is logged in. If not it redirects to the login.xhtml page.
	 */
	@Override
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String url = request.getRequestURI();
		String contextPath = ((HttpServletRequest)request).getContextPath();
		LoginBean login = null;
		if (session != null){
			login = (LoginBean) session.getAttribute("loginBean");
		} if ((session == null || login == null)) {
				response.sendRedirect(request.getContextPath() 	+ "/login.xhtml");
				return;
		
		
		} if ((url.contains("Admin.xhtml"))  &&  "Admin".equals(login.getLoggedUser())){
			
			 chain.doFilter(req, res);
		} else {
			response.sendRedirect(request.getContextPath() 	+ "/login.xhtml");
		}
		
		
	}	
					   
			

	

	// !loginBean.getLoggedUser().equals("Admin"))

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}
