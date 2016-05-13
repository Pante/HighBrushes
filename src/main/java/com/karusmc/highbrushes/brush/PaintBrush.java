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

import com.karusmc.highbrushes.io.ConfigHandler;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class PaintBrush {
    
    // Fields
    private Brush brush;
    
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
