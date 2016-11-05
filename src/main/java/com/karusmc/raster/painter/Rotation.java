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

import org.bukkit.util.Vector;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class Rotation {
    
    public static Vector rotate(float yaw, int size, Vector vector) {
        int normalizedYaw = Math.round(yaw / 90);
        
        int x = vector.getBlockX();
        int z = vector.getBlockZ();
        
        switch (normalizedYaw) {
            case -1:
                vector.setX(z);
                vector.setZ(size - x);
                break;
                
            case 0:
                vector.setX(size - x);
                vector.setZ(size - z);
                break;
                
            case 1:
                vector.setX(size - z);
                vector.setZ(x);
                break;
        }

        return vector;
        
    }
    
}
