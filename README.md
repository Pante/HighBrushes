![HighBrushes Logo](http://i.imgur.com/uSO6I7s.png "HighBrushes")
Create mountains higher than Kevaasaurus.

##About
HighBrushes is a small side project of mine that is a fork/rewrite of [Arcaniax](https://twitter.com/Arcaniax) and [Aerios](https://twitter.com/Aeriooos)'s [goBrushes plugin](http://www.planetminecraft.com/mod/spigot-gobrush---worldpainter-ingame/) that was created primarily to address the performance shortcomings and lack of per-command permission suport of the original plugin. Very much like the original plugin, HighBrushes's aim is to radically simplify your terrain building experience and improve your productivity through command short-forms/aliases. HighBrushes, like goBrushes comes pre-bundled with 15 brushes, however the possibility for custom brushes are unless and only your imagination is truly the limit. Alternatively you may purchase custom brushes made by Aerios [here](https://sellfy.com/aerios).

## Features
* Right click with a blaze rod to place the height-map
* Short and concise commands with even-shorter aliases
* Per-command permissions
* Open source code & Javadocs that makes developing for you, really convinent

##Brush properties
* Mountain mode: Makes everything a lot smoother than the heightmap normally is. If disabled the terrain will look more spiky.
* Flat Mode: Puts the maximum height at the block you click and brings everything around it up to that height.
* Box-bounded: Will deny the brush from going outside of your WE selection. This can prevent destroying surroundings.
* Auto-rotate: Will rotate the brush based on your cardinal rotation: North, East, South and West. If disabled it will always be facing North.

##Commands

### General commands
| Command         | Aliases      | Description       | Usage       | Permission      |
| :-------------- |:-------------| :--------------- | :----------- | :-------------- |
| /highbrushes      | highbrush, hbrushes, hbrush, hb | Main command | /highbrushes [subcommand] | highbrushes.command |
| /highbrushes about | a | Displays plugin info | /highbrushes about | highbrushes.about |
| /highbrushes help | h | Displays help informatio about commands | /highbrushes help [(search)] [(page)] | highbrushes.help |
| /highbrushes list | li, l | Returns a list of available brushes | //highbrushes list [(page)] | highbrushes.list |
| /highbrushes reload | re, r | Reloads the plugin component | /highbrushes reload [(component)] | highbrushes.reload |
| highbrushes toggle | t | Enables and disabled a brush | /highbrushes toggle [(autorotate/boxbounded/flat/mountain)]| highbrushes.toggle |

### Brush commands
| Command       | Aliases       | Description  | Usage    | Permission  |
| :------------ |:-------------:| :------------ | :------- | :---------- |
| /highbrushes brush | b | Select and rotate between brushes | /highbrushes brush [(brush name)] | highbrushes.brush.select |
| /highbrushes info | i | Displays curretn brush information | /highbrushes info | highbrushes.brush.info |
| /highbrushes intensity | int | Change the brush intensity | /highbrushes intensity [(intensity)] | highbrushes.brush.intensity |
| /highbrushes undo | u | description: Undo your previous actions | /highbrushes undo [(number of steps)] | highbrushes.brush.undo |
| highbrushes size | s | Change the brush size | /highbrushes size [(size)] | highbrushes.brush.size |


Append a command with a "?" symbol at the end in-game to view more information about it.

## Licensing
Copyright (C) 2016  PanteLegacy

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
