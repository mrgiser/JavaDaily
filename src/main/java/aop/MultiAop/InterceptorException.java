package aop.MultiAop;

/**
 * 描述:
 * InterceptorException
 *
 * @Author he
 * @Create 2019-03-01 10:29 AM
 */
public class InterceptorException extends   Exception{
    public InterceptorException(){
        this("msg...",null);

    }
    public InterceptorException(String msg){
        this(msg,null);
    }
    public InterceptorException(String msg,Throwable e){
        super(msg,e);
    }
}