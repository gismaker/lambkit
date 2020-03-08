/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.common.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;

public class ImageUtils {
	private static final Log log = Log.getLog(ImageUtils.class);

    private final static String[] imgExts = new String[]{"jpg", "jpeg", "png", "bmp"};
    
    private static Map<String, String> mapMimeType = new HashMap<>();
    
    public static String getMimeType(String fileName) {
    	if (fileName == null || "".equals(fileName.trim()) || fileName.indexOf(".") <= 0)
            return "";
    	String suffix = getExtName(fileName);
    	String type = mapMimeType.get(suffix.toLowerCase());
        if (type == null || "".equals(type)) {
            return "";
        }
    	return type;
    }

    public static String getExtName(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index != -1 && (index + 1) < fileName.length()) {
            return fileName.substring(index + 1);
        } else {
            return null;
        }
    }


    /**
     * 过文件扩展名，判断是否为支持的图像文件
     *
     * @param fileName
     * @return 是图片则返回 true，否则返回 false
     */
    public static boolean isImageExtName(String fileName) {
        if (StrKit.isBlank(fileName)) {
            return false;
        }
        fileName = fileName.trim().toLowerCase();
        String ext = getExtName(fileName);
        if (ext != null) {
            for (String s : imgExts) {
                if (s.equals(ext)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean notImageExtName(String fileName) {
        return !isImageExtName(fileName);
    }

    public static int[] ratio(String src) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(src));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        return new int[]{width, height};
    }

    public static String ratioAsString(String src) throws IOException {
        File file = new File(src);
        if (!file.exists()) {
            return null;
        }
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        return String.format("%s x %s", width, height);
    }

    /**
     * 等比缩放，居中剪切，自动在在当前目录下生产新图
     * 例如：aaa.jpg 宽高为100和200，自动在当前目录下生成新图 aaa_100x200.jpg 的图
     *
     * @param src
     * @param w
     * @param h
     * @return 放回新图的路径
     * @throws IOException
     */
    public static String scale(String src, int w, int h) throws IOException {
        int inserTo = src.lastIndexOf(".");
        String dest = src.substring(0, inserTo) + String.format("_%sx%s", w, h) + src.substring(inserTo, src.length());
        scale(src, dest, w, h);
        return dest;
    }

    /**
     * 等比缩放，居中剪切
     *
     * @param src
     * @param dest
     * @param w
     * @param h
     * @throws IOException
     */
    public static void scale(String src, String dest, int w, int h) throws IOException {

        if (notImageExtName(src)) {
            throw new IllegalArgumentException("只支持如下几种图片格式：jpg、jpeg、png、bmp");
        }

        Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(getExtName(src));
        ImageReader reader = (ImageReader) iterator.next();

        InputStream in = new FileInputStream(src);
        ImageInputStream iis = ImageIO.createImageInputStream(in);
        reader.setInput(iis);

        BufferedImage srcBuffered = readBuffereImage(reader, w, h);
        BufferedImage targetBuffered = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = targetBuffered.getGraphics();
        graphics.drawImage(srcBuffered.getScaledInstance(w, h, Image.SCALE_DEFAULT), 0, 0, null); // 绘制缩小后的图

        graphics.dispose();
        srcBuffered.flush();
        save(targetBuffered, dest);
        targetBuffered.flush();
    }

    private static BufferedImage readBuffereImage(ImageReader reader, int w, int h) throws IOException {
        ImageReadParam param = reader.getDefaultReadParam();
        int srcWidth = reader.getWidth(0);
        int srcHeight = reader.getHeight(0);

        Rectangle rect = null;

        if ((float) w / h > (float) srcWidth / srcHeight) {
            h = h * srcWidth / w;
            w = srcWidth;
            rect = new Rectangle(0, (srcHeight - h) / 2, w, h);
        } else {
            w = w * srcHeight / h;
            h = srcHeight;
            rect = new Rectangle((srcWidth - w) / 2, 0, w, h);
        }
        param.setSourceRegion(rect);
        BufferedImage srcBuffered = reader.read(0, param);
        return srcBuffered;
    }

    public final static void pressImage(String watermarkImg, String srcImageFile) {
        pressImage(watermarkImg, srcImageFile, srcImageFile, 5, -1, -1, 0.2f, 1);
    }

    public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile) {
        pressImage(watermarkImg, srcImageFile, destImageFile, 5, -1, -1, 0.2f, 1);
    }

    public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile, int position,
                                        float alpha) {
        pressImage(watermarkImg, srcImageFile, destImageFile, position, -1, -1, 0.2f, alpha);
    }

    /**
     * @param watermarkImg  水印图片位置
     * @param srcImageFile  源图片位置
     * @param destImageFile 生成的图片位置
     * @param position      水印打印的位置： 1->左上角，2->右上角，3->居中，4->左下角，5->右下角
     * @param xOffset       x轴偏移量，xOffset小于0，自动偏移
     * @param yOffset       y轴偏移量，yOffset小于0，自动偏移
     * @param radio         默认为原图的 1/4
     * @param alpha         透明度（0~1），PNG图片建议设置为1
     */
    public final static void pressImage(String watermarkImg, String srcImageFile, String destImageFile, int position,
                                        int xOffset, int yOffset, float radio, float alpha) {

        if (notImageExtName(srcImageFile)) {
            throw new IllegalArgumentException("只支持如下几种图片格式：jpg、jpeg、png、bmp");
        }

        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int srcWidth = src.getWidth(null);
            int srcHeight = src.getHeight(null);

            BufferedImage image = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.drawImage(src, 0, 0, srcWidth, srcHeight, null);

            // 水印文件
            Image wmImage = ImageIO.read(new File(watermarkImg));
            int wmWidth = wmImage.getWidth(null);
            int wmHeight = wmImage.getHeight(null);

            radio = radio <= 0 ? 0.2f : radio;
            int newWidth = (int) (srcWidth * radio);
            int newHeight = (int) (wmHeight * (newWidth / (float) wmWidth));

            xOffset = (xOffset < 0) ? (int) (newWidth * 0.1f) : xOffset;
            yOffset = (yOffset < 0) ? (int) (newHeight * 0.1f) : yOffset;

            int xPostion = 0;
            int yPostion = 0;

            switch (position) {
                case 1:
                    xPostion = xOffset;
                    yPostion = yOffset;
                    break;
                case 2:
                    xPostion = (int) (srcWidth * (1 - radio) - xOffset);
                    yPostion = yOffset;
                    break;
                case 3:
                    xPostion = (int) (srcWidth - newWidth) / 2;
                    yPostion = (int) (srcHeight - newHeight) / 2;
                    break;
                case 4:
                    xPostion = xOffset;
                    yPostion = (int) (srcHeight - newHeight - yOffset);
                    break;
                case 5:
                    xPostion = (int) (srcWidth * (1 - radio) - xOffset);
                    yPostion = (int) (srcHeight - newHeight - yOffset);
                    break;
                default:
                    xPostion = xOffset;
                    yPostion = yOffset;
                    break;
            }

            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            graphics.drawImage(wmImage, xPostion, yPostion, newWidth, newHeight, null);
            // 水印文件结束
            graphics.dispose();

            save(image, destImageFile);

        } catch (Exception e) {
            log.warn("ImageUtils pressImage error", e);
        }
    }

    public static void zoom(int maxWidth, String srcImageFile, String destImageFile) {
        try {
            BufferedImage srcImage = ImageIO.read(new File(srcImageFile));
            int srcWidth = srcImage.getWidth();
            int srcHeight = srcImage.getHeight();

            // 当宽度在 maxWidth 范围之内，直接copy
            if (srcWidth <= maxWidth) {
                FileUtils.copyFile(new File(srcImageFile), new File(destImageFile));
            }
            // 当宽度超出 maxWidth 范围，将宽度变为 maxWidth，高度按比例缩放
            else {
                float scalingRatio = (float) maxWidth / (float) srcWidth;            // 计算缩放比率
                float maxHeight = ((float) srcHeight * scalingRatio);    // 计算缩放后的高度
                BufferedImage ret = resize(srcImage, maxWidth, (int) maxHeight);
                save(ret, destImageFile);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 剪切
     *
     * @param srcImageFile  原图
     * @param destImageFile 存放的目标位置
     * @param left          起始点：左
     * @param top           起始点：上
     * @param width         宽
     * @param height        高
     */
    public static void crop(String srcImageFile, String destImageFile, int left, int top, int width, int height) {

        if (notImageExtName(srcImageFile)) {
            throw new IllegalArgumentException("只支持如下几种图片格式：jpg、jpeg、png、bmp");
        }

        try {
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            width = Math.min(width, bi.getWidth());
            height = Math.min(height, bi.getHeight());
            if (width <= 0) width = bi.getWidth();
            if (height <= 0) height = bi.getHeight();

            left = Math.min(Math.max(0, left), bi.getWidth() - width);
            top = Math.min(Math.max(0, top), bi.getHeight() - height);

            BufferedImage subimage = bi.getSubimage(left, top, width, height);
            BufferedImage resizeImage = resize(subimage, 200, 200);

            save(resizeImage, destImageFile);

        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }


    /**
     * 高保真缩放
     */
    private static BufferedImage resize(BufferedImage bi, int toWidth, int toHeight) {
        Graphics g = null;
        try {
            Image scaledImage = bi.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH);
            BufferedImage ret = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
            g = ret.getGraphics();
            g.drawImage(scaledImage, 0, 0, null);
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }


    private static void save(BufferedImage bi, String outputImageFile) {
        try {
            ImageIO.write(bi, getExtName(outputImageFile), new File(outputImageFile));
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
    }

    static {
        mapMimeType.put("123", "application/vnd.lotus-1-2-3");
        mapMimeType.put("3gp", "video/3gpp");
        mapMimeType.put("aab", "application/x-authoware-bin");
        mapMimeType.put("aam", "application/x-authoware-map");
        mapMimeType.put("aas", "application/x-authoware-seg");
        mapMimeType.put("ai", "application/postscript");
        mapMimeType.put("aif", "audio/x-aiff");
        mapMimeType.put("aifc", "audio/x-aiff");
        mapMimeType.put("aiff", "audio/x-aiff");
        mapMimeType.put("als", "audio/X-Alpha5");
        mapMimeType.put("amc", "application/x-mpeg");
        mapMimeType.put("ani", "application/octet-stream");
        mapMimeType.put("asc", "text/plain");
        mapMimeType.put("asd", "application/astound");
        mapMimeType.put("asf", "video/x-ms-asf");
        mapMimeType.put("asn", "application/astound");
        mapMimeType.put("asp", "application/x-asap");
        mapMimeType.put("asx", "video/x-ms-asf");
        mapMimeType.put("au", "audio/basic");
        mapMimeType.put("avb", "application/octet-stream");
        mapMimeType.put("avi", "video/x-msvideo");
        mapMimeType.put("awb", "audio/amr-wb");
        mapMimeType.put("bcpio", "application/x-bcpio");
        mapMimeType.put("bin", "application/octet-stream");
        mapMimeType.put("bld", "application/bld");
        mapMimeType.put("bld2", "application/bld2");
        mapMimeType.put("bmp", "application/x-MS-bmp");
        mapMimeType.put("bpk", "application/octet-stream");
        mapMimeType.put("bz2", "application/x-bzip2");
        mapMimeType.put("cal", "image/x-cals");
        mapMimeType.put("ccn", "application/x-cnc");
        mapMimeType.put("cco", "application/x-cocoa");
        mapMimeType.put("cdf", "application/x-netcdf");
        mapMimeType.put("cgi", "magnus-internal/cgi");
        mapMimeType.put("chat", "application/x-chat");
        mapMimeType.put("class", "application/octet-stream");
        mapMimeType.put("clp", "application/x-msclip");
        mapMimeType.put("cmx", "application/x-cmx");
        mapMimeType.put("co", "application/x-cult3d-object");
        mapMimeType.put("cod", "image/cis-cod");
        mapMimeType.put("cpio", "application/x-cpio");
        mapMimeType.put("cpt", "application/mac-compactpro");
        mapMimeType.put("crd", "application/x-mscardfile");
        mapMimeType.put("csh", "application/x-csh");
        mapMimeType.put("csm", "chemical/x-csml");
        mapMimeType.put("csml", "chemical/x-csml");
        mapMimeType.put("css", "text/css");
        mapMimeType.put("cur", "application/octet-stream");
        mapMimeType.put("dcm", "x-lml/x-evm");
        mapMimeType.put("dcr", "application/x-director");
        mapMimeType.put("dcx", "image/x-dcx");
        mapMimeType.put("dhtml", "text/html");
        mapMimeType.put("dir", "application/x-director");
        mapMimeType.put("dll", "application/octet-stream");
        mapMimeType.put("dmg", "application/octet-stream");
        mapMimeType.put("dms", "application/octet-stream");
        mapMimeType.put("doc", "application/msword");
        mapMimeType.put("docx", "application/msword");
        mapMimeType.put("dot", "application/x-dot");
        mapMimeType.put("dvi", "application/x-dvi");
        mapMimeType.put("dwf", "drawing/x-dwf");
        mapMimeType.put("dwg", "application/x-autocad");
        mapMimeType.put("dxf", "application/x-autocad");
        mapMimeType.put("dxr", "application/x-director");
        mapMimeType.put("ebk", "application/x-expandedbook");
        mapMimeType.put("emb", "chemical/x-embl-dl-nucleotide");
        mapMimeType.put("embl", "chemical/x-embl-dl-nucleotide");
        mapMimeType.put("eps", "application/postscript");
        mapMimeType.put("eri", "image/x-eri");
        mapMimeType.put("es", "audio/echospeech");
        mapMimeType.put("esl", "audio/echospeech");
        mapMimeType.put("etc", "application/x-earthtime");
        mapMimeType.put("etx", "text/x-setext");
        mapMimeType.put("evm", "x-lml/x-evm");
        mapMimeType.put("evy", "application/x-envoy");
        mapMimeType.put("exe", "application/octet-stream");
        mapMimeType.put("fh4", "image/x-freehand");
        mapMimeType.put("fh5", "image/x-freehand");
        mapMimeType.put("fhc", "image/x-freehand");
        mapMimeType.put("fif", "image/fif");
        mapMimeType.put("fm", "application/x-maker");
        mapMimeType.put("fpx", "image/x-fpx");
        mapMimeType.put("fvi", "video/isivideo");
        mapMimeType.put("gau", "chemical/x-gaussian-input");
        mapMimeType.put("gca", "application/x-gca-compressed");
        mapMimeType.put("gdb", "x-lml/x-gdb");
        mapMimeType.put("gif", "image/gif");
        mapMimeType.put("gps", "application/x-gps");
        mapMimeType.put("gtar", "application/x-gtar");
        mapMimeType.put("gz", "application/x-gzip");
        mapMimeType.put("hdf", "application/x-hdf");
        mapMimeType.put("hdm", "text/x-hdml");
        mapMimeType.put("hdml", "text/x-hdml");
        mapMimeType.put("hlp", "application/winhlp");
        mapMimeType.put("hqx", "application/mac-binhex40");
        mapMimeType.put("htm", "text/html");
        mapMimeType.put("html", "text/html");
        mapMimeType.put("hts", "text/html");
        mapMimeType.put("ice", "x-conference/x-cooltalk");
        mapMimeType.put("ico", "application/octet-stream");
        mapMimeType.put("ief", "image/ief");
        mapMimeType.put("ifm", "image/gif");
        mapMimeType.put("ifs", "image/ifs");
        mapMimeType.put("imy", "audio/melody");
        mapMimeType.put("ins", "application/x-NET-Install");
        mapMimeType.put("ips", "application/x-ipscript");
        mapMimeType.put("ipx", "application/x-ipix");
        mapMimeType.put("it", "audio/x-mod");
        mapMimeType.put("itz", "audio/x-mod");
        mapMimeType.put("ivr", "i-world/i-vrml");
        mapMimeType.put("j2k", "image/j2k");
        mapMimeType.put("jad", "text/vnd.sun.j2me.app-descriptor");
        mapMimeType.put("jam", "application/x-jam");
        mapMimeType.put("jar", "application/java-archive");
        mapMimeType.put("jnlp", "application/x-java-jnlp-file");
        mapMimeType.put("jpe", "image/jpeg");
        mapMimeType.put("jpeg", "image/jpeg");
        mapMimeType.put("jpg", "image/jpeg");
        mapMimeType.put("jpz", "image/jpeg");
        mapMimeType.put("js", "application/x-javascript");
        mapMimeType.put("jwc", "application/jwc");
        mapMimeType.put("kjx", "application/x-kjx");
        mapMimeType.put("lak", "x-lml/x-lak");
        mapMimeType.put("latex", "application/x-latex");
        mapMimeType.put("lcc", "application/fastman");
        mapMimeType.put("lcl", "application/x-digitalloca");
        mapMimeType.put("lcr", "application/x-digitalloca");
        mapMimeType.put("lgh", "application/lgh");
        mapMimeType.put("lha", "application/octet-stream");
        mapMimeType.put("lml", "x-lml/x-lml");
        mapMimeType.put("lmlpack", "x-lml/x-lmlpack");
        mapMimeType.put("lsf", "video/x-ms-asf");
        mapMimeType.put("lsx", "video/x-ms-asf");
        mapMimeType.put("lzh", "application/x-lzh");
        mapMimeType.put("m13", "application/x-msmediaview");
        mapMimeType.put("m14", "application/x-msmediaview");
        mapMimeType.put("m15", "audio/x-mod");
        mapMimeType.put("m3u", "audio/x-mpegurl");
        mapMimeType.put("m3url", "audio/x-mpegurl");
        mapMimeType.put("ma1", "audio/ma1");
        mapMimeType.put("ma2", "audio/ma2");
        mapMimeType.put("ma3", "audio/ma3");
        mapMimeType.put("ma5", "audio/ma5");
        mapMimeType.put("man", "application/x-troff-man");
        mapMimeType.put("map", "magnus-internal/imagemap");
        mapMimeType.put("mbd", "application/mbedlet");
        mapMimeType.put("mct", "application/x-mascot");
        mapMimeType.put("mdb", "application/x-msaccess");
        mapMimeType.put("mdz", "audio/x-mod");
        mapMimeType.put("me", "application/x-troff-me");
        mapMimeType.put("mel", "text/x-vmel");
        mapMimeType.put("mi", "application/x-mif");
        mapMimeType.put("mid", "audio/midi");
        mapMimeType.put("midi", "audio/midi");
        mapMimeType.put("mif", "application/x-mif");
        mapMimeType.put("mil", "image/x-cals");
        mapMimeType.put("mio", "audio/x-mio");
        mapMimeType.put("mmf", "application/x-skt-lbs");
        mapMimeType.put("mng", "video/x-mng");
        mapMimeType.put("mny", "application/x-msmoney");
        mapMimeType.put("moc", "application/x-mocha");
        mapMimeType.put("mocha", "application/x-mocha");
        mapMimeType.put("mod", "audio/x-mod");
        mapMimeType.put("mof", "application/x-yumekara");
        mapMimeType.put("mol", "chemical/x-mdl-molfile");
        mapMimeType.put("mop", "chemical/x-mopac-input");
        mapMimeType.put("mov", "video/quicktime");
        mapMimeType.put("movie", "video/x-sgi-movie");
        mapMimeType.put("mp2", "audio/x-mpeg");
        mapMimeType.put("mp3", "audio/x-mpeg");
        mapMimeType.put("mp4", "video/mp4");
        mapMimeType.put("mpc", "application/vnd.mpohun.certificate");
        mapMimeType.put("mpe", "video/mpeg");
        mapMimeType.put("mpeg", "video/mpeg");
        mapMimeType.put("mpg", "video/mpeg");
        mapMimeType.put("mpg4", "video/mp4");
        mapMimeType.put("mpga", "audio/mpeg");
        mapMimeType.put("mpn", "application/vnd.mophun.application");
        mapMimeType.put("mpp", "application/vnd.ms-project");
        mapMimeType.put("mps", "application/x-mapserver");
        mapMimeType.put("mrl", "text/x-mrml");
        mapMimeType.put("mrm", "application/x-mrm");
        mapMimeType.put("ms", "application/x-troff-ms");
        mapMimeType.put("mts", "application/metastream");
        mapMimeType.put("mtx", "application/metastream");
        mapMimeType.put("mtz", "application/metastream");
        mapMimeType.put("mzv", "application/metastream");
        mapMimeType.put("nar", "application/zip");
        mapMimeType.put("nbmp", "image/nbmp");
        mapMimeType.put("nc", "application/x-netcdf");
        mapMimeType.put("ndb", "x-lml/x-ndb");
        mapMimeType.put("ndwn", "application/ndwn");
        mapMimeType.put("nif", "application/x-nif");
        mapMimeType.put("nmz", "application/x-scream");
        mapMimeType.put("nokia-op-logo", "image/vnd.nok-oplogo-color");
        mapMimeType.put("npx", "application/x-netfpx");
        mapMimeType.put("nsnd", "audio/nsnd");
        mapMimeType.put("nva", "application/x-neva1");
        mapMimeType.put("oda", "application/oda");
        mapMimeType.put("oom", "application/x-AtlasMate-Plugin");
        mapMimeType.put("pac", "audio/x-pac");
        mapMimeType.put("pae", "audio/x-epac");
        mapMimeType.put("pan", "application/x-pan");
        mapMimeType.put("pbm", "image/x-portable-bitmap");
        mapMimeType.put("pcx", "image/x-pcx");
        mapMimeType.put("pda", "image/x-pda");
        mapMimeType.put("pdb", "chemical/x-pdb");
        mapMimeType.put("pdf", "application/pdf");
        mapMimeType.put("pfr", "application/font-tdpfr");
        mapMimeType.put("pgm", "image/x-portable-graymap");
        mapMimeType.put("pict", "image/x-pict");
        mapMimeType.put("pm", "application/x-perl");
        mapMimeType.put("pmd", "application/x-pmd");
        mapMimeType.put("png", "image/png");
        mapMimeType.put("pnm", "image/x-portable-anymap");
        mapMimeType.put("pnz", "image/png");
        mapMimeType.put("pot", "application/vnd.ms-powerpoint");
        mapMimeType.put("ppm", "image/x-portable-pixmap");
        mapMimeType.put("pps", "application/vnd.ms-powerpoint");
        mapMimeType.put("ppt", "application/vnd.ms-powerpoint");
        mapMimeType.put("pptx", "application/vnd.ms-powerpoint");
        mapMimeType.put("pqf", "application/x-cprplayer");
        mapMimeType.put("pqi", "application/cprplayer");
        mapMimeType.put("prc", "application/x-prc");
        mapMimeType.put("proxy", "application/x-ns-proxy-autoconfig");
        mapMimeType.put("ps", "application/postscript");
        mapMimeType.put("ptlk", "application/listenup");
        mapMimeType.put("pub", "application/x-mspublisher");
        mapMimeType.put("pvx", "video/x-pv-pvx");
        mapMimeType.put("qcp", "audio/vnd.qcelp");
        mapMimeType.put("qt", "video/quicktime");
        mapMimeType.put("qti", "image/x-quicktime");
        mapMimeType.put("qtif", "image/x-quicktime");
        mapMimeType.put("r3t", "text/vnd.rn-realtext3d");
        mapMimeType.put("ra", "audio/x-pn-realaudio");
        mapMimeType.put("ram", "audio/x-pn-realaudio");
        mapMimeType.put("rar", "application/x-rar-compressed");
        mapMimeType.put("ras", "image/x-cmu-raster");
        mapMimeType.put("rdf", "application/rdf+xml");
        mapMimeType.put("rf", "image/vnd.rn-realflash");
        mapMimeType.put("rgb", "image/x-rgb");
        mapMimeType.put("rlf", "application/x-richlink");
        mapMimeType.put("rm", "audio/x-pn-realaudio");
        mapMimeType.put("rmf", "audio/x-rmf");
        mapMimeType.put("rmm", "audio/x-pn-realaudio");
        mapMimeType.put("rmvb", "audio/x-pn-realaudio");
        mapMimeType.put("rnx", "application/vnd.rn-realplayer");
        mapMimeType.put("roff", "application/x-troff");
        mapMimeType.put("rp", "image/vnd.rn-realpix");
        mapMimeType.put("rpm", "audio/x-pn-realaudio-plugin");
        mapMimeType.put("rt", "text/vnd.rn-realtext");
        mapMimeType.put("rte", "x-lml/x-gps");
        mapMimeType.put("rtf", "application/rtf");
        mapMimeType.put("rtg", "application/metastream");
        mapMimeType.put("rtx", "text/richtext");
        mapMimeType.put("rv", "video/vnd.rn-realvideo");
        mapMimeType.put("rwc", "application/x-rogerwilco");
        mapMimeType.put("s3m", "audio/x-mod");
        mapMimeType.put("s3z", "audio/x-mod");
        mapMimeType.put("sca", "application/x-supercard");
        mapMimeType.put("scd", "application/x-msschedule");
        mapMimeType.put("sdf", "application/e-score");
        mapMimeType.put("sea", "application/x-stuffit");
        mapMimeType.put("sgm", "text/x-sgml");
        mapMimeType.put("sgml", "text/x-sgml");
        mapMimeType.put("sh", "application/x-sh");
        mapMimeType.put("shar", "application/x-shar");
        mapMimeType.put("shtml", "magnus-internal/parsed-html");
        mapMimeType.put("shw", "application/presentations");
        mapMimeType.put("si6", "image/si6");
        mapMimeType.put("si7", "image/vnd.stiwap.sis");
        mapMimeType.put("si9", "image/vnd.lgtwap.sis");
        mapMimeType.put("sis", "application/vnd.symbian.install");
        mapMimeType.put("sit", "application/x-stuffit");
        mapMimeType.put("skd", "application/x-Koan");
        mapMimeType.put("skm", "application/x-Koan");
        mapMimeType.put("skp", "application/x-Koan");
        mapMimeType.put("skt", "application/x-Koan");
        mapMimeType.put("slc", "application/x-salsa");
        mapMimeType.put("smd", "audio/x-smd");
        mapMimeType.put("smi", "application/smil");
        mapMimeType.put("smil", "application/smil");
        mapMimeType.put("smp", "application/studiom");
        mapMimeType.put("smz", "audio/x-smd");
        mapMimeType.put("snd", "audio/basic");
        mapMimeType.put("spc", "text/x-speech");
        mapMimeType.put("spl", "application/futuresplash");
        mapMimeType.put("spr", "application/x-sprite");
        mapMimeType.put("sprite", "application/x-sprite");
        mapMimeType.put("spt", "application/x-spt");
        mapMimeType.put("src", "application/x-wais-source");
        mapMimeType.put("stk", "application/hyperstudio");
        mapMimeType.put("stm", "audio/x-mod");
        mapMimeType.put("sv4cpio", "application/x-sv4cpio");
        mapMimeType.put("sv4crc", "application/x-sv4crc");
        mapMimeType.put("svf", "image/vnd");
        mapMimeType.put("svg", "image/svg-xml");
        mapMimeType.put("svh", "image/svh");
        mapMimeType.put("svr", "x-world/x-svr");
        mapMimeType.put("swf", "application/x-shockwave-flash");
        mapMimeType.put("swfl", "application/x-shockwave-flash");
        mapMimeType.put("t", "application/x-troff");
        mapMimeType.put("tad", "application/octet-stream");
        mapMimeType.put("talk", "text/x-speech");
        mapMimeType.put("tar", "application/x-tar");
        mapMimeType.put("taz", "application/x-tar");
        mapMimeType.put("tbp", "application/x-timbuktu");
        mapMimeType.put("tbt", "application/x-timbuktu");
        mapMimeType.put("tcl", "application/x-tcl");
        mapMimeType.put("tex", "application/x-tex");
        mapMimeType.put("texi", "application/x-texinfo");
        mapMimeType.put("texinfo", "application/x-texinfo");
        mapMimeType.put("tgz", "application/x-tar");
        mapMimeType.put("thm", "application/vnd.eri.thm");
        mapMimeType.put("tif", "image/tiff");
        mapMimeType.put("tiff", "image/tiff");
        mapMimeType.put("tki", "application/x-tkined");
        mapMimeType.put("tkined", "application/x-tkined");
        mapMimeType.put("toc", "application/toc");
        mapMimeType.put("toy", "image/toy");
        mapMimeType.put("tr", "application/x-troff");
        mapMimeType.put("trk", "x-lml/x-gps");
        mapMimeType.put("trm", "application/x-msterminal");
        mapMimeType.put("tsi", "audio/tsplayer");
        mapMimeType.put("tsp", "application/dsptype");
        mapMimeType.put("tsv", "text/tab-separated-values");
        mapMimeType.put("tsv", "text/tab-separated-values");
        mapMimeType.put("ttf", "application/octet-stream");
        mapMimeType.put("ttz", "application/t-time");
        mapMimeType.put("txt", "text/plain");
        mapMimeType.put("ult", "audio/x-mod");
        mapMimeType.put("ustar", "application/x-ustar");
        mapMimeType.put("uu", "application/x-uuencode");
        mapMimeType.put("uue", "application/x-uuencode");
        mapMimeType.put("vcd", "application/x-cdlink");
        mapMimeType.put("vcf", "text/x-vcard");
        mapMimeType.put("vdo", "video/vdo");
        mapMimeType.put("vib", "audio/vib");
        mapMimeType.put("viv", "video/vivo");
        mapMimeType.put("vivo", "video/vivo");
        mapMimeType.put("vmd", "application/vocaltec-media-desc");
        mapMimeType.put("vmf", "application/vocaltec-media-file");
        mapMimeType.put("vmi", "application/x-dreamcast-vms-info");
        mapMimeType.put("vms", "application/x-dreamcast-vms");
        mapMimeType.put("vox", "audio/voxware");
        mapMimeType.put("vqe", "audio/x-twinvq-plugin");
        mapMimeType.put("vqf", "audio/x-twinvq");
        mapMimeType.put("vql", "audio/x-twinvq");
        mapMimeType.put("vre", "x-world/x-vream");
        mapMimeType.put("vrml", "x-world/x-vrml");
        mapMimeType.put("vrt", "x-world/x-vrt");
        mapMimeType.put("vrw", "x-world/x-vream");
        mapMimeType.put("vts", "workbook/formulaone");
        mapMimeType.put("wav", "audio/x-wav");
        mapMimeType.put("wax", "audio/x-ms-wax");
        mapMimeType.put("wbmp", "image/vnd.wap.wbmp");
        mapMimeType.put("web", "application/vnd.xara");
        mapMimeType.put("wi", "image/wavelet");
        mapMimeType.put("wis", "application/x-InstallShield");
        mapMimeType.put("wm", "video/x-ms-wm");
        mapMimeType.put("wma", "audio/x-ms-wma");
        mapMimeType.put("wmd", "application/x-ms-wmd");
        mapMimeType.put("wmf", "application/x-msmetafile");
        mapMimeType.put("wml", "text/vnd.wap.wml");
        mapMimeType.put("wmlc", "application/vnd.wap.wmlc");
        mapMimeType.put("wmls", "text/vnd.wap.wmlscript");
        mapMimeType.put("wmlsc", "application/vnd.wap.wmlscriptc");
        mapMimeType.put("wmlscript", "text/vnd.wap.wmlscript");
        mapMimeType.put("wmv", "audio/x-ms-wmv");
        mapMimeType.put("wmx", "video/x-ms-wmx");
        mapMimeType.put("wmz", "application/x-ms-wmz");
        mapMimeType.put("wpng", "image/x-up-wpng");
        mapMimeType.put("wpt", "x-lml/x-gps");
        mapMimeType.put("wri", "application/x-mswrite");
        mapMimeType.put("wrl", "x-world/x-vrml");
        mapMimeType.put("wrz", "x-world/x-vrml");
        mapMimeType.put("ws", "text/vnd.wap.wmlscript");
        mapMimeType.put("wsc", "application/vnd.wap.wmlscriptc");
        mapMimeType.put("wv", "video/wavelet");
        mapMimeType.put("wvx", "video/x-ms-wvx");
        mapMimeType.put("wxl", "application/x-wxl");
        mapMimeType.put("x-gzip", "application/x-gzip");
        mapMimeType.put("xar", "application/vnd.xara");
        mapMimeType.put("xbm", "image/x-xbitmap");
        mapMimeType.put("xdm", "application/x-xdma");
        mapMimeType.put("xdma", "application/x-xdma");
        mapMimeType.put("xdw", "application/vnd.fujixerox.docuworks");
        mapMimeType.put("xht", "application/xhtml+xml");
        mapMimeType.put("xhtm", "application/xhtml+xml");
        mapMimeType.put("xhtml", "application/xhtml+xml");
        mapMimeType.put("xla", "application/vnd.ms-excel");
        mapMimeType.put("xlc", "application/vnd.ms-excel");
        mapMimeType.put("xll", "application/x-excel");
        mapMimeType.put("xlm", "application/vnd.ms-excel");
        mapMimeType.put("xls", "application/vnd.ms-excel");
        mapMimeType.put("xlsx", "application/vnd.ms-excel");
        mapMimeType.put("xlt", "application/vnd.ms-excel");
        mapMimeType.put("xlw", "application/vnd.ms-excel");
        mapMimeType.put("xm", "audio/x-mod");
        mapMimeType.put("xml", "text/xml");
        mapMimeType.put("xmz", "audio/x-mod");
        mapMimeType.put("xpi", "application/x-xpinstall");
        mapMimeType.put("xpm", "image/x-xpixmap");
        mapMimeType.put("xsit", "text/xml");
        mapMimeType.put("xsl", "text/xml");
        mapMimeType.put("xul", "text/xul");
        mapMimeType.put("xwd", "image/x-xwindowdump");
        mapMimeType.put("xyz", "chemical/x-pdb");
        mapMimeType.put("yz1", "application/x-yz1");
        mapMimeType.put("z", "application/x-compress");
        mapMimeType.put("zac", "application/x-zaurus-zac");
        mapMimeType.put("zip", "application/zip");
    }

}