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

import com.karusmc.highbrushes.brush.Direction;

import org.bukkit.Material;
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
        
        if (player.hasPermission("highbrushes.brush.use") && e.getMaterial() == Material.BLAZE_ROD 
                && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            
            // TODO: Implement logic here
            
        }
        
    }
    
    private double getHeight(Player player, int x, int z, boolean blur) {
        
        int size = PlayerHandler.PLAYERS.get(player.getUniqueId()).getBrush().getImage().getHeight();
        
        int tempX = x;
        int tempZ = z;
        
        if (PlayerHandler.PLAYERS.get(player.getUniqueId()).isAutoRotation()) {
            switch (Direction.caculate(player)) {
                case EAST:
                    x = tempZ;
                    z = size - tempX;
                    break;
                    
                case SOUTH:
                    x = size - tempX;
                    z = size - tempZ;
                    break;
                
                case WEST:
                    x = size - tempZ;
                    z = tempX;
            }
        }
        
        int rgb = 0;
        
        if ((z < size) && (x < size) && (x >= 0) && (z >= 0)) {
          rgb = PlayerHandler.PLAYERS.get(player.getUniqueId()).getBrush().getImage().getRGB(x, z);
        }
        int red = rgb >>> 16 & 0xFF;
        int green = rgb >>> 8 & 0xFF;
        int blue = rgb & 0xFF;
        if ((red == 0) && (green == 0) && (blue == 0)) {
          return 0;
        }
        
        double grayScale = (red + blue + green) / 3.0 / 255.0;

        double height = grayScale * PlayerHandler.PLAYERS.get(player.getUniqueId()).getBrush().getIntensity();
        if (!blur) {
          return height;
        }
        
        
    }
    
}
