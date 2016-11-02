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

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class BrushFlyweight {
    
    private Map<String, BufferedImage> images;
    
    
    public BrushFlyweight() {
        this(new HashMap<>());
    }
    
    public BrushFlyweight(Map<String, BufferedImage> images) {
        this.images = images;
    }
    
    
    public void load(File file) {
        try {
            images.put(file.getName().replaceAll(" ", "-").replaceAll("[.][^.]+$", ""), grayscaleImage(ImageIO.read(file)));

        } catch (IOException e) {
            throw new BrushException("Failed to load file: " + file.getName(), e);
        }
    }
    
    private BufferedImage grayscaleImage(BufferedImage image) {
        int type = image.getType();
        if (type != BufferedImage.TYPE_USHORT_GRAY && type != BufferedImage.TYPE_BYTE_GRAY) {
            BufferedImage grayscaledImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            
            Graphics g = grayscaledImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            
            return grayscaledImage;

        } else {
            return image;
        }
    }
    
    public Map<String, BufferedImage> getBrushes() {
        return images;
    }
    
}
