package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import models.User;

public class AuthSessionHandler {

	public static void setSessionUser(HttpServletRequest request, User user) {
		HttpSession session = request.getSession(false);
		session.setAttribute("auth_user", user);
	}
	
	public static void removeSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("auth_user");
	}
	
	public static boolean checkAuth(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		
		if( session.getAttribute("auth_user") == null ) {
			return false;
		} else {
			return true;
		}
		
		
	}

	public static void logoutUser(HttpServletRequest request) {
		
		if( checkAuth(request) ) {
			removeSessionUser(request);
		}
		
	}

	public static boolean isAuthUserAdmin(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if( checkAuth(request) ) {
			User user = (User) session.getAttribute("auth_user");
			return user.isAdmin();
		}

		return false;
	}
	
}
