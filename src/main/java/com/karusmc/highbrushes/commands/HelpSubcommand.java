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

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Displays information about a command
 */
public class HelpSubcommand implements Subcommand, CommandUtil {
    
    // Fields
    private CommandMeta meta;
    private final int SIZE = 3;
    
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
        
        if (!checkLength(sender, this, args, 1, 3)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        int page;
        if (args.length == 3) {
            try {
                page = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid page number!");
                return;
            }
        } else {
            page = 1;
        }
        
        ArrayList<String> commands;
        if (args.length == 1 || args[1].equals("all")) {
             commands = new ArrayList<>(MainCommand.COMMANDS.entrySet().stream().filter(entry -> 
                sender.hasPermission(entry.getValue().getMeta().getPermission())
            ).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue())).keySet());

        } else {
            commands = new ArrayList<>(MainCommand.COMMANDS.entrySet().stream().filter(entry -> 
                entry.getKey().contains(args[1]) && sender.hasPermission(entry.getValue().getMeta().getPermission())
            ).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue())).keySet());
        }
        
        if (commands.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "No matches found.");
            return;
        }
        
        int totalPages = (int) Math.max(1, Math.floor(commands.size() / SIZE));
        
        if (page <= 0 || page > totalPages) {
            sender.sendMessage(ChatColor.RED + "Invalid page number!");
            return;
        }
        
        if (args.length == 1) {
            sender.sendMessage(ChatColor.GOLD + "==== Help: " + ChatColor.RED + "All" + ChatColor.GOLD 
                    + " === Page: " + ChatColor.RED + page + "/" + totalPages + ChatColor.GOLD + " ====");
        } else {
            sender.sendMessage(ChatColor.GOLD + "==== Help: " + ChatColor.RED + args[1] + ChatColor.GOLD 
                + " === Page: " + ChatColor.RED + page + "/" + totalPages + ChatColor.GOLD + " ====");
        }
        
        IntStream.range(page * SIZE - SIZE, commands.size()).limit(SIZE)
        .forEach(i -> sender.sendMessage(ChatColor.GOLD + MainCommand.COMMANDS.get(commands.get(i)).getMeta().getUsage()));
        
    }
    
}
