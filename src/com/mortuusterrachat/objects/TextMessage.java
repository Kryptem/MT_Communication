/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see <https://github.com/kadeska/MT_Communication/blob/master/LICENSE>.
 */
package com.mortuusterrachat.objects;

public class TextMessage {

  private String sender;
  private String recipient;
  private String message;
  private boolean read;
  
  public TextMessage(String sender, String recipient, String message) {
    this.sender = sender;
    this.recipient = recipient;
    this.message = message;
    this.read = false;
  }
  
  public String getConfigFormat() {
	  return sender + "-" + recipient + "-" + message;
  }

  public String getSender() {
    return sender;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getMessage() {
    return message;
  }
  
  public boolean isRead() {
    return read;
  }

  public void markAsUnRead() {
    this.read = false;
  }

  public void markAsRead() {
    this.read = true;
  }
  
}
