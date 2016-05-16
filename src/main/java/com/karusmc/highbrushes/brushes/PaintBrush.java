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
package com.karusmc.highbrushes.brushes;

import com.karusmc.highbrushes.io.BrushHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * A representation of an in-game brush that is attached to a player.
 */
public class PaintBrush {
    
    // Fields
    private Palette palette;
    private String type;
    private BufferedImage image;
    private BufferedImage cachedImage;
    
    private boolean enabled;
    
    private int size;
    private int intensity;
    
    
    /** Creates a new instance of PaintBrush with default variables specified in the config.yml. */
    public PaintBrush() {
        
        palette = new Palette();
        type = BrushHandler.getDefaultBrush();
        image = BrushHandler.getBrushes().get(BrushHandler.getDefaultBrush());
        
        enabled = true;
        size = BrushHandler.getDefaultSize();
        intensity = BrushHandler.getDefaultIntensity();
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    /** Creates a new instance of a PaintBrush with custom variable,
     * @param type The name of the brush type,
     * @param image The brush image.
     * @param enabled Sets whether the brush is enabled/disabled by default.
     * @param size Sets the brush size.
     * @param intensity Sets the brush intensity.*/
    public PaintBrush(String type, BufferedImage image, boolean enabled, int size, int intensity) {
        
        palette = new Palette();
        this.type = type;
        this.image = image;
        
        this.enabled = enabled;
        this.size = size;
        this.intensity = intensity;
        
        BufferedImage resizedImage = new BufferedImage(size, size, 1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, size, size, null);
        g.dispose();
        cachedImage = resizedImage;
        
    }
    
    // <------ Getter & Setter methods ------>
    
    public Palette getPalette() {
        return palette;
    }
    
    public void setPalette(Palette palette) {
        this.palette = palette;
    }
    
    
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
    
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
    
    /** Calculates the height to increase the in-game region based on the brush image.
     * 
     * @param yaw The player's yaw.
     * @param x The x-coordinate of the picture.
     * @param z The z-coordinate of the picture.
     * @return The height to increase the in-game region by
     */
    public double getHeight(float yaw, int x, int z) {
        size = cachedImage.getHeight();
        int a = x;
        int b = z;

        // Inefficent, move this portion out
        if (palette.isAutoRotate()) {
            switch (Direction.getDirection(yaw)) {
                case SOUTH:
                    x = size - a;
                    z = size - b;
                    break;
                case WEST:
                    x = size - b;
                    z = a;
                    break;
                case EAST:
                    x = b;
                    z = size - a;
                    break;
                default:
                    break;
            }
        }
        //

        double height = calHeight(x, z);

        if (!palette.isMountain()) {
            return height;
        }

        // Distorts, decreases/increases height based on pixel values next to it
        height = height / 10.0D * 1.5D;

        // Affection depends on pixel distance from original
        // The further the lower the weight. E.g pixel next to it will be / 10 while pixel 2 pixels away will be / 20
        height += calHeight(a - 1, b) / 10.0;
        height += calHeight(a + 1, b) / 10.0;
        height += calHeight(a, b - 1) / 10.0;
        height += calHeight(a, b + 1) / 10.0;

        height += calHeight(a + 1, b + 1) / 20.0;
        height += calHeight(a - 1, b + 1) / 20.0;
        height += calHeight(a + 1, b - 1) / 20.0;
        height += calHeight(a - 1, b - 1) / 20.0;

        height += calHeight(a - 2, b - 1) / 20.0;
        height += calHeight(a - 2, b) / 20.0;
        height += calHeight(a - 2, b + 1) / 20.0;

        height += calHeight(a + 2, b - 1) / 20.0;
        height += calHeight(a + 2, b) / 20.0D;
        height += calHeight(a + 2, b + 1) / 20.0;

        height += calHeight(a - 1, b - 2) / 20.0;
        height += calHeight(a, b - 2) / 20.0D;
        height += calHeight(a + 1, b - 2) / 20.0;

        height += calHeight(a - 1, b + 2) / 20.0;
        height += calHeight(a, b + 2) / 20.0D;
        height += calHeight( a + 1, b + 2) / 20.0;

        height /= 1.35;
        height += -Math.pow(height / intensity * 0.45, 2.0) + 0.2;
        return height;
    }
    
    
    // Helper methods
    
    private double calHeight(int x, int z) {
        
        int rgb = 0;
        if ((z < size) && (x < size) && (x >= 0) && (z >= 0)) {
          rgb = cachedImage.getRGB(x, z);
        }
        int red = rgb >>> 16 & 0xFF;
        int green = rgb >>> 8 & 0xFF;
        int blue = rgb & 0xFF;
        
        int sum = (red + blue + green);
        
        if (sum == 0) {
          return 0.0;
        }
        
        double grayScale = sum / 3.0 / 255.0;

        return grayScale * intensity;
        
    }
    
}
