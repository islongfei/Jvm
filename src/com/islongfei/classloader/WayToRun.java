package com.islongfei.classloader;

/**
 * @author islongfei
 * @date 2021.06.06
 * 指定jvm编译模式，热点代码执行效率对比
 */
public class WayToRun {

    /**
     * IDEA: Run->Edit Configurations 设置jvm参数 修改编译模式：
     * <p>
     * -Xmixed 默认采用混合模式：开始解释执行，启动速度较快，对热点代码实行检测和编译。 用时2.7s
     * -Xint 使用纯解释模式：启动很快 执行稍慢。 用时很久，等了几分钟都没结束，不等了...
     * -Xcomp 使用纯编译模式，执行很快，启动很慢（如果类特别多的时候）。 用时 3.6s
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10_0000; i++)
            m();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_0000; i++) {
            m();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void m() {
        for (long i = 0; i < 10_0000L; i++) {
            long j = i % 3;
        }
    }

}
