package com.liuhongchen.bsuserconsumer.interceptor;

import com.alibaba.fastjson.JSON;
import com.liuhongchen.bscommondto.common.DtoUtil;
import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");

        if (EmptyUtils.isEmpty(token)||EmptyUtils.isEmpty(redisUtils.get(token))){

//            response.setStatus(401);
            response.getWriter().write(JSON.toJSONString(DtoUtil.returnFail("token验证失败","0011")));
            return false;
        }


        System.out.println(token);
        return true;
    }
}
