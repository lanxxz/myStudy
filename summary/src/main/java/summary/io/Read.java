package summary.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * program: myStudy
 * description: Java读文件的方法总结
 *
 * @author: alien
 * @since: 2020/08/29 20:40
 */
public class Read {

    /**
     * method name: testReadFile <br/>
     * description: {@link java.util.Scanner} Java 5 引入

     * @return: void
     * @since: 2020/8/29
     */
    static void testReadFile() throws FileNotFoundException {
        String fileName = "./newFile1.txt";

        // 一行一行的读
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                final String line = sc.nextLine();
                System.out.println(line);
            }
        }
        System.out.println("===========");

        // 按指定分隔符读
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            sc.useDelimiter("\\|");
            while (sc.hasNext()) {
                final String next = sc.next();
                System.out.println(next);
            }
        }
        System.out.println("================");

        // 按基础类型来读取 如: 整数、浮点数等
        String fileName2 = "./newFile2.txt";
        try (Scanner sc = new Scanner(new FileReader(fileName2))) {
            sc.useDelimiter("\\|");
            while (sc.hasNextInt()) {
                int integer = sc.nextInt();
                System.out.println(integer);
            }
        }

    }

    /**
     * method name: testReadFile2 <br/>
     * description: Java 8 引入

     * @return: void
     * @since: 2020/8/29
     */
    static void testReadFile2() throws IOException {
        String fileName = "./newFile1.txt";

        final Stream<String> lines = Files.lines(Paths.get(fileName));

        // 随机顺序进行数据处理
        lines.forEach(System.out::println);
        System.out.println("===========");

        final Stream<String> lines2 = Files.lines(Paths.get(fileName)); // 注意流只能被消费一次
        // 按文件顺序进行数据处理
        lines2.forEachOrdered(System.out::println);
        System.out.println("=============");

        // 以下要注意 java.lang.OutOfMemoryError: Java heap space 内存溢出
        // 转换成 List<String>
        final Stream<String> lines3 = Files.lines(Paths.get(fileName)); // 注意流只能被消费一次
        final List<String> collect = lines3.collect(Collectors.toList());
        final Stream<String> lines4 = Files.lines(Paths.get(fileName)); // 注意流只能被消费一次
        final String content = lines4.collect(Collectors.joining());
        System.out.println(content);

    }

    /**
     * method name: testReadFlie3 <br/>
     * description: Java 1.7 引入

     * @return: void
     * @since: 2020/8/29
     */
    static void testReadFlie3() throws IOException {
        String fileName = "./newFile1.txt";

        // 以下要注意 java.lang.OutOfMemoryError: Java heap space 内存溢出
        final List<String> list = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        list.forEach(System.out::println); // jdk 8 新增的方法
    }

    /**
     * method name: testReadFile4 <br/>
     * description: Java 11 引入

     * @return: void
     * @since: 2020/8/29
     */
    static void testReadFile4() throws IOException {
        String fileName = "./newFile1.txt";

        // 读取一篇文章可以使用这种方式
        // Java 11 新增的方法
        // 读取的文件不能超过 2G, 与你的内存息息相关
//        final String s = Files.readString(Paths.get(fileName));
//        System.out.println(s);
    }

    /**
     * method name: testReadFile5 <br/>
     * description: Java 1.7 引入

     * @return: void
     * @since: 2020/8/29
     */
    static void testReadFile5() throws IOException {
        String fileName = "./newFile1.txt";

        // 读取一篇文章可以使用这种方式
        // Java 1.7 新增的方法
        // 读取的文件不能超过 2G, 与你的内存息息相关
        final byte[] bytes = Files.readAllBytes(Paths.get(fileName));

        final String content = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(content);
    }

    static void testReadFIle6() throws IOException, ClassNotFoundException {
        // 管道流的方式，比较灵活
        String fileName = "./newFile1.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println("========");

        // Java 8 新的写法
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // 读取对象流
//        try (FileInputStream fis = new FileInputStream(fileName);
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//            ois.readObject();
//        }

    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        testReadFile();
        testReadFile2();
        testReadFlie3();
        System.out.println("|||||||||||");
//        testReadFile4();
        testReadFile5();
        System.out.println("|||||||||||");
        testReadFIle6();
    }
}
