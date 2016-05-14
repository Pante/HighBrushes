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

import com.karusmc.highbrushes.HighBrushes;
import com.karusmc.highbrushes.brush.PaintBrush;
import com.karusmc.highbrushes.io.ConfigHandler;

import com.sk89q.worldedit.bukkit.selections.Selection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author PanteLegacy @ karusmc.com
 */
public class Paint implements Listener {
    
    @EventHandler
    public void onPaint(PlayerInteractEvent e) {
        
        Player player = e.getPlayer();
        Action action = e.getAction();
        
        if (player.hasPermission("highbrushes.brush.use") 
                && PlayerHandler.PLAYERS.get(player.getUniqueId()).isEnabled()
                && e.getMaterial() == Material.BLAZE_ROD 
                && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            
            if (ConfigHandler.getDisabledWorlds().contains(player.getWorld().getName())) {
                player.sendMessage(ChatColor.RED + "Brush has been disabled in this world!");
                return;
            } 
            
            Location location;
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
              location = player.getTargetBlock((Set<Material>) null, 150).getLocation();
            } else {
              location = e.getClickedBlock().getLocation();
            }
            
            if (location.getBlock().getType().equals(Material.AIR)) {
              return;
            }
            
            PaintBrush brush = PlayerHandler.PLAYERS.get(player.getUniqueId());
            
            int size = brush.getBrush().getSize();
            int min = size / 2 * -1;
            int max = size / 2;
            
            ArrayList<Location> list = new ArrayList<>();
            HashMap<Location, Double> temp = new HashMap<>();
            
            IntStream.rangeClosed(min, max).forEach(x -> {
                IntStream.rangeClosed(min, max).forEach(z -> {
                    
                    Location loopLocation = location.add(x, 0, z);
                    Location blockLocation = brush.getHighestLoc(player, loopLocation);                  
                    
                    double height = brush.getHeight(player.getLocation(), x - min, z - min);
                    double subHeight = height % 1.0;
                    
                    if (temp.containsKey(blockLocation)){
                        subHeight = subHeight + temp.get(blockLocation);
                        temp.put(blockLocation, subHeight);
                    }
                    else if (subHeight > 0.0) {
                        temp.put(blockLocation, subHeight);
                    }
                    
                    if (subHeight > 1.0) {
                      height = height + 1.0;
                      temp.put(blockLocation, subHeight - 1.0);
                    }
                    
                    if (brush.isBoundingBox()) {
                        Selection selection = HighBrushes.instance.getWorldEdit().getSelection(player);
                            
                        if (loopLocation.getX() >= selection.getMinimumPoint().getBlockX() 
                                && loopLocation.getX() <= selection.getMaximumPoint().getBlockX()
                                && loopLocation.getZ() >= selection.getMinimumPoint().getBlockZ()
                                && loopLocation.getZ() <= selection.getMaximumPoint().getBlockZ()) {
                            
                            loopBounded(selection, location, height, blockLocation, brush, list);
                            
                        }
                        
                    } else {
                        loopUnbounded(location, height, blockLocation, brush, list);
                    }    
                    
                });
            });
            
            ArrayList<ArrayList<Location>> blockListList;
            if (PlayerHandler.UNDOS.containsKey(player.getUniqueId())) {
                blockListList = PlayerHandler.UNDOS.get(player.getUniqueId());
            } else {
                blockListList = new ArrayList<>();
            }
            blockListList.add(list);
            
            if (blockListList.size() > ConfigHandler.getMaxUndos()) {
                blockListList.remove(0);
            }
            PlayerHandler.UNDOS.put(player.getUniqueId(), blockListList);
            
        }
        
    }
    
    
    private void loopBounded(Selection selection, Location location, double height, Location block, PaintBrush brush, ArrayList<Location> list) {
        
        for (int y = 1; y < Math.floor(height); y++) {
            if ((block.add(0, y, 0).getY() >= selection.getMinimumPoint().getBlockY()) 
                    && (block.add(0, y, 0).getY() <= selection.getMaximumPoint().getBlockY())) {
                
                if (brush.isFlat()) {
                    Location tempLoc = block.add(0, y, 0);
                    tempLoc.getBlock().setType(block.getBlock().getType());
                    if (tempLoc.getBlock().getType() != block.getBlock().getType()) {
                        tempLoc.getBlock().setType(block.getBlock().getType());
                    }
                    list.add(tempLoc);
                } else if (block.add(0, y, 0).getY() <= location.getY()) {
                    
                    Location tempLoc = block.getBlock().getLocation().add(0, y, 0);
                    tempLoc.getBlock().setType(block.getBlock().getType());
                    if (tempLoc.getBlock().getType() != block.getBlock().getType()) {
                        tempLoc.getBlock().setType(block.getBlock().getType());
                    }
                    list.add(tempLoc);
                    
                }
            }
        }
        
    }
    
    private void loopUnbounded(Location location, double height, Location block, PaintBrush brush, ArrayList<Location> list) {
        for (int y = 1; y < Math.floor(height); y++) {
            if (brush.isFlat()) {
                Location tempLoc = block.add(0, y, 0);
                tempLoc.getBlock().setType(block.getBlock().getType());
                if (tempLoc.getBlock().getType() != block.getBlock().getType()) {
                    tempLoc.getBlock().setType(block.getBlock().getType());
                }
                list.add(tempLoc);
            } else if (block.add(0, y, 0).getY() <= location.getY()) {

                Location tempLoc = block.getBlock().getLocation().add(0, y, 0);
                tempLoc.getBlock().setType(block.getBlock().getType());
                if (tempLoc.getBlock().getType() != block.getBlock().getType()) {
                    tempLoc.getBlock().setType(block.getBlock().getType());
                }
                list.add(tempLoc);

            }
        }
    }
    
}
