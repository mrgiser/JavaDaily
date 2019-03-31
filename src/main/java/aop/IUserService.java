package aop;

/**
 * @Description： AOP基于动态代理 实现  <br/>
 */
public interface IUserService {
    void saveUser(String username, String password) throws Exception;
}