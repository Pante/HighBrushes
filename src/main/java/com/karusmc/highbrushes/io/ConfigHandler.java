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

import com.karusmc.highbrushes.HighBrushes;
import java.io.File;
import java.util.ArrayList;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class ConfigHandler {
    
    private static int maxUndos;
    private static int blockRate;
    private static int delay;
    
    private static ArrayList<String> disabledWorlds;
    
    
    private static boolean autoRotation;
    private static boolean boundingBox;
    private static boolean flat;
    private static boolean mountain;
    
    
    private ConfigHandler() {}
    
    public static void load(Output<String, Exception> output) {
        
        if (!new File(HighBrushes.instance.getDataFolder(), "config.yml").exists()) {
            output.out("config.yml not found, creating!", null);
            HighBrushes.instance.saveDefaultConfig();
        } else {
            output.out("config.yml found, loading!", null);
        }
        
        FileConfiguration config = HighBrushes.instance.getConfig();
        
    
        maxUndos = config.getInt("max-undos", 50);
        blockRate = config.getInt("block-rate", 250);
        delay = config.getInt("delay", 2);
        
        disabledWorlds = new ArrayList<>(config.getStringList("disabled-worlds"));
        
        autoRotation = config.getBoolean("auto-rotation", false);
        boundingBox = config.getBoolean("bounding-box", false);
        flat = config.getBoolean("flat", false);
        mountain = config.getBoolean("mountain", false);
        
    }
    
    
    // <------ Getter & Setter methods ------>
    public static int getMaxUndos() {
        return maxUndos;
    }
    
    public static void setMaxUndos(int max) {
        maxUndos = max;
    }
    
    
    public static int getBlockRate() {
        return blockRate;
    }
    
    public static void setBlockRate(int rate) {
        blockRate = rate;
    }
    
    
    public static int getDelay() {
        return delay;
    }
    
    public static void setDelay(int d) {
        delay = d;
    }
    
    
    public static ArrayList<String> getDisabledWorlds() {
        return disabledWorlds;
    }
    
    public static void setDisabledWorlds(ArrayList<String> worlds) {
        disabledWorlds = worlds;
    }
    
    
    public static boolean getDefaultAutoRotation() {
        return autoRotation;
    }
    
    public static void setDefaultAutoRotation(boolean enabled) {
        autoRotation = enabled;
    }
    
    
    public static boolean getDefaultBoundingBox() {
        return boundingBox;
    }
    
    public static void setDefaultBoundingBox(boolean enabled) {
        boundingBox = enabled;
    }
    
    
    public static boolean getDefaultFlat() {
        return flat;
    }
    
    public static void setDefaultFlat(boolean enabled) {
        flat = enabled;
    }
    
    
    public static boolean getDefaultMountain() {
        return mountain;
    }
    
    public static void setDefaultMountain(boolean enabled) {
        mountain = enabled;
    }
   
}
