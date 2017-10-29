package com.mortuusterrachat.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.utils.StringUtilities;

public class PlayerChatListener implements Listener {
	
  private MortuusTerraChat main = JavaPlugin.getPlugin(MortuusTerraChat.class);

  @EventHandler
  private void onChatEvent(AsyncPlayerChatEvent e) {
	  // Watch is the temporary cell phone
	  if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.WATCH)
		  return;
	  // Walkie Talkie
	  if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.REDSTONE_COMPARATOR)
		  return;
	  
	  e.setCancelled(true);

    String message = e.getMessage();
    Player sender = e.getPlayer();
    String format = ("&b" + sender.getName() + "&8&l >> &r");

    for (Player recipient : e.getRecipients()) {
    	
      // Check if players are in the same world.
      if (!sender.getWorld().equals(recipient.getWorld()))
        continue;
      
      // Sender counts as recipient so send him his unscrambled message.
      if (sender.equals(recipient)) {
        recipient.sendMessage(StringUtilities.color(format + message));
        continue;
      }
      
      // If message sent by admin/op don#t scramble
      if (sender.hasPermission("mtcom.unscrambled")) {
    	  recipient.sendMessage(StringUtilities.color(format + message));
          continue;
      }
      
      String scrambled = main.getMessageScrambler().scramble(message, sender, recipient);
      // 'Scrambled' will be null if the distance is > 100 (send no message to recipient).
      if (scrambled == null)
        continue;

      String scrambledMessage = StringUtilities.color(format + scrambled);
      recipient.sendMessage(scrambledMessage);
    }
  }

}
