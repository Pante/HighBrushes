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

import org.bukkit.Location;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class DirectionTest {
    
    @Test
    public void testGetDirectionReturnsNorth() {
        
        Location location = Mockito.mock(Location.class);
        when(location.getYaw()).thenReturn((float) 180.0);
        
        assertEquals(Direction.NORTH, Direction.getDirection(location.getYaw()));
        
    }
    
    @Test
    public void testGetDirectionReturnsEast() {
        
        Location location = Mockito.mock(Location.class);
        when(location.getYaw()).thenReturn((float) -90.0);
        
        assertEquals(Direction.EAST, Direction.getDirection(location.getYaw()));
        
    }
    
    @Test
    public void testGetDirectionReturnsSouth() {
        
        Location location = Mockito.mock(Location.class);
        when(location.getYaw()).thenReturn((float) 0.0);
        
        assertEquals(Direction.SOUTH, Direction.getDirection(location.getYaw()));
        
    }
    
    @Test
    public void testGetDirectionReturnsWest() {
        
        Location location = Mockito.mock(Location.class);
        when(location.getYaw()).thenReturn((float) 90.0);
        
        assertEquals(Direction.WEST, Direction.getDirection(location.getYaw()));
        
    }
    
}
