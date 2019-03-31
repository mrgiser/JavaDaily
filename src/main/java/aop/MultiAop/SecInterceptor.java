package aop.MultiAop;

import java.util.Arrays;

/**
 * 描述:
 * SecInterceptor
 *
 * @Author he
 * @Create 2019-03-01 10:29 AM
 */
public class SecInterceptor implements Interceptor {


    @Override
    public void beforeIntercept(Object... args) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName()+" "+ Arrays.toString(args));
    }

    @Override
    public void afterIntercept(Object result) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName());
    }
}