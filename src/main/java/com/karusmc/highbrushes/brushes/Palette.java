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

import com.karusmc.highbrushes.HighBrushes;
import com.karusmc.highbrushes.io.ConfigHandler;
import com.sk89q.worldedit.bukkit.selections.Selection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;


/**
 *
 * @author PanteLegacy @ karusmc.com
 * Class that contains the brush options.
 */
public class Palette {
    
    // Fields
    private boolean autoRotate;
    private boolean boxBounded;
    
    private boolean flat;
    private boolean mountain;
    
    /** Creates a palette with default variables specified in the config.yml. */
    public Palette() {
        
        autoRotate = ConfigHandler.getDefaultAutoRotate();
        boxBounded = ConfigHandler.getDefaultBoxBounded();
        
        flat = ConfigHandler.getDefaultFlat();
        mountain = ConfigHandler.getDefaultMountain();
        
    }
    
    // <------ Getter & Setter methods ------>

    public boolean isAutoRotate() {
        return autoRotate;
    }
    
    public void setAutoRotate(boolean rotate) {
        autoRotate = rotate;
    }
    
    
    public boolean isBoxBounded() {
        return boxBounded;
    }
    
    public void setBoxBounded(boolean bounded) {
        boxBounded = bounded;
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
    
    /** Calculates the highest possible Y value of a given location.
     * @param p A player
     * @param loc The location to be calculated on.
     * @return The modified @param loc.
     * 
    */
    public Location getHighestLoc(Player p, Location loc) {
        
        int max = 256;
        
        if (boxBounded) {
            Selection selection = HighBrushes.instance.getWorldEdit().getSelection(p);
            if (selection != null) {
                max = selection.getMaximumPoint().getBlockY();
            } else return loc;
        }
        
        loc.setY(max);
        
        while (loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.WATER || loc.getBlock().getType() == Material.LAVA  
                  || loc.getBlock().getType() == Material.STATIONARY_WATER || loc.getBlock().getType() == Material.STATIONARY_LAVA) {

            loc.setY(loc.getBlockY() - 1);
            if (loc.getBlockY() == -1) {
                loc.setY(max);
                break;
            }
        }
        
        return loc;
        
    }
    
}
