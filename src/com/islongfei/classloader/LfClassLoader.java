package com.islongfei.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author islongfei
 * @date 2021.05.27
 * 实现自定义类加载器
 */
public class LfClassLoader extends ClassLoader {

    /**
     * 必须重写 findClass
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 创建一个文件
        File f = new File("D:/code/test/", name.replace(".", "/").concat(".class"));
        try {
            // 把文件转化为二进制字节数组加载到内存中
            FileInputStream fis = new FileInputStream(f);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int b = 0;

            while ((b = fis.read()) != 0) {
                stream.write(b);
            }

            byte[] bytes = stream.toByteArray();
            stream.close();
            fis.close();
            // 把二进制字节数组转化为class类对象
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name); //throws ClassNotFoundException
    }

    public static void main(String[] args) throws Exception {
        ClassLoader l = new LfClassLoader();
        Class clazz = l.loadClass("com.islongfei.classloader.Hello");
        Class clazz1 = l.loadClass("com.islongfei.classloader.Hello");

        System.out.println(clazz == clazz1);

        // 反射创建对象
        Hello h = (Hello) clazz.newInstance();
        h.m();

        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());

        System.out.println(getSystemClassLoader());
    }

}
