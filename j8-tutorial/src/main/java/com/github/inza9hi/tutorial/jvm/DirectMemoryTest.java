package com.github.inza9hi.tutorial.jvm;

import sun.misc.JavaNioAccess;
import sun.misc.SharedSecrets;
import sun.misc.VM;

import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.nio.ByteBuffer;

public class DirectMemoryTest {


    public static BufferPoolMXBean getDirectBufferPoolMBean(){
        return ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class)
                .stream()
                .filter(e -> e.getName().equals("direct"))
                .findFirst().get();
    }

    public  static JavaNioAccess.BufferPool getNioBufferPool(){
        return SharedSecrets.getJavaNioAccess().getDirectBufferPool();
    }

//

    /**
     * -XX:MaxDirectMemorySize=60M
     */
    public static void main(String[] args) throws IOException {
        ByteBuffer.allocateDirect(59*1024*1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0);
        System.out.println(VM.maxDirectMemory() / 1024.0 / 1024.0);
        System.out.println(getDirectBufferPoolMBean().getTotalCapacity() / 1024.0 / 1024.0);
        System.out.println(getNioBufferPool().getTotalCapacity() / 1024.0 / 1024.0);
        System.in.read();

//
//        作者：go4it
//        链接：https://juejin.im/post/5c9ced366fb9a070e344c614
//        来源：掘金
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
