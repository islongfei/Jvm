package com.islongfei.classloader;

/**
 * @author islongfei
 * @date 2021.06.06
 * 懒加载（懒初始化）机制
 */
public class LazyLoading {


    /**
     * jvm规范并没有规定何时加载类，按需加载
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        P p; //类不会被加载
        X x = new X(); //new对象 子类和父类都被加载了（初始化子类的时候，父类首先初始化）
        System.out.println(P.i); //访问final变量 类不会被加载，不需要加载整个类，直接访问方法区
        System.out.println(P.j);  //访问非final变量，类会被加载


    }

    public static class P {
        final static int i = 8;
        static int j = 9;

        static {
            System.out.println("P被加载了");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X被加载了");
        }
    }

}
