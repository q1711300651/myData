package testImage;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ww on 17/4/26.
 */
public class TestConvertImg {
    /**
     * 测试转换png图片为jpg
     *
     * @throws IOException
     */
    @Test
    public void convertPng2Jpg() throws IOException {
        File imgfile = new File("src.png");
        String format = "";
        File formatFile = new File("dec.jpg");
        imgfile.canRead();
        BufferedImage bi = ImageIO.read(imgfile);
        // create a blank, RGB, same width and height, and a white background
        BufferedImage newBufferedImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = newBufferedImage.createGraphics();
        //设置透明度
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.DST_OVER, 0.5f);
        graphics.setComposite(composite);
        newBufferedImage.createGraphics().drawImage(bi, 0, 0, Color.WHITE, null);
        ImageIO.write(newBufferedImage, format, formatFile);
    }

    // 循环每一个像素点，改变像素点的Alpha值
    public void paint(BufferedImage bufferedImage, int alpha) {
        for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                int rgb = bufferedImage.getRGB(j2, j1);
                rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(j2, j1, rgb);
            }
        }
    }
}
