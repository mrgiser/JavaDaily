package classUtil;

import java.io.IOException;

/**
 * 描述:
 * anima
 *
 * @Author HeFeng
 * @Create 2018-09-16 19:36
 */
public class Animal {
    protected void run() {
        System.out.println("Animal run");
    }

    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.run();
    }

}

class Dog extends Animal{
    public void run(){
        System.out.println("Dog run");
    }
}