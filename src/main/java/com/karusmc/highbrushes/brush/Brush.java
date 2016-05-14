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
package com.karusmc.highbrushes.brush;

import com.karusmc.highbrushes.io.BrushHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class Brush {
    
    // Fields
    private String type;
    private BufferedImage image;
    
    private BufferedImage cachedImage;
    
    private int size;
    private int intensity;
    
    public Brush() {
        
        type = BrushHandler.getDefaultBrush();
        image = BrushHandler.getBrushes().get(BrushHandler.getDefaultBrush());
        
        size = BrushHandler.getDefaultSize();
        intensity = BrushHandler.getDefaultIntensity();
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    public Brush(String type, BufferedImage image, int size, int intensity) {
        
        this.type = type;
        this.image = image;
        
        this.size = size;
        this.intensity = intensity;
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    // <------ Getter & Setter methods ------>
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    
    public BufferedImage getCachedImage() {
        return cachedImage;
    }
    
    public void setCachedImage(BufferedImage image) {
        this.cachedImage = image;
    }
    
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    
    public int getSize() {
        return size;
    }
    
    public void setSize(int size) {
        this.size = size;
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    
    public int getIntensity() {
        return intensity;
    }
    
    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
    
}
