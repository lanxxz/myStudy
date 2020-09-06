package summary.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * program: myStudy
 * description: 总结删除文件、文件夹的方法<br/>
 * <br/><br/>
 * 这四种方法可以删除文件以及空文件夹
 *                            | 判别文件夹是否存在导致的失败 | 判别文件夹不为空导致的失败      | 备注       <br/>
 * File类的delete()            | 不能，返回false          | 不能返回false               | 传统IO      <br/>
 * File类的deleteOnExit()      | 不能，不存在不执行删除     | 不能，返回 void              | 传统IO      <br/>
 * Files.delete(Path)         | NoSuchFileException    | DirectoryNotEmptyException | NIO        <br/>
 * Files.deleteIfExists(Path) | false                  | DirectoryNotEmptyException | NIO        <br/>
 * <br/><br/>
 * 删除文件夹以及文件夹下的所有目录<br/>
 * Files.walkFileTree 方法 与 FileVisitor <br/>
 * Files.walk 方法，然后遍历删除 <br/>
 * 递归  File 类中的 listFiles 然后删除 <br/>
 *
 * @author: alien
 * @since: 2020/09/06 11:09
 */
public class DeleteFile {

    public static void testDeleteFile() throws IOException {
        String filePath = "./deleteFile/file.txt";
        final File file = new File(filePath);

        final boolean delete = file.delete();

        file.deleteOnExit();

        final Path path = Paths.get(filePath);
        Files.delete(path);

        final boolean success = Files.deleteIfExists(path);
    }

    public static void testDeleteDirs1() throws IOException {
        // 创建测试的文件夹
        createDir();
        // 删除文件夹
        final Path path = Paths.get("./test1");
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            /**
             * method name: visitFile <br/>
             * description: 遍历删除文件
             * @param file:
             * @param attrs:
             * @return: java.nio.file.FileVisitResult
             * @since: 2020/9/6
             */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // 可以使用 BasicFileAttributes 类中提供的方法来判断当前文件是否符合删除条件
                Files.delete(file);
                System.out.printf("删除文件：%s%n", file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                System.out.printf("删除文件夹：%s%n", dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void testDeleteDirs2() throws IOException {
        // 创建文件夹
        createDir();
        // 删除文件夹
        final Path path = Paths.get("./test1");
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())  // 倒叙排序，使其先删除文件再删除文件夹
                    .forEach(DeleteFile::deleteDirectoriesStream);
        }
    }

    public static void testDeleteDirs3() throws IOException {
        // 创建文件夹
        createDir();
        // 删除文件夹
        final File file = new File("./test1");
        deleteDirectoriesLegacyIO(file);

    }

    private static void deleteDirectoriesLegacyIO(File file) {
        final File[] files = file.listFiles();
        if (files != null) {
            for (File file1: files) {
                deleteDirectoriesLegacyIO(file1);
            }
        }

        if (file.delete()) {
            System.out.printf("删除文件 %s 成功%n", file);
        } else {
            System.err.printf("删除文件 %s 失败%n", file);
        }
    }

    private static void deleteDirectoriesStream(Path path) {
        try {
            Files.delete(path);
            System.out.printf("成功删除文件路径 %s%n", path);
        } catch (IOException e) {
            System.err.printf("无法删除文件路径 %s%n%s", path, e);
        }
    }



    private static void createDir() throws IOException {
        Files.createDirectories(Paths.get("./test1/test2/test3/test4/test5"));
        Files.write(Paths.get("./test1/test2/test3/test3.txt"), "hello".getBytes());
        Files.write(Paths.get("./test1/test2/test3/test4/test4.txt"), "world".getBytes());
    }


    public static void main(String[] args) throws IOException {
//        testDeleteFile();
//        testDeleteDirs1();
//        testDeleteDirs2();
        testDeleteDirs3();
    }

}
