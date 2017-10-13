package com.mortuusterrachat.devices;

import java.util.List;

import org.bukkit.entity.Player;

import com.mortuusterrachat.MortuusTerraChat;
import com.mortuusterrachat.objects.PlayerObject;
import com.mortuusterrachat.objects.TextMessage;
import com.mortuusterrachat.utils.StringUtilities;

public class CellularPhone {

  private MortuusTerraChat main;

  public CellularPhone(MortuusTerraChat main) {
    this.main = main;
  }

  public void getNextContact(Player p) {
    PlayerObject pObject = main.getPlayerManager().getPlayer(p);
    String currentRecipient = pObject.getCurrentCellPhoneRecipient();
    String nextRecipient = "";

    int contactIndex = 0;

    if (pObject.getContacts().size() == 0) {
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7You have no contacts..."));
      return;

    } else {
      for (String contactName : pObject.getContacts()) {

        if (contactName.equalsIgnoreCase(currentRecipient)) {
          if (contactIndex == pObject.getContacts().size() - 1) {

            nextRecipient = pObject.getContacts().get(0);

          } else {
            nextRecipient = pObject.getContacts().get(contactIndex + 1);
          }
        }

        contactIndex++;
      }

      if (currentRecipient.equalsIgnoreCase("")) {
        nextRecipient = pObject.getContacts().get(0);
      }
      pObject.setCurrentCellPhoneRecipient(nextRecipient);
      p.sendMessage(
          MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7Current Contact: &6" + nextRecipient));
    }
  }

  public void getPreviousContact(Player p) {
    PlayerObject pObject = main.getPlayerManager().getPlayer(p);
    String currentRecipient = pObject.getCurrentCellPhoneRecipient();
    String previousRecipient = "";

    int contactIndex = 0;

    if (pObject.getContacts().size() == 0) {
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7You have no contacts..."));
      return;

    } else {
      for (String contactName : pObject.getContacts()) {

        if (contactName.equalsIgnoreCase(currentRecipient)) {
          if (contactIndex == 0) {

            previousRecipient = pObject.getContacts().get(pObject.getContacts().size() - 1);

          } else {

            previousRecipient = pObject.getContacts().get(contactIndex - 1);
          }
        }

        contactIndex++;
      }

      if (previousRecipient.equalsIgnoreCase(""))
        previousRecipient = pObject.getContacts().get(0);
    }

    pObject.setCurrentCellPhoneRecipient(previousRecipient);
    p.sendMessage(
        MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7Current Contact: &6" + previousRecipient));
  }

  public void checkTextMessages(Player p) {
    if (main.getTextMessageManager().getMessagesForName(p.getName()).size() == 0) {
      p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7You have no messages."));
      return;
    }

    List<TextMessage> playerMessages = main.getTextMessageManager().getMessagesForName(p.getName());

    // Get the latest message
    TextMessage latestMessage = playerMessages.get(playerMessages.size() - 1);
    String messageStatus = StringUtilities.color("&7Old");

    if (!latestMessage.isRead()) {
      messageStatus = StringUtilities.color("&aNew");
    }

    p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&8&l[" + messageStatus + "&8&l] [&e"
        + latestMessage.getSender() + "&8&l] &3" + latestMessage.getMessage()));
    main.getTextMessageManager().markMessageAsRead(latestMessage);
  }

  public void deleteTextMessage(Player p) {
    if (main.getTextMessageManager().getMessagesForName(p.getName()).isEmpty()) {
      p.sendMessage(
          MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7You have no messages to delete."));
      return;
    }
    TextMessage message = main.getTextMessageManager().getMessagesForName(p.getName()).get(0);

    main.getTextMessageManager().removeTextMessage(message.getSender(), message.getRecipient(),
        message.getMessage());
    p.sendMessage(MortuusTerraChat.CELLPHONE_PREFIX + StringUtilities.color("&7Message deleted."));
  }
}
