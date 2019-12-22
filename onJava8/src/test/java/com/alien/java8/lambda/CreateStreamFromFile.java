package com.alien.java8.lambda;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * program: myStudy
 * description: 测试 Stream 流从文件中读取内容
 *
 * @author: alien
 * @since: 2019/12/08 23:38
 */
public class CreateStreamFromFile {

    /**
     * method name: test <br/>
     * description: 绝对路径
     *
     * @return: void
     * @since: 2019-12-08
     */
    @Test
    public void test() {
        Path path = Paths.get("/Users/alien/IdeaProjects/myStudy/onJava8/src/main/java/com/alien/java8/bean/Apple.java");
        try (Stream<String> stringStream = Files.lines(path)) {
            stringStream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * method name: test2 <br/>
     * description: 相对路径

     * @return: void
     * @since: 2019-12-08
     */
    @Test
    public void test2() {
        Path path = Paths.get("./src/main/java/com/alien/java8/bean/Apple.java");
        try (Stream<String> stringStream = Files.lines(path)) {
            stringStream.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
