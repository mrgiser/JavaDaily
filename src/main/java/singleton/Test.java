package singleton;

/**
 * 描述:
 * 测试
 *
 * @Author he
 * @Create 2019-02-15 3:38 PM
 */
public class Test {
    public Test() {
        System.out.print("默认构造方法！--");
    }

    //非静态代码块

    {
        System.out.print("非静态代码块！--");
    }

    //静态代码块

    static {
        System.out.print("静态代码块！--");
    }

    public static void test() {
        System.out.print("静态方法中的内容 !--");
        {
            System.out.print("静态方法中的代码块！--");
        }

    }

    public static void main(String[] args) {

//        Test test = new Test();
        Test.test();//静态代码块！--静态方法中的内容! --静态方法中的代码块！--
    }
}