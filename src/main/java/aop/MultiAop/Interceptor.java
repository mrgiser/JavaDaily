package aop.MultiAop;

/**
 * 描述:
 * Interceptor
 *
 * @Author he
 * @Create 2019-03-01 10:28 AM
 */
public interface Interceptor {
    /**
     * 拦截器 之前实现用于代码逻辑
     * @param args 委托类参数
     */
    public void beforeIntercept(Object... args) throws InterceptorException;

    /**
     * 拦截器 之后 实现对计算结果实现公共逻辑
     * @param result
     */
    public void afterIntercept(Object  result)throws InterceptorException;
}