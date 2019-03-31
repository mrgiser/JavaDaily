package gc;

import java.util.ArrayList;
import java.util.List;
/**
 * 描述:
 * SummaryCase
 *
 * @Author he
 * @Create 2019-03-10 11:10 PM
 */

public class SummaryCase {
        public static void main(String[] args) throws Exception{
            List<Object> caches=new ArrayList<Object>();
            for(int i=0;i<7;i++){
                caches.add(new byte[1024*1024*3]);
            }
            caches.clear();
            for(int j=0;j<2;j++){
                caches.add(new byte[1024*1024*3]);
            }
            Thread.sleep(5000);
        }

}