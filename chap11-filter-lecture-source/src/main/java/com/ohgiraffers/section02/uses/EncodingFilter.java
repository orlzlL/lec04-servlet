package com.ohgiraffers.section02.uses;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/member/*")
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        /* 설명. 우린 톰캣 10 버전인데 톰캣 10 버전은 기본 인코딩 설정이 UTF-8이라 안 해줘도 된다. */
        /* 설명. 요청이 POST 요청일 때는 UTF-8로 인코딩 설정을 사전 처리 해 주고 이후 필터나 서블릿으로 넘길 수 있다. */
        if("POST".equals(httpRequest.getMethod())){     // 리터럴 값을 앞에 두면 null pointer exception 방지 가능 (request는 없을 수도 있으므로)
            httpRequest.setCharacterEncoding("UTF-8");
        }

        chain.doFilter(httpRequest, response);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
