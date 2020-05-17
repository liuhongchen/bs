package com.liuhongchen.bsgatewayzuul.filters;

import com.liuhongchen.bscommonextutils.common.RedisUtils;
import com.liuhongchen.bscommonutils.common.EmptyUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Component
public class PreFilter extends ZuulFilter {


    @Autowired
    private RedisUtils redisUtils;

    private static List<String> paths=new ArrayList<>();

    static {
        paths.add("/user/login");
        paths.add("/user/wxLogin");
        paths.add("/user/wxLoginCode");
    }

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 同一级别内部，例如，pre中、route中、post中、error中
     * 值越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器开关，这里应该根据前面过滤器传过来的sendResponseStatus来决定是否要执行，如果前面设置了false
     * ，本类中的run方法不会执行。例子参考
     * @see SecondFilter#shouldFilter()
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    private boolean pass(String path){
        return paths.contains(path);
    }


    @Override
    public Object run() throws ZuulException {

        //传递信息的一个对象，可以给后面的过滤器传递信息，或者读取前面传来的信息
        //map结构
        RequestContext context=RequestContext.getCurrentContext();

        HttpServletRequest request = context.getRequest();
        String servletPath = request.getServletPath();

        if (pass(servletPath)){
            context.setSendZuulResponse(true);
            return "pass";
        }

        String token = request.getHeader("token");

//        context.setSendZuulResponse(true);


        if (token==null|| EmptyUtils.isEmpty(redisUtils.get(token))){
            context.setSendZuulResponse(false);//给后面的过滤器发信息，这里出问题了，不要在进行路由转发了
            context.setResponseStatusCode(200);

            context.setResponseBody("{\"msg\":\"401,access without premission,please login first\"");

            return "access denied";
        }


        return "pass";//返回值是预留的，没有用
    }
}
