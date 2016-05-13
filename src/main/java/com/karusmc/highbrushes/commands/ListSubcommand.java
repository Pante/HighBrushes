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
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class ListSubcommand implements Subcommand, CommandUtil {
    
    // Fields
    private CommandMeta meta;
    private final int SIZE = 5;
    
    @Override
    public CommandMeta getMeta() {
        if (meta == null) {
            meta = new CommandMeta();
        }
        return meta;
    }
    
    @Override
    public void setMeta(CommandMeta meta) {
        this.meta = meta;
    }
    
    
    @Override
    public void execute(CommandSender sender, String[] args) {
        
        if (!checkLength(sender, this, args, 1, 2)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        int page;
        
        if (args.length == 2) {
            try {
                page = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid page number!");
                return;
            }
        } else {
            page = 1;
        }
        
        int totalPages = (int) Math.max(1, Math.floor(BrushHandler.getBrushes().size() / SIZE));
        
        if (page <= 0 || page > totalPages) {
            sender.sendMessage(ChatColor.RED + "Invalid page number!");
            return;
        }
        
        ArrayList<String> brushes = new ArrayList<>(BrushHandler.getBrushes().keySet());
        
        sender.sendMessage(ChatColor.GOLD + "==== Brushes: " + ChatColor.RED + args[1] + ChatColor.GOLD 
                + " === Page: " + ChatColor.RED + page + "/" + totalPages + ChatColor.GOLD + " ====");
        
        IntStream.range(page * SIZE - SIZE, brushes.size()).limit(SIZE)
        .forEach(i -> sender.sendMessage(ChatColor.RED + brushes.get(i)));
        
    }
}
