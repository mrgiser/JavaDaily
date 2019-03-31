package jdk8;

/**
 * @author he
 * @Title: NewInterface
 * @ProjectName java.daily
 * @Description: TODO
 * @date 2019/3/183:28 PM
 */
public interface NewInterface {
    double calculate(int a);
    default double sqrt(int a){
        return Math.sqrt(a);
    }
}
