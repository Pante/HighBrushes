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

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public enum Direction {
    
    NORTH, SOUTH, EAST, WEST;
    
    public static Direction caculate(Location location) {
        
        double rotation = (location.getYaw() - 90.0) % 360.0;
        
        if (rotation < 0.0) {
          rotation += 360.0;
        }

        if (rotation < 45.0) {
          return WEST;
        }
        else if (rotation < 135.0) {
          return NORTH;
        }
        else if (rotation < 225.0) {
          return EAST;
        }
        else if (rotation < 315.0) {
          return SOUTH;
        }
        else {
          return WEST;
        }
        
    }
    
}
