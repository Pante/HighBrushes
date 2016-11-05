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
import java.io.*;
import javax.imageio.ImageIO;

import junitparams.*;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
@RunWith(JUnitParamsRunner.class)
public class BrushTest {
    
    private Brush brush;
    private BufferedImage image;
    
    
    public BrushTest() throws IOException{
        image = ImageIO.read(getClass().getClassLoader().getResource("brushes/TestImage1.png"));
        brush = new Brush("", image, 10, 1);
    }
    
    
    @Before
    public void setup() {
        brush.setReferenceImage("", image);
        brush.setSize(10);
        brush.setIntensity(1);
    }
    
    @Test
    @Parameters({"1", "5", "3"})
    public void setSize(int size){
        brush.setSize(size);
        
        assertEquals(size, brush.getSize());
        assertEquals(size, brush.getSize());
    }
    
    
    @Test
    public void setReferenceImage_caches() throws IOException {
        brush.setReferenceImage("", ImageIO.read(getClass().getClassLoader().getResource("brushes/TestImage2.png")));
        assertEquals(0.8980392, brush.getHeight(0, 0), 0.00001);
    }
    
    
    @Test
    public void getHeight() {
        brush.setIntensity(2);
        assertEquals(1.5843137, brush.getHeight(0, 0), 0.00001);
    }
    
    
    @Test
    @Parameters({"0, 0, 0.526969", "9, 9, 0.526969", "0, 9, 0.526969", "9, 0, 0.526969", "5, 5, 0.86508"})
    public void getSmoothenHeight(int x, int z, double expected) {
        System.out.print(brush.getSize());
        assertEquals(expected, brush.getSmoothHeight(x, z), 0.00001);
    }
    
}
