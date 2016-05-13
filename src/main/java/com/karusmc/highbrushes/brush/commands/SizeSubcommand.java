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
package com.karusmc.highbrushes.brush.commands;

import com.karusmc.highbrushes.commands.CommandMeta;
import com.karusmc.highbrushes.commands.CommandUtil;
import com.karusmc.highbrushes.commands.Subcommand;
import com.karusmc.highbrushes.io.BrushHandler;
import com.karusmc.highbrushes.listeners.PlayerHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class SizeSubcommand implements Subcommand, CommandUtil {
    
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
        if (!checkLength(sender, this, args, 1, 2)) return;
        if (!checkPlayer(sender, meta.getPermission())) return;
        
        Player player = (Player) sender;
        int size;
        
        if (args.length == 1) {
            PlayerHandler.PLAYERS.get(player.getUniqueId()).getBrush().setSize(BrushHandler.getDefaultSize());
            sender.sendMessage(ChatColor.GOLD + "Brush size has been set back to default (" + BrushHandler.getDefaultSize() + ")");
            return;
            
        } else {
            try {
                size = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Brush size must be a positive integer!");
                return;
            }
        }
        
        if (size < 0) {
            sender.sendMessage(ChatColor.RED + "Brush size must be a positive integer!");
        } else if (size > BrushHandler.getMaxSize()) {
            sender.sendMessage(ChatColor.RED + "Brush size is over the maximum allowed size (" + BrushHandler.getMaxSize() + ")");
        } else {
            PlayerHandler.PLAYERS.get(player.getUniqueId()).getBrush().setSize(size);
            sender.sendMessage(ChatColor.GOLD + "Brush size has been set to: " + size);
        }
        
    }
    
}
