package summary.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * program: myStudy
 * description: 文件剪切或拷贝
 *
 * @author: alien
 * @since: 2020/09/06 10:29
 */
public class CopyOrShear {

    public static void testCopyFile1() throws IOException {
        File fromFile = new File("./xxx/newFile.txt");
        File toFile = new File("./xxx/copyedFile.txt");

        // 这种方式的缺点是会覆盖掉 toFile文件中的内容，如果不想被覆盖使用追加模式
        try (InputStream in = new FileInputStream(fromFile);
            OutputStream out = new FileOutputStream(toFile)) {
            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0 , length);
                out.flush();
            }

        }
    }

    public static void testCopyFile2() throws IOException {
        final Path fromFile = Paths.get("./xxx/newFile.txt");
        final Path toFile = Paths.get("./xxx/copyedFile.txt");

        // 当 源文件不存在时 抛出 NoSuchFileException
        // 当 目标文件存在时, 抛出 FileAlreadyExistsException
        Files.copy(fromFile, toFile);

        // 覆盖已存在的目标文件
        Files.copy(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);

        // 连同文件属性一起拷贝,覆盖已存在的目标文件
        CopyOption[] options = {
                StandardCopyOption.REPLACE_EXISTING, //覆盖已存在的目标文件
                StandardCopyOption.COPY_ATTRIBUTES // 连同文件属性一起拷贝
        };
        Files.copy(fromFile, toFile, options);
    }

    public static void testRenameFile1() throws IOException {
        final Path fromFile = Paths.get("./xxx/newFile.txt");
        final Path toFile = Paths.get("./xxx/renameFile.txt");

        // 文件重命名，使用 move方法
        Files.move(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void testRenameFile2() throws IOException {
        Path source = Paths.get("./xxx/newFile.txt");

        // 文件重命名
        Files.move(source, source.resolveSibling("renameFile.txt"));
    }

    public static void testRenameFile3() {
        File source = new File("./xxx/newFile.txt");
        // 该方法只是返回一个文件重命名是否成功，无法确定是由于什么原因导致的文件重命名失败
        final boolean success = source.renameTo(new File("./xxx/renameFile.txt"));
    }

    public static void testMoveFile() throws IOException {
        final Path source = Paths.get("./xxx/newFile.txt");
        final Path anotherDir = Paths.get("./xxx2/dir"); // 目标文件夹

        Files.createDirectories(anotherDir);
        Files.move(source,
                anotherDir.resolveSibling(source.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);

    }

    public static void main(String[] args) throws IOException {
        testCopyFile1();
        testCopyFile2();
    }
}
