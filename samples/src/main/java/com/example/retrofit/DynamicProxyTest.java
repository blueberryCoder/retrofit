package com.example.retrofit;

import sun.misc.ProxyGenerator;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * blueberry
 */
public class DynamicProxyTest {


    public interface TestInterface {
        int test(String key, String params) throws Exception;
    }

    public interface TestInterface2 {
        long test2(String key, String params, String params2) throws Exception;
    }
    @CallerSensitive
    public static void main(String[] args) {

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy1", new Class[]{TestInterface.class,TestInterface2.class});

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(".", "$Proxy1.class"));
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.getFD().sync();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
