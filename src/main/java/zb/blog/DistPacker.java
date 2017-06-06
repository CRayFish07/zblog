package zb.blog;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhmt on 2017/6/5.
 */
public class DistPacker {
    public static void main(String[] args) throws Exception {
        copyFileToDist("blogCfg.txt");
        copyFileToDist("run.sh");
        coptDirToDist("blogres/css");
        coptDirToDist("blogres/js");
    }

    private static void copyFileToDist(String filepath) throws IOException {
        FileUtils.copyFile(new File(filepath),new File("dist/"+filepath),true);
    }

    private static void coptDirToDist(String dirPath) throws IOException {
        FileUtils.copyDirectory(new File(dirPath),new File("dist/"+dirPath),true);
    }
}
