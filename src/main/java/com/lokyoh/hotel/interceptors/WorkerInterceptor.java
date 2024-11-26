package com.lokyoh.hotel.interceptors;

import com.lokyoh.hotel.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class WorkerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (claims.get("type").equals("worker")||claims.get("type").equals("admin")) return true;
        response.setStatus(403);
        return false;
    }
}
