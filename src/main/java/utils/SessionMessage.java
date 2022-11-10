package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMessage {

	public static void setSuccessMsg(HttpServletRequest request, String msg) {
		HttpSession session = request.getSession(false);
		session.setAttribute("success", msg);
	}
	
}
