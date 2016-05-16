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
 * Utility class that loads setting from config.yml.
 */
public class ConfigHandler {
    
    // Fields
    private static int maxUndos;
    
    private static ArrayList<String> disabledWorlds;
    
    
    private static boolean autoRotate;
    private static boolean boxBounded;
    private static boolean flat;
    private static boolean mountain;
    
    
    private ConfigHandler() {}
    
    /** Loads the default variables from config.yml.
     * @param output An output format.
    */
    public static void loadDefaults(Output<Exception> output) {
        
        if (!new File(HighBrushes.instance.getDataFolder(), "config.yml").exists()) {
            output.out("config.yml not found, creating!", null);
            HighBrushes.instance.saveDefaultConfig();
        } else {
            output.out("config.yml found, loading!", null);
        }
        
        FileConfiguration config = HighBrushes.instance.getConfig();
        
    
        maxUndos = config.getInt("max-undos", 50);
        
        disabledWorlds = new ArrayList<>(config.getStringList("disabled-worlds"));
        
        autoRotate = config.getBoolean("auto-rotation", false);
        boxBounded = config.getBoolean("bounding-box", false);
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
    
    public static ArrayList<String> getDisabledWorlds() {
        return disabledWorlds;
    }
    
    public static void setDisabledWorlds(ArrayList<String> worlds) {
        disabledWorlds = worlds;
    }
    
    
    public static boolean getDefaultAutoRotate() {
        return autoRotate;
    }
    
    public static void setDefaultAutoRotate(boolean enabled) {
        autoRotate = enabled;
    }
    
    
    public static boolean getDefaultBoxBounded() {
        return boxBounded;
    }
    
    public static void setDefaultBoxBounded(boolean enabled) {
        boxBounded = enabled;
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
