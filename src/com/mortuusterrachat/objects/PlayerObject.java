package com.mortuusterrachat.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerObject {

  private UUID uuid;
  private String currentCellPhoneRecipient;

  private List<String> contacts;

  public PlayerObject(UUID uuid, String currentCellPhoneRecipient) {
    this.uuid = uuid;
    this.currentCellPhoneRecipient = currentCellPhoneRecipient;

    this.contacts = new ArrayList<>();
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setContacts(List<String> contacts) {
    this.contacts = contacts;
  }
  
  public List<String> getContacts() {
    return contacts;
  }

  public String getCurrentCellPhoneRecipient() {
    return currentCellPhoneRecipient;
  }
  
  public void setCurrentCellPhoneRecipient(String currentCellPhoneRecipient) {
    this.currentCellPhoneRecipient = currentCellPhoneRecipient;
  }

}
