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

/**
 *
 * @author PanteLegacy @ karusmc.com
 * A representation of a player's direction in-game.
 */
public enum Direction {
    
    NORTH, EAST, SOUTH, WEST;
    
    /** Calculates the direction a player's facing based on his/her yaw.
     * @param yaw A player's yaw.
     * @return The direction a player's facing.
    */
    public static Direction getDirection(float yaw) {
        
        if (yaw >= 135 || yaw < -135) {
            return NORTH;
        } else if (yaw < -45) {
            return EAST;
        } else if (yaw < 45) {
            return SOUTH;
        } else {
            return WEST;
        }
        
    }
    
}
