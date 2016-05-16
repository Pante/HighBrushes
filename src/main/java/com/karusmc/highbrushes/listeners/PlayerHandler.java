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
package com.karusmc.highbrushes.listeners;

import com.karusmc.highbrushes.brushes.PaintBrush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Handles player related events
 */
public class PlayerHandler implements Listener {
    
    // Fields
    
    /** Maps a player's UUID to a PaintBrush object*/
    public static final HashMap<UUID, PaintBrush> PLAYERS = new HashMap<>();
    
    /** Maps a player's UUID to a arraylist of Location objects in another arraylist.*/
    public static final HashMap<UUID, ArrayList<ArrayList<Location>>> UNDOS = new HashMap<>();
    
    @EventHandler
    /** Attaches a PaintBrush object to the player on join.
     * 
     */
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        PLAYERS.put(player.getUniqueId(), new PaintBrush());
    }
    
    @EventHandler
    /** Clears the player's undo list and removes the attached PaintBrush from the hashmap.
     * 
     */
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        
        PLAYERS.remove(player.getUniqueId());
        UNDOS.remove(player.getUniqueId());
    }
    
}
