package com.mortuusterrachat.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerObject {

	private UUID uuid;
	private String currentCellPhoneRecipient;
	private Frequency currentWalkieTalkieFrequency;
	private boolean receiveNotificationSound;

	private List<String> contacts;

	public PlayerObject(UUID uuid, String currentCellPhoneRecipient, Frequency currentWalkieTalkieFrequency) {
		this.uuid = uuid;
		this.receiveNotificationSound = true;
		this.currentCellPhoneRecipient = currentCellPhoneRecipient;
		this.currentWalkieTalkieFrequency = currentWalkieTalkieFrequency;

		this.contacts = new ArrayList<>();
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public boolean receiveNotificationSound() {
		return receiveNotificationSound;
	}
	
	public void setReceiveNotificationSound(boolean receiveNotificationSound) {
		this.receiveNotificationSound = receiveNotificationSound;
	}
	
	public Frequency getCurrentWalkieTalkieFrequency() {
		return currentWalkieTalkieFrequency;
	}

	public void setCurrentWalkieTalkieFrequency(Frequency currentWalkieTalkieFrequency) {
		this.currentWalkieTalkieFrequency = currentWalkieTalkieFrequency;
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
