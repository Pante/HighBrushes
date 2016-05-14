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
package com.karusmc.highbrushes;

import com.karusmc.highbrushes.commands.*;
import com.karusmc.highbrushes.brush.commands.*;
import com.karusmc.highbrushes.io.BrushHandler;
import com.karusmc.highbrushes.io.ConfigHandler;
import com.karusmc.highbrushes.io.Output;
import com.karusmc.highbrushes.listeners.Paint;
import com.karusmc.highbrushes.listeners.PlayerHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class HighBrushes extends JavaPlugin {
    
    // Fields
    public static HighBrushes instance;
    private WorldEditPlugin we;
    
    public Output<String, Exception> log = (message, exception) -> {
        if (exception == null) {
            getLogger().info(ChatColor.stripColor(message));
        } else {
            getLogger().severe(ChatColor.stripColor(message));
        }
    };
    
    
    @Override
    public void onEnable() {
        instance = this;
        we = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        
        ConfigHandler.load(log);
        BrushHandler.loadDefaults(log);
        BrushHandler.loadBrushes(log);
        
        registerCommands();
        registerEvents();
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    
    // <--- Helper methods for registering commands & events --->

    private void registerCommands() {
        MainCommand command = new MainCommand();
        
        command.registerSubcommand("highbrushes about", new AboutSubcommand());
        command.registerSubcommand("highbrushes help", new HelpSubcommand());
        command.registerSubcommand("highbrushes list", new ListSubcommand());
        command.registerSubcommand("highbrushes reload", new ReloadSubcommand());
        command.registerSubcommand("highbrushes toggle", new ToggleSubcommand());
        
        command.registerSubcommand("highbrushes info", new InfoSubcommand());
        command.registerSubcommand("highbrushes intensity", new IntensitySubcommand());
        command.registerSubcommand("highbrushes brush", new SelectSubcommand());
        command.registerSubcommand("highbrushes size", new SizeSubcommand());
        command.registerSubcommand("highbrushes undo", new UndoSubcommand());
        
        getCommand("highbrushes").setExecutor(command);
        
    }


    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new Paint(), this);
        getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
    }
    
    public WorldEditPlugin getWorldEdit() {
        return we;
    }
    
}
