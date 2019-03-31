package jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-18 3:30 PM
 */
public class NewInterfaceDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("123");
        threadLocal.get();

        NewInterface newInterface = new NewInterface() {
            @Override
            public double calculate(int a) {
                return sqrt(a*100);
            }
        };

        System.out.println(newInterface.calculate(100));
        System.out.println(newInterface.sqrt(16));

        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        Comparator<String> comparator = (String str1, String str2) ->{return str1.compareTo(str2);};
        Arrays.sort(players,comparator);

        Arrays.sort(players,( str1,  str2)->{return str1.compareTo(str2);});



        Runnable r = () -> {
            System.out.println("hello world");
        };

        Thread thread = new Thread(r);
        thread.start();

        new Thread( ()->System.out.println("hello world")).start();

        Function<String, String> upperfier = String::toUpperCase;
        System.out.println(upperfier.apply("Hello"));

    }

}