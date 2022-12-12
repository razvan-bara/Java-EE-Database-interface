package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AuthService;
import utils.AuthSessionHandler;


@WebFilter("*")
public class AuthFilter extends HttpFilter implements Filter {
	
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String path = req.getRequestURI();
		System.out.println(path);
		
	    if(path.endsWith(".css") || path.endsWith(".js")){
	        chain.doFilter(request,response);
	        return;
	     }
		
		if ( path.startsWith("/register") || path.startsWith("/login") || path.startsWith("/logout") ) {
			
				if(path.startsWith("/logout")) {
					chain.doFilter(request, response);
					return;
				}
			
				if(AuthSessionHandler.checkAuth(req)) {
					request.getRequestDispatcher("index.jsp").forward(request, response);
					return;
				} else {
					chain.doFilter(request, response);
				}

		} else {
			
			if(AuthSessionHandler.checkAuth(req)) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/pages/auth/login.jsp").forward(request, response);
				return;
				
			}
			
		}
		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
