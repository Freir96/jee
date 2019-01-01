package pl.polsl;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/restricted/*")
public class AccessFilter implements Filter {
    
    @Override
    /**
     * inits
     */
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    /**
     * filters
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpSession session = ((HttpServletRequest) 
		request).getSession();

	if ("ok".equals(session.getAttribute("user"))) {
	    chain.doFilter(request, response);
	} else {
	    ((HttpServletResponse) response).sendRedirect(
		    ((HttpServletRequest) request).getContextPath()
		    + "/index.html");
	}
    }

    @Override
    /**
     * destroys
     */
    public void destroy() {

    }

}
