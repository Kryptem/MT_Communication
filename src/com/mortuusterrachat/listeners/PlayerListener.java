/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mortuusterrachat.MortuusTerraChat;

public class PlayerListener implements Listener {

  private MortuusTerraChat main;

  public PlayerListener(MortuusTerraChat main) {
    this.main = main;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    if (main.getPlayerManager().getPlayer(e.getPlayer()) == null) {
      main.getPlayerManager().addPlayer(e.getPlayer());
    }
  }

}
