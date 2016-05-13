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
package com.karusmc.highbrushes.io;

import com.google.common.io.Files;

import com.karusmc.highbrushes.HighBrushes;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class BrushHandler {
    
    // Fields
    private static final File FOLDER = new File(HighBrushes.instance.getDataFolder(), "brushes");
    private static HashMap<String, BufferedImage> brushes = new HashMap<>();
    
    
    private static String defaultBrush;
    
    
    private static int defaultSize;
    private static int maxSize;
    
    private static int defaultIntensity;
    private static int maxIntensity;
    
    private BrushHandler() {}
    
    
    public static void loadBrushes(Output<String, Exception> out) {
        new BukkitRunnable() {
            @Override
            public void run() {
                
                if (!FOLDER.exists() || !FOLDER.isDirectory()) {
                    FOLDER.mkdirs();
                }
                
                File defaultFile = new File(FOLDER, "circle.png");
                if (!defaultFile.exists()) {
                    try (InputStream inputStream = new BufferedInputStream(HighBrushes.instance.getResource("circle.png")); 
                            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(defaultFile))) {
                        
                        IOUtils.copy(inputStream, outputStream);
                        
                    } catch (IOException e) {
                        out.out("Failed to retrieve circle.png from jar!", e);
                    }
                }
                
                HashMap<String, BufferedImage> temp = new HashMap<>();
                new ArrayList<>(Arrays.asList(FOLDER.listFiles())).stream().forEach(file -> {
                    try {
                        BufferedImage image = ImageIO.read(file);
                        temp.put(Files.getNameWithoutExtension(file.getName()), image);
                        out.out(ChatColor.RED + "Loaded brush: " + Files.getNameWithoutExtension(file.getName()), null);
                    } catch (IOException e) {
                        out.out(ChatColor.RED + "Failed to load brush: " + Files.getNameWithoutExtension(file.getName()), e);
                    }
                });
                
                synchronized(brushes) {
                    brushes = new HashMap<>(temp);
                }
                
            }
        }.runTaskAsynchronously(HighBrushes.instance);
    }
    
    public static String loadBrush(String name) {
        
        File file = new File(FOLDER, name + ".png");
        
        if (file.exists()) {
            try {
                BufferedImage image = ImageIO.read(file);
                brushes.put(Files.getNameWithoutExtension(file.getName()), image);
                return ChatColor.RED + "Loaded brush: " + Files.getNameWithoutExtension(file.getName());
            } catch (IOException e) {
                return ChatColor.RED + "Failed to load brush: " + Files.getNameWithoutExtension(file.getName());
            }
        } else {
            return ChatColor.RED + "Could not find brush. Is it a .png file?";
        }
        
    }
    
    
    public static void loadDefaults(Output<String, Exception> out) {
        
        FileConfiguration config = HighBrushes.instance.getConfig();
        
        defaultBrush = config.getString("brushes.default-brush-name", "circle.png");
        
        File file = new File(FOLDER, defaultBrush);
        if (!file.exists()) {
            out.out(ChatColor.RED + "Default brush could not be loaded", new Exception());
        }
        
        defaultSize = config.getInt("brushes.default-size", 1);
        maxSize = config.getInt("brushes.max-size", 10);
        
        defaultIntensity = config.getInt("brushes.default-intensity", 5);
        maxIntensity = config.getInt("brushes.max-intensity", 10);
        
    }
    
    
    // <------ Getter & Setter methods ------>
    public static HashMap<String, BufferedImage> getBrushes() {
        if (brushes == null) {
            brushes = new HashMap<>();
        }
        return brushes;
    }
    
    public static void setBrushes(HashMap<String, BufferedImage> brushes) {
        BrushHandler.brushes = brushes;
    }
    
    
    public static String getDefaultBrush() {
        return defaultBrush;
    }
    
    public static void setDefaultBrush(String brush) {
        defaultBrush = brush;
    }
    
    
    public static int getDefaultSize() {
        return defaultSize;
    }
    
    public static void setDefaultSize(int size) {
        defaultSize = size;
    }
    
    
    public static int getMaxSize() {
        return maxSize;
    }
    
    public static void setMaxSize(int size) {
        maxSize = size;
    }
    
    
    public static int getDefaultIntensity() {
        return defaultIntensity;
    }
    
    public static void setDefaultIntensity(int intensity) {
        defaultIntensity = intensity;
    }
    
    
    public static int getMaxIntensity() {
        return maxIntensity;
    }
    
    public static void setMaxIntensity(int intensity) {
        maxIntensity  = intensity;
    }
    
}
