package com.scrumers.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.commons.configuration.Configuration;

public class ImageUtils {

    private static Configuration conf;
    
    public void setConf(Configuration conf) {
        ImageUtils.conf = conf;
    }

    public static BufferedImage createResizedCopy(BufferedImage originalImage,
            int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_ARGB
                : BufferedImage.TYPE_INT_RGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,
                imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }

    public static BufferedImage createResizedCopy1(BufferedImage originalImage, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_ARGB
                : BufferedImage.TYPE_INT_RGB;
        BufferedImage scaledBI = new BufferedImage(originalImage.getWidth()/2, originalImage.getHeight()/2,
                imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, originalImage.getWidth()/2, originalImage.getHeight()/2, null);
        g.dispose();
        return scaledBI;
    }
    
    /** 
     * sizeMark: s(small 64x64), m(mini 128x128), l(large 256x256), xl(extralarge 512x512) 
     * */
    public static BufferedImage createStandartImage(BufferedImage image,
            String sizeMark, String type) throws IOException {

        BufferedImage finalImage = null;
        int size = 0;

        size = conf.getInt(sizeMark);

        if(type.equals("png") || type.equals("gif")){
            finalImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        } else {
            finalImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics g = finalImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size, size);
        }

        if (image.getHeight() == image.getWidth()) {
            image = createResizedCopy(image, size, size, true);
            finalImage.getGraphics().drawImage(image, 0, 0, null);
        } else

        if (image.getWidth() > image.getHeight()) {
            double scale = (double) image.getWidth() / size;
            image = createResizedCopy(image, size, (int) ((double) image.getHeight() / scale), true);
            finalImage.getGraphics().drawImage(image, 0, (int) ((double) finalImage.getHeight() - image.getHeight()) / 2, null);
        } else

        if (image.getWidth() < image.getHeight()) {
            double scale = (double) image.getHeight() / size;
            image = createResizedCopy(image, (int) ((double) image.getWidth() / scale), size, true);
            finalImage.getGraphics().drawImage(image, (int) ((double) finalImage.getWidth() - image.getWidth()) / 2, 0, null);
        }

        return finalImage;
    }
}
