package singleton;

/**
 * 描述:
 * 静态内部类实现单例
 *
 * @Author he
 * @Create 2019-02-15 3:07 PM
 */
public class SingletonStatic {

    private SingletonStatic(){

    }

    private static class SingletonHolder{
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }

    public static SingletonStatic getInstance(){
        return SingletonHolder.INSTANCE;
    }

}