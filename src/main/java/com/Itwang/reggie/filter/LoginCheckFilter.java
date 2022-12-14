package com.Itwang.reggie.filter;

import com.Itwang.reggie.common.BaseContext;
import com.Itwang.reggie.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        String[] urls = new String[]{
              "/employee/login",
              "/employee/logout",
              "/backend/**",
                "/front/**",
                "/demo/**"
        };
        boolean check = check(urls,requestURI);

        if (check){
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute("employee")!= null){


            Long empId = (Long) request.getSession().getAttribute("employee");

            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


    }

    /**
     * 路径匹配检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url: urls){
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
