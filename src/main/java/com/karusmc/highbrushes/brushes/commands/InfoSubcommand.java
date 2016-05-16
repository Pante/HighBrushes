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
package com.karusmc.highbrushes.brushes.commands;

import com.karusmc.highbrushes.brushes.PaintBrush;
import com.karusmc.highbrushes.commands.CommandMeta;
import com.karusmc.highbrushes.commands.CommandUtil;
import com.karusmc.highbrushes.commands.Subcommand;
import com.karusmc.highbrushes.listeners.PlayerHandler;

import java.util.ArrayList;
import org.bukkit.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Displays information about the player's brush.
 */
public class InfoSubcommand implements Subcommand, CommandUtil {
    
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
        
        ArrayList<String> messages = new ArrayList<>();
        
        messages.add(ChatColor.GOLD + "==== Brush Information ====\n");
        
        messages.add(ChatColor.GOLD + "Brush: " + ChatColor.RED + paintBrush.getType() + "\n");
        messages.add(ChatColor.GOLD + "Size: " + ChatColor.RED + paintBrush.getSize() + " ");
        messages.add(ChatColor.GOLD + "Intensity: " + ChatColor.RED + paintBrush.getIntensity() + "\n");
        
        messages.add(ChatColor.GOLD + "Auto-rotate: " + ChatColor.RED + paintBrush.getPalette().isAutoRotate() + " ");
        messages.add(ChatColor.GOLD + "Box-bounded: " + ChatColor.RED + paintBrush.getPalette().isBoxBounded() + "\n");
        
        messages.add(ChatColor.GOLD + "Flat mode: " + ChatColor.RED + paintBrush.getPalette().isFlat() + " ");
        messages.add(ChatColor.GOLD + "Mountain mode: " + ChatColor.RED + paintBrush.getPalette().isMountain() + "\n");
        
        sender.sendMessage(messages.toArray(new String[messages.size()]));
        
    }
        
}
