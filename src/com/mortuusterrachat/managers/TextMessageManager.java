package com.mortuusterrachat.managers;

import java.util.ArrayList;
import java.util.List;

import com.mortuusterrachat.objects.TextMessage;

public class TextMessageManager {
  
  private List<TextMessage> textMessages = new ArrayList<>();
  
  public List<TextMessage> getMessagesForName(String name) {
    List<TextMessage> messages = new ArrayList<>();
    
    for (TextMessage tm : textMessages) {
      if (tm.getRecipient().equalsIgnoreCase(name))
        messages.add(tm);
    }
    return messages;
  }
  
  public void addTextMessage(String sender, String recipient, String message) {
    textMessages.add(new TextMessage(sender, recipient, message));
  }
  
  public void removeTextMessage(String sender, String recipient, String message) {
    TextMessage toDelete = new TextMessage(sender, recipient, message);
    
    for (TextMessage tm : textMessages) {
      if (tm.getRecipient().equalsIgnoreCase(recipient))
        toDelete = tm;
    }
    
    textMessages.remove(toDelete);
  }
  
  public void markMessageAsRead(TextMessage message) {
    for (TextMessage tm : getMessagesForName(message.getRecipient())) {
      if (tm.equals(message))
        message.markAsRead();
    }
  }
  
  public List<TextMessage> getTextMessages() {
    return textMessages;
  }

}
