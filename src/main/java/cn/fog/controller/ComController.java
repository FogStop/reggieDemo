package cn.fog.controller;

import cn.fog.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传，下载
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class ComController {

    @Value("${reggie.path}")
    private String bassPath;
    /**
     * 文件上传
     * @param file
     * @return
     */
    public R<String> upload(MultipartFile file){
//        file是一个临时文件，要转存到指定位置，否则本次请求后临时文件就会被删除
        log.info(file.toString());
//        获取原先的文件名
        String originalFilename = file.getOriginalFilename();
//        获取后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//        使用uuid进行生产文件名，这样不会重复造成文件覆盖
        String fileName = UUID.randomUUID().toString()+suffix;
//       判断一个目录是否存在没有就创建一个目录结构
        File dir = new File(bassPath);
        if (!dir.exists())
            dir.mkdirs();
        try {
//            临时文件转存指定位置
            file.transferTo(new File(bassPath+originalFilename));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return R.success(fileName);
    }

    public void download(String name, HttpServletResponse response){
        try {
//        输入流读取内容
            FileInputStream fileInputStream = new FileInputStream(new File(bassPath+name));
//        输出流写出文件,进行展示
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len=0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
