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
    
    private BufferedImage image;
    private float[][] cachedGrayscale;
    
    
    public Brush(BufferedImage image, int size) {
        this.image = image;
        cachedGrayscale = cache(image);
    }
    
    
    public void resize(int size) {
        BufferedImage resized = new BufferedImage(size, size, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = resized.createGraphics();
        graphics.drawImage(image, 0, 0, size, size, null);
        graphics.dispose();
        
        cache(resized);
    }
    
    private float[][] cache(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        float[][] result = new float[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int rgb = image.getRGB(col, row);
    
                int red = rgb >>> 16 & 0xFF;
                int green = rgb >>> 8 & 0xFF;
                int blue = rgb & 0xFF;

                int sum = red + blue + green;

                if (sum == 0) {
                    result[row][col] = sum;
                } else {
                    result[row][col] = (float) (sum / 3.0 / 255.0);
                }
            }
        }

        return result;
    }
    
    
    public float[][] getCache() {
        return cachedGrayscale;
    }
    
}
