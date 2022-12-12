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

import utils.AuthSessionHandler;


@WebFilter(urlPatterns = {
		"/studenti/delete",
		"/profesori/delete",
		"/functii/delete",
		"/proiecte_cercetare/delete",
		"/catedre/delete",
		"/utilizatori"
		})
public class RoleFilter extends HttpFilter implements Filter {
       

    public RoleFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String path = req.getRequestURI();
		
		if( AuthSessionHandler.isAuthUserAdmin(req) ) {
			chain.doFilter(request, response);
		} else {
			request.setAttribute("error", "Nu ai access la aceasta operatie");
		    String[] url = path.split("/");
			request.getRequestDispatcher(url[0]).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
