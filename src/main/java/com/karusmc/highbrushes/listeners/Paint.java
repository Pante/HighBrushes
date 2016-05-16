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
import com.karusmc.highbrushes.brushes.PaintBrush;
import com.karusmc.highbrushes.io.ConfigHandler;

import com.sk89q.worldedit.bukkit.selections.Selection;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.IntStream;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 *
 * @author PanteLegacy @ karusmc.com
 * Contains event handler that detects when the brush is used.
 */
public class Paint implements Listener {
    
    @EventHandler
    /* Calculates and places the blocks when the brush is used
    */
    public void onPaint(PlayerInteractEvent e) {
        
        if (e.getMaterial() == Material.BLAZE_ROD && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            
            Player p = e.getPlayer();
            
            PaintBrush brush = PlayerHandler.PLAYERS.get(p.getUniqueId());
            
            Location loc;
            if (e.getAction() == Action.RIGHT_CLICK_AIR) {
                loc = p.getTargetBlock((Set<Material>) null, 150).getLocation();
            } else {
                loc = e.getClickedBlock().getLocation();
            }
            
            if (loc.getBlock().getType() == Material.AIR) {
                return;
            }
            
            int size = brush.getSize();
            int min = size / 2 * -1;
            int max = size / 2;
            
            ArrayList<Location> locList = new ArrayList<>();
            
            Location loopLocation = loc.clone();
            
            IntStream.rangeClosed(min, max).forEach(x -> {
                
                IntStream.rangeClosed(min, max).forEach(z -> {
                    
                    loopLocation.add(x, 0, z);
                    Location blockLocation = brush.getPalette().getHighestLoc(p, loopLocation);

                    double height = brush.getHeight(p.getLocation().getYaw(), x - min, z - min);
                    double subHeight = height % 1.0;
                    
                    if (subHeight > 1) {
                        height = height + 1.0;
                    }
                    
                    if (brush.getPalette().isBoxBounded()) {
                        Selection selection = HighBrushes.instance.getWorldEdit().getSelection(p);
                        
                        if (loopLocation.getBlockX() >= selection.getMinimumPoint().getBlockX() 
                                && loopLocation.getBlockX() <= selection.getMaximumPoint().getBlockX()
                                && loopLocation.getBlockZ() >= selection.getMinimumPoint().getBlockZ() 
                                && loopLocation.getBlockZ() <= selection.getMaximumPoint().getBlockZ()) {
                            
                            IntStream.range(1, (int) Math.floor(height)).forEach(y -> {
                                if (blockLocation.getBlockY() + y >= selection.getMinimumPoint().getBlockY() 
                                        && blockLocation.getBlockY() + y <= selection.getMaximumPoint().getBlockY()) {
                                    
                                    placeBlocks(brush, y, blockLocation, loc, locList);
                                }
                            });
                                
                        }
                    } else {
                        IntStream.range(1, (int) Math.floor(height)).forEach(y -> {
                            placeBlocks(brush, y, blockLocation, loc, locList);
                        });        
                    }
                    
                    loopLocation.subtract(x, 0, z);
                });
                
            });
            
            ArrayList<ArrayList<Location>> locListList = PlayerHandler.UNDOS.getOrDefault(p.getUniqueId(), new ArrayList<>());

            locListList.add(locList);
            if (locListList.size() > ConfigHandler.getMaxUndos()) {
                locListList.remove(0);
            }
            
            PlayerHandler.UNDOS.put(p.getUniqueId(), locListList); 
            
        }
    }
    
    
    // Helper method
    private void placeBlocks(PaintBrush brush, int y, Location blockLocation, Location loc, ArrayList<Location> locList) {
        if (!brush.getPalette().isFlat() || blockLocation.getBlockY() + y <= loc.getY()) {
                                
            Block tempBlock = blockLocation.add(0, y, 0).getBlock();
            blockLocation.subtract(0, y, 0);

            tempBlock.setType(blockLocation.getBlock().getType());

            if (tempBlock.getData() != blockLocation.getBlock().getData()) {
                tempBlock.setData(blockLocation.getBlock().getData());
            }
            
            locList.add(tempBlock.getLocation());

        }
    }
    
}
