package com.mortuusterrachat.listeners;

import static com.mortuusterrachat.utils.StringUtilities.color;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.devices.CellularPhone;
import com.mortuusterrachat.objects.PlayerObject;

public class CellularPhoneListener implements Listener {
  
  private MortuusTerraChat main;

  public CellularPhoneListener(MortuusTerraChat main) {
    this.main = main;
  }
  
  // Cell phone item is rotten flesh for now.
  
  @EventHandler
  public void onPlayerSpeak(AsyncPlayerChatEvent e) {
    if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.ROTTEN_FLESH)
      return;
    
    e.setCancelled(true);
    playerSpeaksOnCellPhone(e.getPlayer(), e.getMessage());
  }
  
  @EventHandler
  public void onCellPhoneInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    
    if (p.getInventory().getItemInMainHand().getType() != Material.ROTTEN_FLESH)
      return;
    
    e.setCancelled(true);
    
    CellularPhone phone = new CellularPhone(main);
    
    // Left click for recipient toggle
    if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
      if (p.isSneaking()) {
        phone.getNextContact(p);
        
      } else {
        phone.getPreviousContact(p);
        
      }
    }
    
    // Right click for text messages
    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if (p.isSneaking()) {
        phone.checkTextMessages(p);
        
      } else {
        phone.deleteTextMessage(p);
        
      }
    }
    
  }
  
  public void playerSpeaksOnCellPhone(Player p, String message) {
    PlayerObject pObject = main.getPlayerManager().getPlayer(p);
    
    if (pObject == null)
      return;
    
    if (pObject.getContacts().size() == 0) {
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + color("&7You have no contacts. Add some with [...]"));
      return;
    }
    
    if (pObject.getCurrentCellPhoneRecipient().equalsIgnoreCase(p.getName())) {
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + color("&7You can't call yourself, what are you doing?"));
      return;
    }
    
    Player recipient = main.getServer().getPlayer(pObject.getCurrentCellPhoneRecipient());
    
    // Recipient not online, send as text message
    if (recipient == null) {
      main.getTextMessageManager().addTextMessage(p.getName(), pObject.getCurrentCellPhoneRecipient(), message);
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + color("&7Contact is not online. Message sent as text."));
    
    } else {
      recipient.playSound(recipient.getLocation(), Sound.BLOCK_NOTE_CHIME, 5, 1);
      recipient.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + color("&8&l[&e" + p.getName() + "&8&l] &7" + message));
      
      p.playSound(p.getLocation(), Sound.BLOCK_NOTE_CHIME, 5, 1);
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + color("&7Called &e" + pObject.getCurrentCellPhoneRecipient() + "&7: " + message));
    }
    
  }

}
