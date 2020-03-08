package com.lambkit.web.render;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

import com.jfinal.log.Log;
import com.jfinal.render.Render;
import com.lambkit.common.util.ImageUtils;

public class MimeTypeRender extends Render {
	private static Log log = Log.getLog(MimeTypeRender.class);
	
    private String filename;

    public MimeTypeRender(String filename) {
        this.filename=filename;
    }

    public void render() {
        ServletOutputStream sos = null;
        try {
            File file = new File(filename);
            //设置头信息,内容处理的方式,attachment以附件的形式打开,就是进行下载,并设置下载文件的命名
//            response.setHeader("Content-Disposition","attachment;filename="+file.getName());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(ImageUtils.getMimeType(filename));
            sos=response.getOutputStream();
            // 创建文件输入流
            FileInputStream is = new FileInputStream(file);
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                sos.write(buffer, 0, len);
            }
            is.close();
        } catch (Exception e) {
            log.error("图片render出错:"+e.getLocalizedMessage(),e);
            throw new RuntimeException(e);
        } finally {
            if (sos != null)
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
