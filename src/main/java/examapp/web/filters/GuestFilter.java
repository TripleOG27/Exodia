package examapp.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/details","/logout","/home","/print","/schedule",
        "/faces/views/schedule.xhtml","/faces/views/print.xhtml","/faces/views/home.xhtml","/faces/views/details.xhtml"})
public class GuestFilter implements Filter {
    //Checks whether a guest is trying to access a page he should not.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(req.getSession().getAttribute("logged-in") == null){
            resp.sendRedirect("/");
        }else {
            filterChain.doFilter(req,resp);
        }
    }
}
