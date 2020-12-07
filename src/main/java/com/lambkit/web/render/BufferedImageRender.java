package com.lambkit.web.render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.jfinal.log.Log;
import com.jfinal.render.Render;
import com.lambkit.common.util.ImageUtils;

public class BufferedImageRender extends Render {
	private static Log log = Log.getLog(BufferedImageRender.class);
	
    private BufferedImage image;
    private String formatName;

    public BufferedImageRender(BufferedImage image, String formatName) {
        this.image=image;
        this.formatName = formatName;
    }

    public void render() {
        ServletOutputStream sos = null;
        try {
            //设置头信息,内容处理的方式,attachment以附件的形式打开,就是进行下载,并设置下载文件的命名
//            response.setHeader("Content-Disposition","attachment;filename="+file.getName());
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType(ImageUtils.getMineTypeFormat(formatName));
            sos=response.getOutputStream();
            ImageIO.write(image, formatName, sos);
        	sos.flush();
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
