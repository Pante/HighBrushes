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

import com.karusmc.highbrushes.HighBrushes;
import com.karusmc.highbrushes.io.ConfigHandler;
import com.karusmc.highbrushes.listeners.PlayerHandler;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.util.stream.IntStream;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class PaintBrush {
    
    // Fields
    private Brush brush;
    private int size;
    
    private boolean enabled;
    
    private boolean autoRotation;
    private boolean boundingBox;
    private boolean flat;
    private boolean mountain;
    
    
    public PaintBrush() {
        
        brush = new Brush();
        
        enabled = true;
        autoRotation = ConfigHandler.getDefaultAutoRotation();
        boundingBox = ConfigHandler.getDefaultBoundingBox();
        flat = ConfigHandler.getDefaultFlat();
        mountain = ConfigHandler.getDefaultMountain();
        
    }
    
    public PaintBrush(boolean enabled) {
        
        brush = new Brush();
        
        this.enabled = enabled;
        autoRotation = ConfigHandler.getDefaultAutoRotation();
        boundingBox = ConfigHandler.getDefaultBoundingBox();
        flat = ConfigHandler.getDefaultFlat();
        mountain = ConfigHandler.getDefaultMountain();
        
    }
     
    public PaintBrush(Brush brush) {
        
        this.brush = brush;
        
        enabled = true;
        autoRotation = ConfigHandler.getDefaultAutoRotation();
        boundingBox = ConfigHandler.getDefaultBoundingBox();
        flat = ConfigHandler.getDefaultFlat();
        mountain = ConfigHandler.getDefaultMountain();
        
    }
    
    public PaintBrush(Brush brush, boolean enabled, boolean autoRotation, boolean boundingBox, boolean flat, boolean mountain) {
        
        this.brush = brush;
        
        this.enabled = enabled;
        this.autoRotation = autoRotation;
        this.boundingBox = boundingBox;
        this.flat = flat;
        this.mountain = mountain;
        
    }
    
    
    public double getHeight(Location location, int x, int z) {
        
        size = brush.getCachedImage().getHeight();
        
        int a = x;
        int b = z;
        
        if (autoRotation) {
            switch (Direction.caculate(location)) {
                case EAST:
                    x = b;
                    z = size - a;
                    break;
                    
                case SOUTH:
                    x = size - a;
                    z = size - b;
                    break;
                
                case WEST:
                    x = size - b;
                    z = a;
            }
        }
        
        double height = calHeight(x, z);
        
        if (mountain) {
            
            height = height / 10.0 * 1.5;
            
            height += calHeight(new int[] {a -1, a + 1, a, a}, new int[] {b, b, b- 1, b + 1}, 10.0);
            
            height += calHeight(new int[]{
                a + 1, a - 1, a + 1, a - 1,
                a - 2, a - 2, a - 2,
                a + 2, a + 2, a + 2,
                a - 1, a, a + 1,
                a - 1, a, a + 1
            }, new int[]{
                b + 1, b + 1, b - 1, b - 1,
                b - 1, b, b + 1,
                b - 1, b, b + 1,
                b - 2, b - 2, b - 2,
                b + 2, b + 2, b + 2
            }, 20.0);

            height /= 1.35;
            height += - Math.pow(height / brush.getIntensity() * 0.45, 2.0) + 0.2;
            
        } 
        
        return height;
        
    }
    
    
    // Helper methods
    
    private double calHeight(int x, int z) {
        
        int rgb = 0;
        if ((z < size) && (x < size) && (x >= 0) && (z >= 0)) {
          rgb = brush.getCachedImage().getRGB(x, z);
        }
        int red = rgb >>> 16 & 0xFF;
        int green = rgb >>> 8 & 0xFF;
        int blue = rgb & 0xFF;
        
        int sum = (red + blue + green);
        
        if (sum == 0) {
          return 0.0;
        }
        
        double grayScale = sum / 3.0 / 255.0;

        return grayScale * brush.getIntensity();
        
    }
    
    private double calHeight(int[] x, int[] z, double div) {
        
        return IntStream.range(0, x.length).mapToDouble(i -> {
            int rgb = 0;
            if ((z[i] < size) && (x[i] < size) && (x[i] >= 0) && (z[i] >= 0)) {
              rgb = brush.getCachedImage().getRGB(x[i], z[i]);
            }
            int red = rgb >>> 16 & 0xFF;
            int green = rgb >>> 8 & 0xFF;
            int blue = rgb & 0xFF;

            int sum = (red + blue + green);

            if (sum == 0) {
              return 0.0;
            }
            
            double grayScale = sum / 3.0 / 255.0;

            return (grayScale * brush.getIntensity()) / div;
        }).sum();
        
    }
    
    
    public Location getHighestLoc(Player player, Location location) {
        
        if (PlayerHandler.PLAYERS.get(player.getUniqueId()).isBoundingBox()) {
            Selection selection = HighBrushes.instance.getWorldEdit().getSelection(player);
            if (selection != null) {
                location.setY(selection.getMaximumPoint().getBlockY());
            }
            return location;
        }
        location.setY(256.0);
        return location;
  }
    
    
    // <------ Getter & Setter methods ------>
    
    public Brush getBrush() {
        return brush;
    }
    
    public void setBrush(Brush brush) {
        this.brush = brush;
    }
    
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
    public boolean isAutoRotation() {
        return autoRotation;
    }
    
    public void setAutoRotation(boolean enabled) {
        autoRotation = enabled;
    }
    
    
    public boolean isBoundingBox() {
        return boundingBox;
    }
    
    public void setBoundingBox(boolean enabled) {
        boundingBox = enabled;
    }
    
    
    public boolean isFlat() {
        return flat;
    }
    
    public void setFlat(boolean enabled) {
        flat = enabled;
    }
    
    
    public boolean isMoutain() {
        return mountain;
    }
    
    public void setMountain(boolean enabled) {
        mountain = enabled;
    }
    
}
