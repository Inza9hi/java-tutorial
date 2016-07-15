package com.github.inza9hi.tutorial;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.inza9hi.util.TimeWatch;
import org.github.jamm.MemoryMeter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created by lawulu on 16-7-4.
 */
public class KroyTest {
//    static int rows = 500000;
//    static int columns = 2000;
//    private String[][] data= new String[rows][columns];
//    {
//        for (int i = 0; i < rows; i++) {
//            data[i][0]="000101014120939658"+i;
//
//            for (int j = 1; j < columns; j++) {
//                data[i][j]="tes";
//
//            }
//
//        }
//
//    }

    static int rows = 2000;
    static int columns = 500000;
    private String[][] data= new String[rows][columns];
//    {
//        for (int i = 0; i < rows; i++) {
//            data[i][0]="000101014120939658"+i;
//
//            for (int j = 1; j < columns; j++) {
//                data[i][j]="tes";
//
//            }
//
//        }
//
//    }

    public static void main(String[] args) throws Exception {
        TimeWatch watch = TimeWatch.start();

        MemoryMeter meter = new MemoryMeter();
        Kryo kryo = new Kryo();

        KroyTest someObject = new KroyTest();
        System.out.println(meter.measureDeep(someObject.data));



        // ...
        Output output = new Output(new FileOutputStream("/data/logs/test.bin"));







        for (int i = 0; i < rows; i++) {
            someObject.data[i][0]="000101014120939658"+i;

            for (int j = 1; j < columns; j++) {
                someObject.data[i][j]="test";

            }

        }

        kryo.writeObject(output, someObject);
        output.close();

        System.out.println("Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS));

        // ...
        Input input = new Input(new FileInputStream("/data/logs/test.bin"));
        someObject = kryo.readObject(input, KroyTest.class);
        input.close();

        System.out.println("Elapsed Time in seconds: " + watch.time(TimeUnit.SECONDS));



    }
}
