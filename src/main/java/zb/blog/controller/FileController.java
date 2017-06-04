package zb.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zb.blog.BlogCfg;
import zb.blog.model.FileNode;
import zb.blog.service.FileService;

import java.io.File;
import java.util.List;

/**
 * Created by zhmt on 2017/6/3.
 */
@RestController()
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private BlogCfg blogCfg;

    /**
     * 枚举目录
     * @param dir
     * @return
     */
    @GetMapping("/file/list")
    public List<FileNode> getFileList(String dir) {
        dir = dir.replaceAll("#","");
        if(StringUtils.isBlank(dir))
            dir = "/";
        return fileService.listDir(dir);
    }

    @PostMapping("/file/delete")
    public String delete(String dir) {
        if(StringUtils.isBlank(dir))
            return "";
        return fileService.del(dir);
    }

    @PostMapping("/file/mkdir")
    public void mkdir(String parentDir,String dir) {
        if(StringUtils.isBlank(parentDir))
            throw new RuntimeException(blogCfg.strParentDirectoryCantBeBlank);
        if(StringUtils.isBlank(dir))
            throw new RuntimeException(blogCfg.strDirectoryCantBeBlank);

        fileService.mkdir(parentDir+dir);
        return;
    }

    @PostMapping("/file")
    public void postFile(@RequestParam("file")MultipartFile file,String dir) {
//       System.out.println(file.getOriginalFilename());
//       System.out.println(dir);
        fileService.save(file,dir);
    }
}
