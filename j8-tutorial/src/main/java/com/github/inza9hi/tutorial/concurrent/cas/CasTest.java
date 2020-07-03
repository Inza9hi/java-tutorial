package com.github.inza9hi.tutorial.concurrent.cas;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class CasTest {

  static class Target {

    public int value = 10;
  }

    public static void main(String[] args) throws Exception {
//      Exception in thread "main" java.lang.SecurityException: Unsafe
//      at sun.misc.Unsafe.getUnsafe(Unsafe.java:90)
//      at com.github.inza9hi.tutorial.concurrent.cas.CasTest.main(CasTest.java:15)

//      Unsafe unsafe = Unsafe.getUnsafe();


      //通过反射获得Unsafe实例，仅BootstrapClassLoader加载的类
      //（$JAVA_HOME/lib目录下jar包包含的类，如java.util.concurrent.atomic.AtomicInteger）
      //才能通过Unsafe.getUnsafe静态方法获取
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      Unsafe unsafe = (Unsafe) field.get(null);





      Target t = new Target();
      System.out.println("原始value值:" + t.value);
      Field valueField = Target.class.getDeclaredField("value");


      //获得实例域在class文件里的偏移量
      final long valueOffset = unsafe.objectFieldOffset(valueField);

      //第一次swap
      System.out.println("第一次swap(10,20)函数返回值:" + unsafe.compareAndSwapInt(t, valueOffset, 10, 20));
      System.out.println("第一次swap(10,20)后value值:" + valueField.get(t));

      //第二次swap
      System.out.println("第2次swap(10,21)函数返回值:" + unsafe.compareAndSwapInt(t, valueOffset, 10, 21));
      System.out.println("第2次swap(10,21)后value值:" + valueField.get(t));
//      版权声明：本文为CSDN博主「硅藻泥先森」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//      原文链接：https://blog.csdn.net/jilindaxue/article/details/74854625

  }

}
