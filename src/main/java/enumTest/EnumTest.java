package enumTest;

/**
 * 描述:
 * enum
 *
 * @Author HeFeng
 * @Create 2018-09-16 13:25
 */
public class EnumTest {

    enum Color {
        RED, GREEN, YELLOW
    }

    enum Season {
        SPRING, SUMMER, WINTER
    }

    public static void main(String[] args) {
        System.out.println(Color.RED.ordinal());
        System.out.println(Season.SPRING.ordinal());

        Color color = Color.valueOf("RED");
        System.out.println("color: " + color);
    }

}