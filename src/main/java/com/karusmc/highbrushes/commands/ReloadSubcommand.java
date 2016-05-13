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

import com.karusmc.highbrushes.io.BrushHandler;
import com.karusmc.highbrushes.io.ConfigHandler;
import com.karusmc.highbrushes.io.Output;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class ReloadSubcommand implements Subcommand, CommandUtil {
    
    // Fields
    private CommandMeta meta;
    
    @Override
    public CommandMeta getMeta() {
        if (meta == null) {
            meta = new CommandMeta();
        }
        return meta;
    }
    
    @Override
    public void setMeta(CommandMeta properties) {
        this.meta = properties;
    }
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        // Inheritied from CommandUtil
        if (!checkLength(sender, this, args, 1, 3)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        Output<String, Exception> out = (message, exception) -> {
            if (exception == null) {
                sender.sendMessage(message);
            } else {
                sender.sendMessage(message);
            }
        };
        
        
        if (args.length == 1 || args[1].equals("all")) {
            ConfigHandler.load(out);
            BrushHandler.loadDefaults(out);
            BrushHandler.loadBrushes(out);
            return;
        }
        
        switch (args[1].toLowerCase()) {
            
            case "config":
                ConfigHandler.load(out);
                break;
                
            case "arena":
                loadBrushes(sender, args, out);
                break;
                
            default:
                sender.sendMessage(ChatColor.RED + "Invalid argument: " + args[1]);
                break;
            
        }
        
    }
    
    private void loadBrushes(CommandSender sender, String[] args, Output<String, Exception> out) {
        if (args.length <= 2) {
            BrushHandler.loadBrushes(out);
        } else sender.sendMessage(BrushHandler.loadBrush(args[2]));
    }
    
}
