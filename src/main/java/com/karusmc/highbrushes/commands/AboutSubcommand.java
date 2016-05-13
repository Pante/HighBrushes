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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class AboutSubcommand implements Subcommand, CommandUtil {
    
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
        if (!checkLength(sender, this, args, 1, 1)) return;
        if (!checkSender(sender, meta.getPermission())) return;
        
        sender.sendMessage(ChatColor.GOLD 
                + "HighBrushes version: " + HighBrushes.instance.getDescription().getVersion() + "\n"
                + HighBrushes.instance.getDescription().getDescription() + "\n"
                + "Author(s): goCreative Arcaniax, PanteLegacy @ karusmc.com\n"
                + "Source code & development resources: " + ChatColor.RED + "https://github.com/Pante/HighBrushes \n"
                + ChatColor.GOLD + "Type " + ChatColor.RED + "\"/hb help\"" + ChatColor.GOLD + " for a list of commands.");
        
    }
    
}
