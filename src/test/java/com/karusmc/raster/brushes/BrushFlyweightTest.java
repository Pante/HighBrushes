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
package com.karusmc.raster.brushes;

import java.awt.image.BufferedImage;
import java.io.File;

import junitparams.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
@RunWith(JUnitParamsRunner.class)
public class BrushFlyweightTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    private BrushFlyweight flyweight;
    private File folder = new File(getClass().getClassLoader().getResource("brushes/").getPath());
    
    public BrushFlyweightTest() {
        flyweight = new BrushFlyweight();
    }
    
    
    @Before
    public void clear() {
        flyweight.getBrushes().clear();
    }
    
    
    @Test
    @Parameters({"no-such-brush.png", "china mountain 1"})
    public void load_ThrowsException(String fileName) {
        exception.expect(BrushException.class);
        exception.expectMessage("Failed to load file: " + fileName);
        
        flyweight.load(new File(folder, fileName));
    }
    
    
    @Test
    @Parameters({"china mountain 1.png, china-mountain-1", "circle.png, circle", "banner.jpg, banner"})
    public void load(String fileName, String name) {
        flyweight.load(new File(folder, fileName));
        BufferedImage image = flyweight.getBrushes().get(name);
        assertTrue(image.getType() == BufferedImage.TYPE_USHORT_GRAY || image.getType() == BufferedImage.TYPE_BYTE_GRAY);
    }
}
