/*
 * Copyright (C) 2016 PanteLegacy @ karusmc.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.karusmc.raster.brushes;

import java.awt.Graphics2D;
import java.awt.image.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class Brush {
    
    private static final int[][] WEIGHTAGE = new int[][] {
        {0, 1, 1, 1, 0},
        {1, 1, 2, 1, 1},
        {1, 2, 3, 2, 1},
        {1, 1, 2, 1, 1},
        {0, 1, 1, 1, 0}
    };
    
    
    private String name;
    private BufferedImage image;
    private double[][] cachedGrayscale;
    private int intensity;
    
    
    public Brush(String name, BufferedImage image, int size, int intensity) {
        this.name = name;
        this.image = image;
        setSize(size);
        this.intensity = intensity;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setReferenceImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
        setSize(cachedGrayscale.length);
    }
    
    
    public int getSize() {
        return cachedGrayscale.length;
    }
    
    public void setSize(int size) {
        BufferedImage resized = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = resized.createGraphics();
        graphics.drawImage(image, 0, 0, size, size, null);
        graphics.dispose();
        
        cache(resized);
    }
    
    
    private void cache(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        double[][] result = new double[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                
                int rgb = image.getRGB(col, row);
                
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int sum = red + blue + green;

                if (sum == 0) {
                    result[row][col] = 0;
                    
                } else {
                    result[row][col] = sum / 3.0 / 255.0;
                }
            }
        }

        cachedGrayscale = result;
    }
    
    
    public int getIntensity() {
        return intensity;
    }
    
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    
    public double getHeight(int x, int z) {
        return cachedGrayscale[x][z] * intensity;
    }
    
    public double getSmoothHeight(int originalX, int originalZ) {
        double height = 0;
        
        for (int x = Math.max(originalX - 2, 0); x < Math.min(originalX + 3, cachedGrayscale.length); x++) {
            int offsetX = originalX - x + 2;
            
            for (int z = Math.max(originalZ - 2, 0); z < Math.min(originalZ + 3, cachedGrayscale.length); z++) {
                int offsetZ = originalZ - z + 2;
                height += cachedGrayscale[x][z] * WEIGHTAGE[offsetX][offsetZ];
            }
        }

        height /= 27;
        return height * intensity - Math.pow(height * 0.45, 2) + 0.2;
    }
    
}
