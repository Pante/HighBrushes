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

import com.karusmc.highbrushes.brushes.PaintBrush;
import com.karusmc.highbrushes.listeners.PlayerHandler;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Toggles the settings of a player's paintbrush.
 */
public class ToggleSubcommand implements Subcommand, CommandUtil {
    
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
        PaintBrush paintBrush = PlayerHandler.PLAYERS.get(player.getUniqueId());
        
        if (args.length == 1) {
            if (paintBrush.isEnabled()) {
                paintBrush.setEnabled(false);
                sender.sendMessage(ChatColor.GOLD + "Brush has been disabled");
            } else {
                paintBrush.setEnabled(true);
                sender.sendMessage(ChatColor.GOLD + "Brush has been enabled");
            }
        } else {
            
            switch (args[1].toLowerCase()) {
                case "autorotate":
                    if (paintBrush.getPalette().isAutoRotate()) {
                        paintBrush.getPalette().setAutoRotate(false);
                        sender.sendMessage(ChatColor.GOLD + "Auto rotate has been disabled");
                    } else {
                        paintBrush.getPalette().setAutoRotate(true);
                        sender.sendMessage(ChatColor.GOLD + "Auto rotate has been enabled");
                    }
                    break;
                    
                case "boxbounded":
                    if (paintBrush.getPalette().isBoxBounded()) {
                        paintBrush.getPalette().setBoxBounded(false);
                        sender.sendMessage(ChatColor.GOLD + "Box bounded has been disabled");
                    } else {
                        paintBrush.getPalette().setBoxBounded(true);
                        sender.sendMessage(ChatColor.GOLD + "Box bounded has been enabled");
                    }
                    break;
                    
                case "flat":
                    if (paintBrush.getPalette().isFlat()) {
                        paintBrush.getPalette().setFlat(false);
                        sender.sendMessage(ChatColor.GOLD + "Flat mode has been disabled");
                    } else {
                        paintBrush.getPalette().setFlat(true);
                        sender.sendMessage(ChatColor.GOLD + "Flat mode has been enabled");
                    }
                    break;
                    
                case "mountain":
                    if (paintBrush.getPalette().isMountain()) {
                        paintBrush.getPalette().setMountain(false);
                        sender.sendMessage(ChatColor.GOLD + "Mountain has been disabled");
                    } else {
                        paintBrush.getPalette().setMountain(true);
                        sender.sendMessage(ChatColor.GOLD + "Mountain has been enabled");
                    }
                    break;
                    
                default:
                    sender.sendMessage(ChatColor.RED + meta.getUsage());
                    break;
                    
            }
            
        }
        
    }
    
}
