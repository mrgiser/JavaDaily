package singleton;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-02 3:22 PM
 */
public class SingletonDemo {
    private volatile static SingletonDemo instance;

    private SingletonDemo(){

    }

    public static SingletonDemo getInstance(){

        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        SingletonDemo instance = SingletonDemo.getInstance();
    }


}