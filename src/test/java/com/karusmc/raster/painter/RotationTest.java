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

import junitparams.*;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
/**
 *
 * @author PanteLegacy @ karusmc.com
 */
@RunWith(JUnitParamsRunner.class)
public class RotationTest {
    
    private Vector vector;
    private int size;
    
    
    public RotationTest() {
        vector = new Vector();
        size = 10;
    }
    
    
    @Before
    public void setup() {
        vector.setX(1);
        vector.setZ(2);
    }
    
    
    @Test
    @Parameters({"174, 1, 2", "-47, 2, 9", "44, 9, 8", "110, 8, 1"})
    public void rotate(float yaw, int expectedX, int expectedZ) {
        Rotation.rotate(yaw, size, vector);
        
        assertEquals(expectedX, vector.getBlockX());
        assertEquals(expectedZ, vector.getBlockZ());
        assertEquals(0, vector.getBlockY());
    }
    
    
}
