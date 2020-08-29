package summary.io;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * program: myStudy
 * description: Java 创建文件并写入数据的方法总结
 *
 * @author: alien
 * @since: 2020/08/29 19:53
 */
public class Write {

    /**
     * method name: testCreateFile <br/>
     * description: jdk 1.7 后新增的方式

     * @return: void
     * @since: 2020/8/29
     */
    static void testCreateFile() throws IOException {
        String fileName = "./newFile1.txt";

        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            bufferedWriter.write("Hello World 创建文件\n");
            // 这里可以不需要 flush 方法
//            bufferedWriter.flush();
        }

        // 追加写模式
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.write("Hello World 第二行");
        }
    }

    /**
     * method name: testCresateFile2 <br/>
     * description: jdk 1.7 以后新增的方式

     * @return: void
     * @since: 2020/8/29
     */
    static void testCreateFile2() throws IOException {
        String fileName = "./newFile2.txt";
        Path path = Paths.get(fileName);
        Files.write(path, "Hello World 创建文件\n".getBytes(StandardCharsets.UTF_8));

        // 追加写模式
        Files.write(path,
                "Hello World 第二行".getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND);
    }

    /**
     * method name: testCreateFile3 <br/>
     * description: jdk 1.5 引入的方法

     * @return: void
     * @since: 2020/8/29
     */
    static void testCreateFile3() throws IOException {
        String fileName = "./newFile3.txt";

        try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
            writer.println("Hello World 创建文件"); // 换行写入
            writer.println("Hello World 第二行");
        }

//        // jdk 10 以后引入的重载方法
//        try (PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
//            writer.println("Hello World 创建文件");
//            writer.println("Hello World 第二行");
//        }
    }

    /**
     * method name: testCreateFIle4 <br/>
     * description: 最原始的创建文件并写入的方式

     * @return: void
     * @since: 2020/8/29
     */
    static void testCreateFIle4() throws IOException {
        String fileName = "./newFile4.txt";
        final File file = new File(fileName);
        if (file.createNewFile()) {
            System.out.println("创建文件成功");
        } else {
            System.out.println("文件已存在，不在创建");
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Hello world 创建文件\n");
        }

        // 追加模式
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Hello World 第二行");
        }
    }

    /**
     * method name: testCreateFile5 <br/>
     * description: 管道流写入文件（最原始的方式之一）

     * @return: void
     * @since: 2020/8/29
     */
    static void testCreateFile5() throws IOException {
        String fileName = "./newFile5.txt";
        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write("Hello world 创建文件");
            writer.flush();
        }
        // 加缓冲  BufferedWriter
        // 写入二进制数据 ByteArrayOutputStream
        // 写入 Java 对象 ObjectOutputStream
        // ... ... 组合使用，功能灵活

//        try (FileOutputStream fos = new FileOutputStream(fileName);
//             ObjectOutputStream writer = new ObjectOutputStream(fos)) {
//            writer.writeObject("Hello world 创建文件");
//            writer.flush();
//        }

//        try (FileOutputStream fos = new FileOutputStream(fileName);
//             ByteArrayOutputStream writer = new ByteArrayOutputStream()) {
//            writer.write("Hello world 创建文件".getBytes(StandardCharsets.UTF_8));
//            writer.writeTo(fos);
//            writer.flush();
//        }
    }

    /**
     * method name: main <br/>
     * description: 入口函数
     * @param args:
     * @return: void
     * @since: 2020/8/29
     */
    public static void main(String[] args) throws IOException {
        testCreateFile();
        testCreateFile2();
        testCreateFile3();
        testCreateFIle4();
        testCreateFile5();
    }
}
