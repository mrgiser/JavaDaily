package gc;

/**
 * 描述:
 * TestAllocation
 *
 * @Author he
 * @Create 2019-03-11 2:37 PM
 */
public class TestAllocation {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        TestAllocation.testTenuringThredhold();
    }

    /**
     * VM参数：（参数序号对应实验序号）
     *  1. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseParNewGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *  2. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseParallelGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[4*_1MB]; //出现一次 Minor GC
    }

    /**
     * VM参数：（参数序号对应实验序号）
     *  3. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseParNewGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3145728
     */
    public static void testPreteureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    /**
     * VM参数：（参数序号对应实验序号）
     *  4. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     *  5. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:MaxTenuringThreshold=4 -XX:+PrintTenuringDistribution
     *
     */
    @SuppressWarnings("unused")
    public static void testTenuringThredhold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 16];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * VM参数：（参数序号对应实验序号）
     *  6. -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:MaxTenuringThreshold=4 -XX:+PrintTenuringDistribution
     *
     *  如果在Survivor空间中相同年龄所有对象大小的总和>Survivor空间的一半（ -XX:TargetSurvivorRatio）时，年龄>=该年龄的对象就可以直接进入年老代
     */
    @SuppressWarnings("unused")
    public static void testTenuringThredhold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}