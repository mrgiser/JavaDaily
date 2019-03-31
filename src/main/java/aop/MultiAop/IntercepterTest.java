package aop.MultiAop;

import aop.UserServiceImpl;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * @author PengRong
 * @package org.vincent.aop.intercepter
 * @date 2019/1/10 - 22:30
 * @ProjectName JavaAopLearning
 * @Description: 拦截器栈测试
 */
public class IntercepterTest {
    @Test
    public void intercepterTest() {
        /**
         * 构建拦截器栈, 每个切面add 到List的属性影响 拦截器 栈执行先后顺序
         */
        Interceptor interceptor1 = new FirstInterceptor();
        Interceptor interceptor2 = new SecInterceptor();
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(interceptor1);
        interceptors.add(interceptor2);

        /**
         * 代理类构造工具方法
         */
        UserServiceImpl userService = (UserServiceImpl) InterceptorStackProxyGenerator.generatorCglibProxy(UserServiceImpl.class, interceptors);
        /**
         * 调用业务方法
         */
        userService.saveUser("asfdas", "12121212");

    }
}