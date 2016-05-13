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
package com.karusmc.highbrushes.commands;

import com.karusmc.highbrushes.HighBrushes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class MainCommand implements CommandExecutor {
    
    // Fields
    public static final HashMap<String, Subcommand> COMMANDS = new HashMap<>();
    
    private HashMap<String, Subcommand> subcommands = new HashMap<>();

    
    // Registers the method in the hashmap
    public void registerSubcommand(String fullCommmandName, Subcommand subcommand) {
        
        COMMANDS.put(fullCommmandName, subcommand);
        
        subcommand.getMeta().setAliases((List<String>) HighBrushes.instance.getDescription().getCommands().get(fullCommmandName).get("aliases"));
        subcommand.getMeta().setPermission((String) HighBrushes.instance.getDescription().getCommands().get(fullCommmandName).get("permission"));
        subcommand.getMeta().setDesc((String) HighBrushes.instance.getDescription().getCommands().get(fullCommmandName).get("description"));
        subcommand.getMeta().setUsage((String) HighBrushes.instance.getDescription().getCommands().get(fullCommmandName).get("usage"));
        
        new ArrayList<>((List<String>) HighBrushes.instance.getDescription()
            .getCommands().get(fullCommmandName).get("aliases"))
            .stream().forEach(alias -> {
                subcommands.put(alias, subcommand);
            });
        
    }
    
    
    @Override
    
    // Implementation of method from CommandExecutor
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length == 0 || subcommands.get(args[0]) == null) {
            sender.sendMessage(ChatColor.RED + "Invalid argument. Type \"/ck help\" for a list of commands."); 
        } else if (args[args.length - 1].equals("?")) {
            
            CommandMeta meta = subcommands.get(args[0]).getMeta();
            ArrayList<String> messages = new ArrayList<>();
            
            messages.add(ChatColor.GOLD + "==== Help: " + ChatColor.RED + args[0] + " ====");
            
            messages.add(ChatColor.GOLD + "\nAliases: [" + ChatColor.RED);
            meta.getAliases().stream().forEach(message -> messages.add(message + ", "));
            
            messages.add(ChatColor.GOLD + "]\nDescription: " + ChatColor.RED + meta.getDesc());
            
            messages.add(ChatColor.GOLD + "\nUsage" + ChatColor.RED + meta.getUsage());
            
            sender.sendMessage(messages.toArray(new String[messages.size()]));
            
        } else {
            subcommands.get(args[0]).execute(sender, args);
        }
        
        return true;
        
    }
    
}
