package classLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * program: myStudy
 * description: 自定义类加载
 *
 * @author: alien
 * @since: 2020/12/28 20:50
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassLoader().findClass("Hello");
            Object o = hello.newInstance();
            Method method = hello.getMethod("hello");
            method.invoke(o, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String path = System.getProperty("java.class.path");
//        String path = System.getProperty("user.dir");
        try (InputStream resourceAsStream =
                this.getClass().getResourceAsStream("../" + name + ".xlass")) {
            byte[] bytes = new byte[resourceAsStream.available()];
            resourceAsStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
