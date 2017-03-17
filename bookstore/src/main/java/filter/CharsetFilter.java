package filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by andy on 2017/3/17.
 */
public class CharsetFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding = config.getInitParameter("encoding");
        if(encoding==null){
            encoding="UTF-8";
        }

        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;encoding=utf-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
