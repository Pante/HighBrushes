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
package com.karusmc.raster.painter;

import com.karusmc.raster.brushes.Brush;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class Painter {
    
    private Brush brush;
    
    private boolean autoRotation;
    private boolean boxBounded;
    
    private boolean flat;
    private boolean mountain;
    
    
    public Painter(Brush brush) {
        this(brush, false, false, false, false);
    }
    
    public Painter(Brush brush, boolean autoRotation, boolean boxBounded, boolean flat, boolean mountain) {
        this.brush = brush;
        
        this.autoRotation = autoRotation;
        this.boxBounded = boxBounded;
        this.flat = flat;
        this.mountain = mountain;
    }
    
    
    public Brush getBrush() {
        return brush;
    }
    
    public void setBrush(Brush brush) {
        this.brush = brush;
    }
    
    
    public boolean isAutoRotation() {
        return autoRotation;
    }
    
    public void setAutoRotation(boolean autoRotation) {
        this.autoRotation = autoRotation;
    }
    
    
    public boolean isBoxBounded() {
        return boxBounded;
    }
    
    public void setBoxBounded(boolean boxBounded) {
        this.boxBounded = boxBounded;
    }
    
    
    public boolean isFlat() {
        return flat;
    }
    
    public void setFlat(boolean flat) {
        this.flat = flat;
    }
    
    
    public boolean isMountain() {
        return mountain;
    }
    
    public void setMountain(boolean mountain) {
        this.mountain = mountain;
    }
    
}
