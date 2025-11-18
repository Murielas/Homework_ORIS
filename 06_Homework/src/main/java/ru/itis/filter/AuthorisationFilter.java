package ru.itis.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthorisationFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) request).getRequestURI();

        if (isPublicURI(requestURI) || isStaticResource(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            ((HttpServletResponse) response).sendRedirect("/login");
            return;
        }

        if (isAdminURI(requestURI)) {
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAdmin == null || !isAdmin) {
                ((HttpServletResponse) response).sendRedirect("/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicURI(String requestURI) {
        return requestURI.equals("/index")
                || requestURI.equals("/registration")
                || requestURI.equals("/login")
                || requestURI.equals("/logout")
                || requestURI.equals("/catalog-bouquets");
    }

    private boolean isAdminURI(String requestURI) {
        return requestURI.equals("/delete-product")
                || requestURI.equals("/admin-panel");
    }

    private boolean isStaticResource(String requestURI) {
        return requestURI.endsWith(".css")
                || requestURI.endsWith(".js")
                || requestURI.endsWith(".png")
                || requestURI.endsWith(".jpg")
                || requestURI.endsWith(".gif")
                || requestURI.endsWith(".ico")
                || requestURI.endsWith(".jpeg")
                || requestURI.endsWith(".mp4");
    }
}
