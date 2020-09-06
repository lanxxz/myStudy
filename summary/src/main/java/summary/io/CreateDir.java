package summary.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * program: myStudy
 * description: 创建文件夹方式总结
 *
 * @author: alien
 * @since: 2020/09/06 10:10
 */
public class CreateDir {

    public static void testCreateDir1() {
        // 缺点：异常处理环节做得比较差，不知道是什么原因导致的失败

        String dirPath = "./createDir/dir";

        final File dir = new File(dirPath);

        // mkdir
        final boolean mkdir = dir.mkdir();
        System.out.println("创建文件夹:" + mkdir);

        // mkdirs
        final boolean mkdirs = dir.mkdirs();
        System.out.println("创建文件夹:" + mkdirs);

    }

    public static void testCreateDir2() throws IOException {
        // nio 改进第一种方法

        final Path path = Paths.get("./createDir2/dir");
        // 父文件夹不存在 则抛出 NoSuchFileException
        // 文件夹已经存在 则抛出 FileAlreadyExistsException
        // 如果磁盘IO异常 则抛出 IOException
        Files.createDirectory(path);

        // 父文件夹不存在 则创建它
        // 文件夹已经存在 则抛出 FileAlreadyExistsException
        // 如果磁盘IO异常 则抛出 IOException
        Files.createDirectories(path);

    }

    public static void main(String[] args) throws IOException {
        testCreateDir1();
        testCreateDir2();
    }
}
