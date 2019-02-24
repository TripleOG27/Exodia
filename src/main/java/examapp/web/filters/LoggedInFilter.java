package examapp.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter({"/login","/register","/"
            ,"/faces/views/login.xhtml","/faces/views/index.xhtml"})
public class LoggedInFilter implements Filter {
    //Checks whether a logged in user is trying to access a page he should not.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(req.getSession().getAttribute("logged-in") != null){
            resp.sendRedirect("/faces/views/home.xhtml");
        }else {
            filterChain.doFilter(req,resp);
        }
    }
}
