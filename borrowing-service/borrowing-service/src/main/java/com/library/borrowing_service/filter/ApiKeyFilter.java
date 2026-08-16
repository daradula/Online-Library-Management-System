package com.library.borrowing_service.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ApiKeyFilter implements Filter {

    private static final String API_KEY = "library-secret-key-123";
    private static final String HEADER_NAME = "X-API-KEY";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        // Browser preflight (OPTIONS) requests eka key check nokara pass karanna
        if ("OPTIONS".equalsIgnoreCase(method)) {
            chain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.startsWith("/h2-console")) {
            chain.doFilter(request, response);
            return;
        }

        String requestApiKey = httpRequest.getHeader(HEADER_NAME);

        if (API_KEY.equals(requestApiKey)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized: Invalid or missing API Key");
        }
    }
}