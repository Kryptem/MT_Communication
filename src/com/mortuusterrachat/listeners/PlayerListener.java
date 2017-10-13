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
