package aop.MultiAop;

/**
 * 描述:
 * FirstInterceptor
 *
 * @Author he
 * @Create 2019-03-01 10:28 AM
 */
public class FirstInterceptor implements Interceptor {


    @Override
    public void beforeIntercept(Object... args) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName());
    }

    @Override
    public void afterIntercept(Object result) throws InterceptorException {
        System.out.println("ClassName: "+this.getClass().getSimpleName()+" methodName ="+new Exception().getStackTrace()[0].getMethodName());
    }
}