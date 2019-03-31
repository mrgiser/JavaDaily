package string;

/**
 * 描述:
 *
 * @Author he
 * @Create 2019-03-01 4:42 PM
 */
public class BufferBuilder {

    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();

        long start= System.currentTimeMillis();
        for(int i=0;i < 100000; i++){
            buffer.append(i);
        }
        System.out.println("buffer use time = " + (System.currentTimeMillis() - start));


        start= System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for(int i=0;i < 100000; i++){
            builder.append(i);
        }
        System.out.println("builder use time = " + (System.currentTimeMillis() - start));

    }

}