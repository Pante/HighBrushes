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

import com.karusmc.highbrushes.commands.CommandMeta;
import com.karusmc.highbrushes.commands.CommandUtil;
import com.karusmc.highbrushes.commands.Subcommand;
import com.karusmc.highbrushes.io.BrushHandler;
import com.karusmc.highbrushes.io.ConfigHandler;
import com.karusmc.highbrushes.listeners.PlayerHandler;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Undoes the changes the player makes.
 */
public class UndoSubcommand implements Subcommand, CommandUtil {
    
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
        int undo;
        
        if (args.length == 1) {
            //new Eraser(PlayerHandler.UNDOS.get(player.getUniqueId()), ConfigHandler.getMaxUndos()).undoActions();
            sender.sendMessage(ChatColor.GOLD + "Actions have been undone");
            return;
            
        } else {
            try {
                undo = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Undo amount be a postive integer!");
                return;
            }
        }
        
        if (undo < 0) {
            sender.sendMessage(ChatColor.RED + "Undo amount must be a positive integer!");
        } else if (undo > BrushHandler.getMaxSize()) {
            sender.sendMessage(ChatColor.RED + "Undo amount is over the maximum allowed size (" + ConfigHandler.getMaxUndos() + ")");
        } else {
            
            if (PlayerHandler.UNDOS.containsKey(player.getUniqueId()) && !PlayerHandler.UNDOS.get(player.getUniqueId()).isEmpty()) {
                int amount = 0;
                for (int x = 0; x < undo; x++) {
                    ArrayList<ArrayList<Location>> list = PlayerHandler.UNDOS.get(player.getUniqueId());
                    list.get(list.size() - 1).stream().forEach(loc -> loc.getBlock().setType(Material.AIR));
                    amount += list.get(list.size() - 1).size();
                    list.remove(list.size() - 1);
                }
                sender.sendMessage(ChatColor.GOLD + "Blocks undone: " + ChatColor.RED + amount);
            } else {
                sender.sendMessage(ChatColor.GOLD + "Nothing to undo");
            }
            
        }
        
    }
    
}
