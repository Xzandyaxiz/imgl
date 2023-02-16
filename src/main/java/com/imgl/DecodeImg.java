package com.imgl;

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DecodeImg {

    public ArrayList<int[]> GetImageProportions(String path) {
        ArrayList<int[]> result = new ArrayList<int[]>();

        try {
            File inputFile = new File(path);
            BufferedImage inputImage = ImageIO.read(inputFile);

            int outputWidth = 64;
            int outputHeight = 64;

            double scaleX = (double) outputWidth / inputImage.getWidth();
            double scaleY = (double) outputHeight / inputImage.getHeight();

            for (int y = 0; y < outputHeight; y++) {
                for (int x = 0; x < outputWidth; x++) {
                    int sourceX = (int) (x / scaleX);
                    int sourceY = (int) (y / scaleY);
                    int rgb = inputImage.getRGB(sourceX, sourceY);

                    int[] pixel = new int[]{ x, y, rgb & 0xFFFFFF };
                    result.add(pixel);
                }
            }

        } catch (Exception e) {
            return result;
        }

        return result;
    }
}
